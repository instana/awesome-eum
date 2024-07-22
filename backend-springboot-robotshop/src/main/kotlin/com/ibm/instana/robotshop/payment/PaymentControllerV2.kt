package com.ibm.instana.robotshop.payment

import com.ibm.instana.robotshop.constants.Constants
import com.ibm.instana.robotshop.models.Payment
import com.ibm.instana.robotshop.models.ResponseObject
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("/${Constants.VERSION_NUMBER_V2}/payment")
class PaymentControllerV2(val restTemplate: RestTemplate) {

    @PostMapping("/validate")
    @ResponseBody
    fun paymentRequestHandler2(@RequestBody requestBody: Payment): ResponseEntity<ResponseObject> {
        val response = ServiceGateway2(restTemplate).performGatewayValidation()
        return ResponseEntity(ResponseObject(message = response), HttpStatus.OK)
    }

    @GetMapping("/gateway")
    @ResponseBody
    fun paymentGatewayHandler():String{
        return ServicePayment2(restTemplate).performPayment()
    }

    @GetMapping("/gateway_distributors")
    @ResponseBody
    fun paymentGatewayDistributorsHandler2():String{
        return "Success"
    }


}

@Service
class ServicePayment2(private val restTemplate: RestTemplate) {
    fun performPayment(): String {
        val response = restTemplate.getForObject("${Constants.BASE_URL_INTERNAL}/gateway_mapper", String::class.java)
        val response2 = restTemplate.getForObject("${Constants.BASE_URL_INTERNAL}/gateway_whitelist_search", String::class.java)
        val response3 = restTemplate.getForObject("${Constants.BASE_URL_INTERNAL}${Constants.VERSION_NUMBER_V2}/payment/gateway_distributors", String::class.java)
        return "Service API call result: $response $response2 $response3"
    }
}

@Service
class ServiceGateway2(private val restTemplate: RestTemplate) {
    fun performGatewayValidation(): String {
        val response = restTemplate.getForObject("${Constants.BASE_URL_INTERNAL}${Constants.VERSION_NUMBER_V2}/payment/gateway", String::class.java)
        return ServicePayment2(restTemplate).performPayment()
    }
}