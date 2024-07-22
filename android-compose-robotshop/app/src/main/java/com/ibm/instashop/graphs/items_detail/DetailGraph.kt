package com.ibm.instashop.graphs.items_detail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ibm.instashop.business_unit.models.OrderSuccess
import com.ibm.instashop.graphs.Graph
import com.ibm.instashop.screens.cart.CartScreen
import com.ibm.instashop.screens.notification.NotificationScreen
import com.ibm.instashop.screens.payment.PaymentComplete
import com.ibm.instashop.screens.payment.PaymentPage
import com.ibm.instashop.screens.product_details.ProductDetailScreen

fun NavGraphBuilder.detailGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailScreen.ProductDetailScreen.route /*+ "/{${Constrains.PRODUCT_ID_PARAM}}"*/
    ) {
        composable(DetailScreen.CartScreen.route) {
            CartScreen(navController)
        }
        composable(DetailScreen.NotificationScreen.route) {
            NotificationScreen(navController)
        }
        composable(DetailScreen.PaymentScreen.route){
            PaymentPage(navController)
        }
        composable(DetailScreen.PaymentSuccess.route){
            PaymentComplete(navController)
        }
        composable(DetailScreen.ProductDetailScreen.route) {
            ProductDetailScreen(navController) {
                navController.popBackStack()
            }
        }
    }
}