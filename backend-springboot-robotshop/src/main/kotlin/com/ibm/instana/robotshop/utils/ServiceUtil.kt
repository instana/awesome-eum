package com.ibm.instana.robotshop.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ibm.instana.robotshop.constants.Constants.BASE_URL_INTERNAL
import com.ibm.instana.robotshop.constants.Constants.VERSION_NUMBER_V1
import com.ibm.instana.robotshop.models.ProductItem
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.BufferedReader
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Service
class ServiceUtil {

    fun readJsonFile(fileName: String): ArrayList<ProductItem> {
        val gson = Gson()
        val resource = ClassPathResource(fileName).inputStream
        val reader = BufferedReader(resource.reader())
        val listType = object : TypeToken<List<ProductItem>>() {}.type
        return gson.fromJson(reader, listType)
    }
}

@Configuration
class AppConfig {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}

@Service
class ServiceRunValidate(private val restTemplate: RestTemplate) {
    fun performAction(): String {
        val response = restTemplate.getForObject("$BASE_URL_INTERNAL$VERSION_NUMBER_V1/user/validate_create", String::class.java)
        return "Service API call result: $response"
    }
}

@Service
class GetDateTimeMilliService(private val restTemplate: RestTemplate) {
    fun getDateTimeMilli(date: String, time: String): Long? {
        return restTemplate.postForObject(
            "$BASE_URL_INTERNAL$VERSION_NUMBER_V1/notifications/date_time_to_milli",
            DateTime(date,time), Long::class.java
        )
    }
}


fun dateTimeToMillis(date: String, time: String, dateFormat: String = "yyyy-MM-dd", timeFormat: String = "HH:mm:ss"): Long {
    // Combine the date and time strings into a single datetime string
    val dateTimeString = "$date $time"
    // Create a formatter for the combined datetime string
    val formatter = DateTimeFormatter.ofPattern("$dateFormat $timeFormat")
    // Parse the datetime string into a LocalDateTime object
    val localDateTime = LocalDateTime.parse(dateTimeString, formatter)
    // Convert the LocalDateTime to an Instant and get the milliseconds since the epoch
    return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

data class DateTime(
    val date: String,
    val time: String
)