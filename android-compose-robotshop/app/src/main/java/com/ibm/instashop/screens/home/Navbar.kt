package com.ibm.instashop.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ibm.instashop.graphs.home.RoboShopHomeScreen
import com.ibm.instashop.graphs.items_detail.DetailScreen
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.TextColor

@Composable
fun NavigationBar(
    navController: NavController,
    isVisible: (Boolean) -> Unit,
) {
    val navItemList = listOf(
        BottomNavItem.HomeNav,
        BottomNavItem.FavouriteNav,
        BottomNavItem.ChatNav,
        BottomNavItem.ProfileNav,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var bottomNavVisibility by remember { mutableStateOf<Boolean>(true) }

    if (bottomNavVisibility) {
        BottomNavigation(
            backgroundColor = Color.White,
            modifier = Modifier
                .background(color = Color.White)
                .shadow(
                    shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                    elevation = 12.dp,
                )
                .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp))


        ) {
            navItemList.forEach { screen ->
                BottomNavigationItem(
                    selected = navBackStackEntry?.destination?.route == screen.route,
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (navBackStackEntry?.destination?.route == screen.route) MaterialTheme.colors.PrimaryColor else LocalContentColor.current,
                        )
                    },
                    label = { Text(text = screen.tittle, color = MaterialTheme.colors.TextColor) },
                    onClick = {
                        navController.navigate(screen.route)
                    },
                    unselectedContentColor = MaterialTheme.colors.TextColor,
                )
            }
        }
    }

    //hide topBar
    when (navBackStackEntry?.destination?.route) {
        RoboShopHomeScreen.DashboardScreen.route -> {
            bottomNavVisibility = true
            isVisible(true)
        }
        DetailScreen.ProductDetailScreen.route, DetailScreen.CartScreen.route, DetailScreen.NotificationScreen.route -> {
            bottomNavVisibility = false
            isVisible(false)
        }
        else -> {
            bottomNavVisibility = true
            isVisible(false)
        }
    }


}