package hu.stewemetal.composehydrationtracker.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hu.stewemetal.composehydrationtracker.R
import hu.stewemetal.composehydrationtracker.Screen.BottomNavScreen.Entries
import hu.stewemetal.composehydrationtracker.Screen.BottomNavScreen.Stats
import hu.stewemetal.composehydrationtracker.bottomNavItems
import hu.stewemetal.composehydrationtracker.ui.main.entries.EntriesScreen
import hu.stewemetal.composehydrationtracker.ui.main.stats.StatsScreen

@Composable
fun MainScreen(onAddClick: () -> Unit) {
    val bottomBarNavController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(id = R.string.main_title))
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onAddClick,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.description_add),
                    )
                }
            },
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by bottomBarNavController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    bottomNavItems.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(ImageVector.vectorResource(screen.icon), contentDescription = null) },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                bottomBarNavController.navigate(screen.route) {
                                    popUpTo(bottomBarNavController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = bottomBarNavController,
                startDestination = Entries.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Entries.route) { EntriesScreen() }
                composable(Stats.route) { StatsScreen() }
            }
        }
    }
}
