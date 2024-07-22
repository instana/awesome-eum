package com.ibm.instashop.screens.intro_screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ibm.instashop.R
import com.ibm.instashop.graphs.init_flow.InitScreen
import com.ibm.instashop.screens.common.components.PrimaryButton
import com.ibm.instashop.ui.theme.PrimaryColor
import com.ibm.instashop.ui.theme.TextColor

@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun IntroScreen(navController: NavController) {
    val splashImageList = listOf(
        R.drawable.intro_1,
        R.drawable.intro_2,
        R.drawable.intro_3,
    )
    val currentPosition = remember { mutableStateOf(0) }
    val animate = remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "RobotShop",
            fontSize = 50.sp,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
        )
        AnimatedContent(
            targetState = currentPosition.value,
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { value -> value }
                ) with slideOutHorizontally(
                    targetOffsetX = { value -> -value }
                )
            }, label = ""
        ) { targetState ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                when (targetState) {
                    0 -> {
                        Text(
                            text = buildAnnotatedString {
                                append("Welcome to ")
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colors.PrimaryColor,
                                    )
                                ) {
                                    append("RobotShop.")
                                }
                                append(" Let's Shop with Robo!")
                            },
                            color = MaterialTheme.colors.TextColor,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                        )
                    }
                    1 -> {
                        Text(
                            text = "RobotShop has a massive collection of items you are searching for",
                            color = MaterialTheme.colors.TextColor,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    else -> {
                        Text(
                            text = "No more waiting for items for Days, Robots deliver items within hours",
                            color = MaterialTheme.colors.TextColor,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(id = splashImageList[targetState]),
                    contentDescription = "Splash Image",
                    modifier = Modifier
                        .padding(40.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                20.dp
                            )
                        )
                )
            }
        }

        DotIndicator(splashImageList.size, currentPosition.value)

        PrimaryButton(btnText = "Next", shapeSize = 10f) {
        if (currentPosition.value < 2) {
            currentPosition.value++
            animate.value = !animate.value
        } else {
            navController.navigate(InitScreen.SignInScreen.route)
        }
    }
    }
}
