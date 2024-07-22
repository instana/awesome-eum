package com.ibm.instashop.graphs.init_flow

sealed class InitScreen(val route: String) {
    object OnBoardingScreen : InitScreen("splash_screen")
    object SignInScreen : InitScreen("sign_in_screen")
    object SignUpScreen : InitScreen("sign_up_screen")
}