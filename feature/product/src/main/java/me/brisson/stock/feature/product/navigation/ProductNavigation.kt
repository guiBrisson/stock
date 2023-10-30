package me.brisson.stock.feature.product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import me.brisson.stock.feature.product.screen.new_product.NewProductRoute
import me.brisson.stock.feature.product.screen.product_detail.ProductDetailRoute
import me.brisson.stock.feature.product.screen.product_list.ProductListRoute

const val productIdArgs = "product_id"

private const val productDetailScreen = "product_detail_route"

const val productsRoute = "products_route"
private const val newProductRoute = "new_product_route"
private const val productDetailRoute = "$productDetailScreen/{$productIdArgs}"

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
            onProduct = { productId ->
                val route = "$productDetailScreen/$productId"
                navController.navigate(route)
            }
        )
    }

    composable(route = newProductRoute) {
        NewProductRoute(
            onShowSnackbar = onShowSnackbar,
            onBack = { navController.navigateUp() },
        )
    }

    composable(
        route = productDetailRoute,
        arguments = listOf(navArgument(productIdArgs) { type = NavType.IntType })
    ) {
        ProductDetailRoute(
            onBack = { navController.navigateUp() }
        )
    }
}
