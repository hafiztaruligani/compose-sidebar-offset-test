package com.example.kmp.android

import kotlinx.serialization.Serializable


@Serializable
sealed interface Route {


    /*@Serializable
    data object AuthRoute: Route {*/
        @Serializable data object Login : Route
        @Serializable data object Register : Route
    //}


    @Serializable
     object SideBarRoute: Route {
        @Serializable object Menu1 : Route
        @Serializable object Menu2 : Route
        @Serializable object Menu3 : Route
    }

}