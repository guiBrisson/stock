package me.brisson.stock.feature.product.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import me.brisson.stock.feature.product.screen.product_list.ProductListRoute

const val productRoute = "product_route"

fun NavController.navigateToProduct(navOptions: NavOptions? = null) {
    this.navigate(productRoute, navOptions)
}

fun NavGraphBuilder.productScreen() {
    composable(route = productRoute) {
        ProductListRoute()
    }
}
