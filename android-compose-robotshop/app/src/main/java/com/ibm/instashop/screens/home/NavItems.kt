package com.ibm.instashop.screens.home

import com.ibm.instashop.R
import com.ibm.instashop.graphs.home.RoboShopHomeScreen

sealed class BottomNavItem(val tittle: String, val icon: Int, val route: String) {
    object HomeNav : BottomNavItem(
        tittle = "Home",
        icon = R.drawable.home,
        route = RoboShopHomeScreen.DashboardScreen.route
    )

    object FavouriteNav : BottomNavItem(
        tittle = "Profile",
        icon = R.drawable.profile,
        route = RoboShopHomeScreen.ProfileScreen.route

    )

    object ChatNav : BottomNavItem(
        tittle = "Scan",
        icon = R.drawable.scan,
        route = RoboShopHomeScreen.ScannerScreen.route

    )

    object ProfileNav : BottomNavItem(
        tittle = "Settings",
        icon = R.drawable.settings,
        route = RoboShopHomeScreen.SettingsScreen.route
    )
}