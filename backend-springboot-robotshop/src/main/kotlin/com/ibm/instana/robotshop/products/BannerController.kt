package com.ibm.instana.robotshop.products

import com.ibm.instana.robotshop.constants.Constants
import com.ibm.instana.robotshop.models.Banner
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/${Constants.VERSION_NUMBER_V1}/banner")
class BannerController {

    @GetMapping("/all")
    @ResponseBody
    fun getAllBanners(): ArrayList<Banner> {
        return arrayListOf(
            Banner("/images/banner1.jpeg", "/images/banner_item1.png", "Up to 25% Off", "iPhone 15 Pro Max"),
            Banner("/images/banner2.jpeg", "/images/banner_item2.jpeg", "Cashback", "35% cashback on JBL headset"),
            Banner("/images/banner3.jpeg", "/images/banner_item3.png", "Free Coupons", "Buy 4 electronic items, get coupons worth $350")
        )
    }
}