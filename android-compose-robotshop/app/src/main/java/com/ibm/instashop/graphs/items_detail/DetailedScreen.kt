package com.ibm.instashop.graphs.items_detail

sealed class DetailScreen( val route: String) {
    object CartScreen : DetailScreen("cart_screen")
    object NotificationScreen : DetailScreen("notification_screen")
    object ProductDetailScreen : DetailScreen("product_detail_screen")
    object PaymentScreen : DetailScreen("payment_screen")
    object PaymentSuccess : DetailScreen("payment_success")
}