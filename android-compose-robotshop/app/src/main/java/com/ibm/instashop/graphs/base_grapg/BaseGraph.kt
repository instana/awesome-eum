package com.ibm.instashop.graphs.base_grapg

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ibm.instashop.graphs.Graph
import com.ibm.instashop.graphs.init_flow.initNavGraph
import com.ibm.instashop.screens.home.HomeScreen
import com.instana.android.Instana

@Composable
fun RootNavigationGraph(navHostController: NavHostController, context: Context) {
    NavHost(
        navController = navHostController,
        route = Graph.ROOT,
        startDestination = Graph.INITIAL_FLOW,
    ) {
        navHostController.addOnDestinationChangedListener{
            _,destination,_ ->
            Instana.view = destination.route
        }
        initNavGraph(navHostController, context)
        composable(route = Graph.HOME) {
            HomeScreen()
        }


    }
}