package com.ibm.instashop.screens.signup

import android.util.Patterns
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ibm.instashop.R
import com.ibm.instashop.graphs.init_flow.InitScreen
import com.ibm.instashop.screens.common.components.AlertSuggestion
import com.ibm.instashop.screens.common.components.BackButton
import com.ibm.instashop.screens.common.components.CustomInputField
import com.ibm.instashop.screens.common.components.PrimaryButton
import com.ibm.instashop.screens.login.LoginViewModel
import com.ibm.instashop.ui.theme.PrimaryLightColor
import com.ibm.instashop.ui.theme.TextColor
import com.instana.android.Instana

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SignUpScreen(navController: NavController,loginViewModel: LoginViewModel = hiltViewModel()) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPass by remember { mutableStateOf(TextFieldValue("")) }
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    val emailErrorState = remember { mutableStateOf(false) }
    val passwordErrorState = remember { mutableStateOf(false) }
    val conPasswordErrorState = remember { mutableStateOf(false) }
    val animate = remember { mutableStateOf(true) }

    val scrollState = rememberScrollState()

    AnimatedContent(targetState = animate.value, transitionSpec = {
        slideInHorizontally(
            initialOffsetX = { value ->
                value
            }
        ) with slideOutHorizontally(
            targetOffsetX = { value ->
                -value
            }
        )
    }, label = "") {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Box(modifier = Modifier.weight(0.7f)) {
                    BackButton {
                        navController.popBackStack()
                    }
                }
                Box(modifier = Modifier.weight(1.0f)) {
                    Text(
                        text = "Sign Up",
                        color = MaterialTheme.colors.TextColor,
                        fontSize = 18.sp
                    )
                }


            }
            Spacer(modifier = Modifier.height(35.dp))
            Text(text = "Register Account", fontSize = 26.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Complete your details or continue\nwith social media.",
                color = MaterialTheme.colors.TextColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(25.dp))
            CustomInputField(
                placeholder = "John Doe",
                trailingIcon = R.drawable.lock,
                label = "User Name",
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
                onChanged = { userNameUpdate ->
                    userName = userNameUpdate
                }
            )
            Spacer(modifier = Modifier.height(25.dp))
            CustomInputField(
                placeholder = "example@gmail.com",
                trailingIcon = R.drawable.email,
                label = "Email",
                errorState = emailErrorState,
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
                onChanged = { newEmail ->
                    email = newEmail
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            CustomInputField(
                placeholder = "********",
                trailingIcon = R.drawable.password,
                label = "Password",
                keyboardType = KeyboardType.Password,
                errorState = passwordErrorState,
                visualTransformation = PasswordVisualTransformation(),
                onChanged = { newPass ->
                    password = newPass
                }
            )


            Spacer(modifier = Modifier.height(20.dp))
            CustomInputField(
                placeholder = "********",
                trailingIcon = R.drawable.password,
                label = "Confirm Password",
                keyboardType = KeyboardType.Password,
                errorState = conPasswordErrorState,
                visualTransformation = PasswordVisualTransformation(),
                onChanged = { newPass ->
                    confirmPass = newPass
                }
            )

            Spacer(modifier = Modifier.height(10.dp))
            if (emailErrorState.value) {
                AlertSuggestion("Email not valid")
            }
            if (passwordErrorState.value) {
                Row() {
                    AlertSuggestion("Password not valid")
                }
            }
            if (conPasswordErrorState.value) {
                AlertSuggestion("Password miss matched.")
            }
            PrimaryButton(shapeSize = 50f, btnText = "Continue") {
                //email pattern
                val pattern = Patterns.EMAIL_ADDRESS
                val isEmailValid = pattern.matcher(email.text).matches()
                val isPassValid = password.text.length >= 8
                val conPassMatch = password == confirmPass
                emailErrorState.value = !isEmailValid
                passwordErrorState.value = !isPassValid
                conPasswordErrorState.value = !conPassMatch
                loginViewModel.createUser(userName.text,email.text,password.text)
                loginViewModel.createUserState.value.data.let {
                    Instana.userName = userName.text
                    Instana.userEmail = email.text
                    navController.navigate(InitScreen.SignInScreen.route){
                        popUpTo(InitScreen.SignInScreen.route) { inclusive = true }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 10.dp,
                        alignment = Alignment.CenterHorizontally
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                MaterialTheme.colors.PrimaryLightColor,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.google_icon),
                            contentDescription = "Google Login Icon"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                MaterialTheme.colors.PrimaryLightColor,
                                shape = CircleShape
                            )
                            .clickable {

                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.twitter),
                            contentDescription = "Twitter Login Icon"
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                MaterialTheme.colors.PrimaryLightColor,
                                shape = CircleShape
                            )
                            .clickable {

                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.facebook_2),
                            contentDescription = "Facebook Login Icon"
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                        .clickable {

                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Or simply login using \uD83D\uDC46",
                        color = MaterialTheme.colors.TextColor
                    )
                    Spacer(modifier = Modifier.height(200.dp))
                }

            }


        }
    }
}