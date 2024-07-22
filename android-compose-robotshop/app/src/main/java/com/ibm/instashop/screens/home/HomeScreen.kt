package com.ibm.instashop.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ibm.instashop.graphs.home.HomeNavGraph
import com.ibm.instashop.graphs.items_detail.DetailScreen
import com.instana.android.Instana

@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
) {

    val topBarVisibilityState = remember {
        mutableStateOf(true)
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        Instana.view = destination.route
    }


    Scaffold(
        modifier = Modifier.background(MaterialTheme.colors.primary),
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                isVisible = topBarVisibilityState.value,
                searchCharSequence = {

                },
                onCartIconClick = {
                    navController.navigate(DetailScreen.CartScreen.route)
                },
                onNotificationIconClick = {
                    navController.navigate(DetailScreen.NotificationScreen.route)
                })
        },
        bottomBar = {
            NavigationBar(navController = navController) { isVisible ->
                topBarVisibilityState.value = isVisible
            }
        },
    ) { padding ->
        padding
        HomeNavGraph(navHostController = navController)
    }
}