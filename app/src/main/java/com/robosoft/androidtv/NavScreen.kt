package com.robosoft.androidtv

sealed interface NavScreen {
    val route: String
    data object Home : NavScreen {
        override val route: String
            get() = "Home"
    }
    data object Search : NavScreen {
        override val route: String
            get() = "search"
    }

    data object Settings : NavScreen {
        override val route: String
            get() = "settings"
    }
}