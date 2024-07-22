package com.ibm.instana.robotshop.notifications

import com.ibm.instana.robotshop.constants.Constants
import com.ibm.instana.robotshop.models.Notifications
import com.ibm.instana.robotshop.utils.DateTime
import com.ibm.instana.robotshop.utils.GetDateTimeMilliService
import com.ibm.instana.robotshop.utils.dateTimeToMillis
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

@RestController
@RequestMapping("/${Constants.VERSION_NUMBER_V1}/notifications")
class NotificationController(private val restTemplate: RestTemplate) {

    @GetMapping("/all")
    @ResponseBody
    fun getAllNotifications(): Any {
        val dateTimeService = GetDateTimeMilliService(restTemplate)
        try {
            TimeUnit.SECONDS.sleep(9)
            if ((System.currentTimeMillis()%2).toInt() != 0){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Simulated 500 Internal Server Error")
            }else{
                return arrayListOf(
                    Notifications(
                        title = "Order Dispatched",
                        subTitle = "Your order ID : #1234DER has been dispatched",
                        dateTime = dateTimeService.getDateTimeMilli("2024-06-17", "12:34:56").toString(),
                        complete = false
                    ),
                    Notifications(
                        title = "Item Delivered",
                        subTitle = "Your order ID : #54324D4H has been delivered",
                        dateTime = dateTimeService.getDateTimeMilli("2024-02-14", "10:24:36").toString(),
                        complete = false
                    ),
                    Notifications(
                        title = "Item Packed",
                        subTitle = "Your order ID : #KNTDIF5 is packed for Dispatch",
                        dateTime = dateTimeService.getDateTimeMilli("2024-03-20", "12:24:16").toString(),
                        complete = false
                    ),
                    Notifications(
                        title = "Item Delivered",
                        subTitle = "Your order ID : #1234DER is packed for Dispatch",
                        dateTime = dateTimeService.getDateTimeMilli("2024-06-14", "12:24:16").toString(),
                        complete = true
                    ),
                )
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
        return arrayListOf<Notifications>()
    }

    @PostMapping("/date_time_to_milli")
    @ResponseBody
    fun getDateTimeInMilli(@RequestBody requestBody: DateTime): Long? {
        return dateTimeToMillis(requestBody.date, requestBody.time)
    }


}