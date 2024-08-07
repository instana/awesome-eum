package com.ibm.instashop.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.widget.RemoteViews
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.ibm.instashop.R
import com.ibm.instashop.business_unit.models.ProductItem
import com.instana.android.core.event.worker.EventWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class SharedPreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "robotshop_prefs"
        private const val KEY_TITLE_STRING = "example_string_title"
        const val KEY_URL_STRING = "example_string_title"
        private const val KEY_EXAMPLE_BOOLEAN = "example_boolean"
    }
    fun saveString(value: String,key: String=KEY_TITLE_STRING) = sharedPreferences.edit().putString(key, value).apply()
    fun getString(key: String = KEY_TITLE_STRING)= sharedPreferences.getString(KEY_TITLE_STRING, null)
    fun saveBoolean(value: Boolean) = sharedPreferences.edit().putBoolean(KEY_EXAMPLE_BOOLEAN, value).apply()
    // if you need to see flickering then you can uninstall the app and install again with below default value as true
    fun getBoolean() = sharedPreferences.getBoolean(KEY_EXAMPLE_BOOLEAN, false)
}
class RoboShopWidgetProvider: AppWidgetProvider() {
    private var bitMap:Bitmap? = null
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val prefs = SharedPreferencesHelper(context)
        if (!prefs.getBoolean()) {
            enqueueDelayedWorker(context)
            prefs.saveBoolean(true)
        }
        val sharedPreferencesHelper = SharedPreferencesHelper(context)
        val dataFromApi = sharedPreferencesHelper.getString() ?: "No data"
        val urlFromData = sharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_URL_STRING) ?: "http://www.google.com"
        for (appWidgetId in appWidgetIds) {
            val intent = Intent(context, javaClass).apply {
                action = ACTION_FETCH_DATA
            }
            GlobalScope.launch {
                bitMap = getBitMap(context,urlFromData)
            }
            val pendingIntent = PendingIntent.getBroadcast(
                context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            val views = RemoteViews(context.packageName, R.layout.widget_layout).apply {
                setOnClickPendingIntent(R.id.fetchDataBtn, pendingIntent)
                setTextViewText(R.id.widget_text,dataFromApi)
                setImageViewBitmap(R.id.image,bitMap)
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }


    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        val sharedPreferencesHelper = SharedPreferencesHelper(context)
        if (ACTION_FETCH_DATA == intent.action) {
            CoroutineScope(Dispatchers.IO).launch {
                val result = fetchApiData()
                sharedPreferencesHelper.saveString(result.title.take(17)+"...")
                sharedPreferencesHelper.saveString(result.image,SharedPreferencesHelper.KEY_URL_STRING)
                withContext(Dispatchers.Main) {
                    updateWidget(context, result)
                }
            }
        }
    }

    private suspend fun fetchApiData(): ProductItem {
        val url = URL("https://fakestoreapi.com/products/${Random.nextInt(1, 19)}") // Replace with your API endpoint
        val connection = url.openConnection() as HttpURLConnection
        return try {
            val jsonResponse = connection.inputStream.bufferedReader().readText()
            Gson().fromJson(jsonResponse, ProductItem::class.java)
        } finally {
            connection.disconnect()
        }
    }

    private suspend fun updateWidget(context: Context, result: ProductItem) {
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val componentName = ComponentName(context, RoboShopWidgetProvider::class.java)
        val bitmap: Bitmap = withContext(Dispatchers.IO) {
            Glide.with(context)
                .asBitmap()
                .load(result.image)
                .submit(150, 100)
                .get()
        }
        val views = RemoteViews(context.packageName, R.layout.widget_layout).apply {
            setTextViewText(R.id.widget_text, result.title.take(17)+"..")
            setImageViewBitmap(R.id.image,bitmap)
        }

        appWidgetManager.updateAppWidget(componentName, views)
    }

    companion object {
        const val ACTION_FETCH_DATA = "com.ibm.instashop.widget.ACTION_FETCH_DATA"
    }
    //This is a workaround to overcome the issue with, WorkManager sending `android.appwidget.action.APPWIDGET_UPDATE`
    //after the work is completed each time causing the Widget to update couple of times
    //Issue Reference : https://issuetracker.google.com/issues/241076154
    private fun enqueueDelayedWorker(context: Context) {
        val workRequest = OneTimeWorkRequest.Builder(EventWorker::class.java)
            .setInitialDelay(5 * 365, TimeUnit.DAYS)
            .setConstraints(Constraints.Builder().setRequiresCharging(true).build())
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            "appWidgetWorkerKeepEnabled",
            ExistingWorkPolicy.KEEP,
            workRequest
        )
    }


    private suspend fun getBitMap(context: Context, url:String):Bitmap{
        return withContext(Dispatchers.IO) {
            Glide.with(context)
                .asBitmap()
                .load(url)
                .submit(150, 100)
                .get()
        }
    }

}