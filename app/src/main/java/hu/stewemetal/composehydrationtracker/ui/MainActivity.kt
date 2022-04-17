package hu.stewemetal.composehydrationtracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.stewemetal.composehydrationtracker.Screen.Add
import hu.stewemetal.composehydrationtracker.Screen.BottomNavScreen.Entries
import hu.stewemetal.composehydrationtracker.Screen.BottomNavScreen.Stats
import hu.stewemetal.composehydrationtracker.Screen.Main
import hu.stewemetal.composehydrationtracker.ui.add.AddDrinkScreen
import hu.stewemetal.composehydrationtracker.ui.main.MainScreen
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HydrationTrackerApp()
        }
    }
}

@Composable
fun HydrationTrackerApp() {
    val navController = rememberNavController()

    HydrationTrackerTheme {
        NavHost(
            navController = navController,
            startDestination = Main.route,
        ) {
            composable(Main.route) {
                MainScreen(
                    onAddClick = { navController.navigate(Add.route) },
                )
            }
            composable(Add.route) {
                AddDrinkScreen(
                    onBackClick = { navController.popBackStack() },
                )
            }
        }
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Main : Screen("main", R.string.main_title)
    sealed class BottomNavScreen(
        route: String,
        @StringRes resourceId: Int,
        @DrawableRes val icon: Int,
    ) : Screen(route, resourceId) {
        object Entries : BottomNavScreen("entries", R.string.bottom_nav_entries, R.drawable.outline_local_drink)
        object Stats : BottomNavScreen("stats", R.string.bottom_nav_stats, R.drawable.outline_bar_chart)
    }

    object Add : Screen("add", R.string.bottom_nav_add)
}

val bottomNavItems = listOf(
    Entries,
    Stats,
)
