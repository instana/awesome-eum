package com.ibm.instana.robotshop.discount

import com.ibm.instana.robotshop.constants.Constants
import com.ibm.instana.robotshop.models.DiscountCoupon
import com.ibm.instana.robotshop.models.ResponseObject
import com.ibm.instana.robotshop.models.User
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/${Constants.VERSION_NUMBER_V1}/discount")
class DiscountController {


    @PostMapping("/coupon")
    @ResponseBody
    fun discountService(@RequestBody requestBody: DiscountCoupon): ResponseEntity<CouponApplicable> {
        return ResponseEntity(CouponApplicable("true",ApplicableValue("some","thing")), HttpStatus.OK)
    }

    data class CouponApplicable(val couponApplicable:String,val applicableValue:ApplicableValue)
    data class ApplicableValue(val valueFirst:String,val valueSecond:String)


}