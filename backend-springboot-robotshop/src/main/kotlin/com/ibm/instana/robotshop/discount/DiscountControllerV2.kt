package com.ibm.instana.robotshop.discount

import com.ibm.instana.robotshop.constants.Constants
import com.ibm.instana.robotshop.models.DiscountCoupon
import com.ibm.instana.robotshop.models.ResponseObject
import com.ibm.instana.robotshop.models.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/${Constants.VERSION_NUMBER_V2}/discount")
class DiscountControllerV2 {


    @PostMapping("/coupon")
    @ResponseBody
    fun discountService(@RequestBody requestBody: DiscountCoupon): ResponseEntity<CouponApplicable> {
        return ResponseEntity(CouponApplicable(true,1.23), HttpStatus.OK)
    }

    data class CouponApplicable(val couponApplicable:Boolean,val applicableValue:Double)



}