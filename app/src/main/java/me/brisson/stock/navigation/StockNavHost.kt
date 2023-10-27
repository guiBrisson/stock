package me.brisson.stock.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import me.brisson.stock.feature.product.navigation.productRoute
import me.brisson.stock.feature.product.navigation.productScreen

@Composable
fun StockNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onShowSnackbar: suspend (String, String?) -> Boolean,
    startDestination: String = productRoute,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        productScreen()
    }

}