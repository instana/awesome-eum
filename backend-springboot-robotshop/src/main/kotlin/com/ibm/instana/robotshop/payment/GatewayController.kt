package com.ibm.instana.robotshop.payment

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
class GatewayController(val restTemplate: RestTemplate) {

    @GetMapping("/gateway_mapper")
    @ResponseBody
    fun paymentGatewayMappingHandler():String{
        return "Success"
    }

    @GetMapping("/gateway_whitelist_search")
    @ResponseBody
    fun paymentGatewayWhitelistHandler():String{
        return "Success"
    }


}