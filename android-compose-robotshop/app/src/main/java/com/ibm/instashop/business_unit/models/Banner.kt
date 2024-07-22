package com.ibm.instashop.business_unit.models

import androidx.compose.ui.graphics.Color

data class Banner(
    val backgroundImageUrl: String = "",
    val itemImageUrl:String = "",
    val color:Color = Color(0xFF673AB7),
    val bannerTitle:String = "",
    val bannerSubTitle:String = ""
)
