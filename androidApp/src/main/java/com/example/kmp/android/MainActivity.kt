package com.example.kmp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    var navController: NavHostController? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Box(modifier = Modifier.fillMaxSize().padding(top = 32.dp)) {

                    navController = rememberNavController()

                    NavHost(
                        navController = navController as NavHostController,
                        startDestination = Route.Login
                    ) {

                        loginScreenRoute(::navigate)
                        registerScreenRoute(::navigate)
                        sidebarScreenRoute(navController!!, ::navigate)


                    }


                }
            }
        }
    }

    private fun navigate(route: Route) {
        navController?.navigate(route)
    }

}


/*fun NavGraphBuilder.authScreenRoute(navigate: (Route) -> Unit) {
    composable<Route.AuthRoute> {
        AuthScreen(navigate)
    }
}
@Composable
fun AuthScreen(navigate: (Route) -> Unit) {
    Row {

        Button(
            onClick = { navigate(Route.AuthRoute.Login) }
        ) {
            Text("Login")
        }

        Button(
            onClick = { navigate(Route.AuthRoute.Register) }
        ) {
            Text("Register")
        }

    }
}*/



fun NavGraphBuilder.loginScreenRoute(navigate: (Route) -> Unit) {
    composable<Route.Login> {
        LoginScreen(navigate)
    }
}
@Composable
fun LoginScreen(navigate: (Route) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { navigate(Route.SideBarRoute) }
        ) {
            Text("Submit Login, go to home")
        }
        Button(
            onClick = { navigate(Route.Register) }
        ) {
            Text("Register")
        }
    }
}


fun NavGraphBuilder.registerScreenRoute(navigate: (Route) -> Unit) {
    composable<Route.Register> {
        RegisterScreen(navigate)
    }
}
@Composable
fun RegisterScreen(navigate: (Route) -> Unit) {
    Button(
        onClick = { navigate(Route.Register) }
    ) {
        Text("Submit Register, go to login")
    }
}









fun NavGraphBuilder.menu1ScreenRoute(navigate: (Route) -> Unit) {
    composable<Route.SideBarRoute.Menu1> {
        Menu1Screen()
    }
}
@Composable
fun Menu1Screen() {
    Text("MENU 1", color = Color.Black)
}


fun NavGraphBuilder.menu2ScreenRoute(navigate: (Route) -> Unit) {
    composable<Route.SideBarRoute.Menu2> {
        Menu2Screen()
    }
}
@Composable
fun Menu2Screen() {
    Text("MENU 2", color = Color.Black)
}

fun NavGraphBuilder.menu3ScreenRoute(navigate: (Route) -> Unit) {
    composable<Route.SideBarRoute.Menu3> {
        Menu3Screen()
    }
}
@Composable
fun Menu3Screen() {
    Text("MENU 3", color = Color.Black)
}


fun NavGraphBuilder.sidebarScreenRoute(navHostController: NavHostController, navigate: (Route) -> Unit) {
    composable<Route.SideBarRoute> {
        SideBarScreen(navHostController)
    }
}
@Composable
private fun SideBarScreen(navHostController: NavHostController) {
    var n: NavHostController? = null
    val offset = remember { Animatable(initialValue = 0f) }
    Row(
        Modifier
            .background(Color.White)
            .padding(top = 32.dp)
    ) {

        Box(
            modifier = Modifier
                .weight(0.8f)
                .zIndex(1f)
        ) {
            var barWidth by remember { mutableFloatStateOf(0f) }
            val scope = rememberCoroutineScope()


            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.Blue)
                    .onSizeChanged {
                        barWidth = it.width.toFloat()
                    }
            ) {
                Column {

                    Button(onClick = { n?.navigate(Route.SideBarRoute.Menu1) }) {
                        Text(
                            text = "Menu 1",
                            fontSize = 24.sp,
                            modifier = Modifier
                                .background(Color.Red)
                                .padding(vertical = 8.dp),
                            color = Color.White
                        )
                    }


                    Button(onClick = { n?.navigate(Route.SideBarRoute.Menu2) }) {
                        Text(
                            text = "Menu 2",
                            fontSize = 24.sp,
                            modifier = Modifier
                                .background(Color.Red)
                                .padding(vertical = 8.dp),
                            color = Color.White
                        )
                    }


                    Button(onClick = { n?.navigate(Route.SideBarRoute.Menu3) }) {
                        Text(
                            text = "Menu 3",
                            fontSize = 24.sp,
                            modifier = Modifier
                                .background(Color.Red)
                                .padding(vertical = 8.dp),
                            color = Color.White
                        )
                    }


                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .offset {
                        IntOffset(offset.value.toInt(), 0)
                    }
                    .pointerInput(barWidth) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { _, dragAmount ->
                                scope.launch {
                                    val newOffset = (offset.value + dragAmount)
                                        .coerceIn(0f, barWidth)
                                    offset.snapTo(newOffset)
                                }
                            },

                            onDragEnd = {
                                if (offset.value >= barWidth / 2f) {
                                    scope.launch {
                                        offset.animateTo(barWidth)
                                    }
                                } else {
                                    scope.launch {
                                        offset.animateTo(0f)
                                    }
                                }
                            }
                        )
                    },
            ) {

                Column(modifier = Modifier.fillMaxSize()) {

                    Box(modifier = Modifier) {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Green)
                        ) {
                            items((0..100).toList()) {
                                Text(
                                    text = "Menu $it",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .background(Color.Red)
                                        .padding(vertical = 8.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }

                    Box(modifier = Modifier.weight(1f).fillMaxSize().background(Color.LightGray)) {

                        n = rememberNavController()
                        SideBarNav(n!!)


                    }

                }


            }
        }



        Box(
            modifier = Modifier
                .weight(0.2f)
                .zIndex(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Yellow)
            ) { }
        }

    }

}


@Composable
fun SideBarNav(navHostController: NavHostController) {


    NavHost(
        navController = navHostController as NavHostController,
        startDestination = Route.SideBarRoute.Menu1,
        modifier = Modifier
    ) {

        menu1ScreenRoute {  }
        menu2ScreenRoute {  }
        menu3ScreenRoute {  }

    }
}



class SideBarViewModel: ViewModel() {

}
