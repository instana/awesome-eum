package com.ibm.instashop.graphs.home

sealed class RoboShopHomeScreen(val route: String) {
    object DashboardScreen : RoboShopHomeScreen("dashboard_screen")
    object SettingsScreen : RoboShopHomeScreen("settings_screen")
    object ProfileScreen : RoboShopHomeScreen("profile_screen")
    object ScannerScreen : RoboShopHomeScreen("favourite_screen")
}