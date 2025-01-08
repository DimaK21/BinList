package ru.kryu.binlist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.kryu.binlist.Destinations.CARD_LIST
import ru.kryu.binlist.Destinations.SEARCH
import ru.kryu.binlist.presentation.cardlist.ListScreen
import ru.kryu.binlist.presentation.search.SearchScreen

@Composable
fun BinNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = SEARCH,
    modifier: Modifier = Modifier
) {
    NavHost(navController, startDestination, modifier) {
        composable(SEARCH) {
            SearchScreen(
                onFabClicked = { navController.navigate(CARD_LIST) },
                modifier = modifier,
            )
        }
        composable(CARD_LIST) {
            ListScreen(modifier = modifier)
        }
    }
}

object Destinations {
    const val SEARCH = "search"
    const val CARD_LIST = "card_list"
}