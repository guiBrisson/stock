package me.brisson.stock.feature.product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import me.brisson.stock.feature.product.screen.new_product.NewProductRoute
import me.brisson.stock.feature.product.screen.product_list.ProductListRoute

const val productsRoute = "products_route"
const val newProductRoute = "new_product_route"

fun NavController.navigateToProduct(navOptions: NavOptions? = null) {
    this.navigate(productsRoute, navOptions)
}

fun NavGraphBuilder.productScreen(
    navController: NavHostController,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = productsRoute) {
        ProductListRoute(
            onNewProduct = { navController.navigate(route = newProductRoute) },
        )
    }

    composable(route = newProductRoute) {
        NewProductRoute(
            onShowSnackbar = onShowSnackbar,
            onBack = { navController.navigateUp() },
        )
    }
}
