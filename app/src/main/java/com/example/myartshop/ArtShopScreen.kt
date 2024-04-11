package com.example.myartshop

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myartshop.ui.OrderViewModel
import com.example.myartshop.ui.ui.SelectedPhotoScreen
import com.example.myartshop.ui.ui.StartPageScreen

enum class ArtShopScreen(@StringRes val title:Int) {
    Start(title = R.string.main_page),
    ArtistList(title = R.string.artist),
    CategoryList(title = R.string.category),
    PaintingsList(title = R.string.paintings),
    PaintingViewer(title = R.string.chosen_painting),
    Summary(title = R.string.payment)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtshopAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun ArtShopApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = ArtShopScreen.valueOf(
        backStackEntry?.destination?.route ?: ArtShopScreen.Start.name
    )

    Scaffold(
        topBar = {
            ArtshopAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    )
    { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ArtShopScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable(route = ArtShopScreen.Start.name) {
                StartPageScreen()
            }
//
//            composable(route = ArtShopScreen.PaintingViewer.name) {
//                ImagesScreen(TODO()) {
//
//                }
//            }
//
//            composable(route = ArtShopScreen.PaintingViewer.name) {
//                SelectedPhotoScreen() {
//                }
//            }
//
//            composable(route = ArtShopScreen.Summary.name) {
//                SummaryScreen() {

//
//                }
            }
        }
        //val uiState by viewModel.uiState.collectAsState()

    }
