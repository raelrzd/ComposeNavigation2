package br.com.alura.panucci.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import br.com.alura.panucci.model.Product
import br.com.alura.panucci.ui.screens.DrinksListScreen
import br.com.alura.panucci.ui.viewmodels.DrinksListViewModel

internal const val drinksRoute = "drinks"
private const val uri = "alura://panucci.com.br/drinks"

fun NavGraphBuilder.drinksScreen(
    onNavigateToProductDetails: (Product) -> Unit
) {
    composable(
        route = drinksRoute,
        deepLinks = listOf(navDeepLink { uriPattern = uri })
    ) {
        val viewModel = viewModel<DrinksListViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        DrinksListScreen(
            uiState = uiState,
            onProductClick = onNavigateToProductDetails,
        )
    }
}

fun NavController.navigateToDrinks(
    navOptions: NavOptions? = null
) {
    navigate(drinksRoute, navOptions)
}