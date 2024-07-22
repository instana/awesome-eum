package com.ibm.instashop.graphs.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibm.instashop.graphs.Graph
import com.ibm.instashop.graphs.init_flow.InitScreen
import com.ibm.instashop.graphs.items_detail.detailGraph
import com.ibm.instashop.screens.dashboard.DashboardScreen
import com.ibm.instashop.screens.profile.ProfileScreen
import com.ibm.instashop.screens.scanner.ScannerScreen
import com.ibm.instashop.screens.settings.SettingsScreen

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        route = Graph.HOME,
        startDestination = RoboShopHomeScreen.DashboardScreen.route
    ) {
        composable(RoboShopHomeScreen.DashboardScreen.route) {
            DashboardScreen(navHostController) { productId ->
                navHostController.navigate(InitScreen.SignInScreen.route/* + "/${productId}"*/)
            }
        }
        composable(RoboShopHomeScreen.ScannerScreen.route) {
            ScannerScreen()
        }
        composable(RoboShopHomeScreen.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(RoboShopHomeScreen.ProfileScreen.route) {
            ProfileScreen()
        }
        detailGraph(navController = navHostController)
    }
}