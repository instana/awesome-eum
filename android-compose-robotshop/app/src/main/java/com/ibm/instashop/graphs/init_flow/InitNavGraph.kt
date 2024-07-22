package com.ibm.instashop.graphs.init_flow

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.WindowInsets
import android.view.WindowManager
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ibm.instashop.graphs.Graph
import com.ibm.instashop.screens.intro_screens.IntroScreen
import com.ibm.instashop.screens.login.LoginScreen
import com.ibm.instashop.screens.signup.SignUpScreen

fun NavGraphBuilder.initNavGraph(navController: NavHostController, context: Context) {
    navigation(
        route = Graph.INITIAL_FLOW,
        startDestination = InitScreen.OnBoardingScreen.route
    ) {
        composable(InitScreen.OnBoardingScreen.route) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                (context as Activity).window.decorView.windowInsetsController?.hide(
                    WindowInsets.Type.statusBars()
                );
            } else {
                (context as Activity).window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
            IntroScreen(navController)
        }
        composable(InitScreen.SignInScreen.route) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                (context as Activity).window.decorView.windowInsetsController?.show(
                    WindowInsets.Type.statusBars()
                );
            } else {
                (context as Activity).window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                }

            }
            LoginScreen(navController = navController)
        }

        composable(InitScreen.SignUpScreen.route) {
            SignUpScreen(navController = navController)
        }

    }
}