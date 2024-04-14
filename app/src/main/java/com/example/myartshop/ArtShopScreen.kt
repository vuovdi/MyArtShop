package com.example.myartshop

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myartshop.data.Category
import com.example.myartshop.data.DataSource
import com.example.myartshop.data.DataSource.listOfPhotos
import com.example.myartshop.data.DataSource.listOfartists
import com.example.myartshop.data.OrderUiState
import com.example.myartshop.data.Photo
import com.example.myartshop.ui.OrderViewModel
import com.example.myartshop.ui.ui.CategoriesPage
import com.example.myartshop.ui.ui.PhotoGridScreen
import com.example.myartshop.ui.ui.SelectArtistPage
import com.example.myartshop.ui.ui.SelectedPhotoScreen
import com.example.myartshop.ui.ui.StartPageScreen
import com.example.myartshop.ui.ui.SummaryScreen
import com.example.myartshop.ui.ui.calculateTotalPrice

enum class ArtShopScreen(@StringRes val title:Int) {
    Start(title = R.string.main_page),
    ArtistList(title = R.string.artists),
    CategoryList(title = R.string.categories),
    ArtistPhotosList(title = R.string.Artist_photos), // bare ressursreferanse her
    CategoryPhotosList(title = R.string.Category_photos),
    PhotoViewer(title = R.string.chosen_photo),
    Summary(title = R.string.payment)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtshopAppBar(
    currentScreen: ArtShopScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
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
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
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
//                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {

            /** START SCREEN - SUCCESSFULLY NAVIGATES TO ARTIST LIST */
            composable(route = ArtShopScreen.Start.name) {
                StartPageScreen(
                    viewModel = viewModel,
                    shoppingCart = viewModel.shoppingCart.value,
                    onArtistButtonClicked = {
                        navController.navigate(ArtShopScreen.ArtistList.name) },
                    onCategoryButtonClicked = { navController.navigate(ArtShopScreen.CategoryList.name)},
                    onPayButtonClicked = { navController.navigate(ArtShopScreen.Summary.name)}
                )
            }

            /** ARTIST LIST */
            composable(route = ArtShopScreen.ArtistList.name) {

                SelectArtistPage(
                    artistList = listOfartists,
                    onChosenArtistClicked = { selectedArtist ->
                        viewModel.setSelectedArtist(selectedArtist)
                        navController.navigate(ArtShopScreen.ArtistPhotosList.name) })
            }

            composable(route = ArtShopScreen.CategoryList.name) {
                val yourPhotoList = listOfPhotos
                CategoriesPage(
                    categories = Category.entries,
                    photos = yourPhotoList,
                    onChosenCategoryClicked = { selectedCategory ->
                        viewModel.setSelectedCategory(selectedCategory)
                        navController.navigate(ArtShopScreen.CategoryPhotosList.name) })
            }

            composable(route = ArtShopScreen.ArtistPhotosList.name) {
                val selectedArtist = viewModel.selectedArtist
                val photoBySelectedArtist = listOfPhotos.filter {it.artist == selectedArtist}
                PhotoGridScreen(
                    photos = photoBySelectedArtist,
                    onPhotoClicked = { selectedPhoto: Photo ->
                        viewModel.setSelectedPhoto(selectedPhoto)
                        navController.navigate(ArtShopScreen.PhotoViewer.name)
                    })
            }

            composable(route = ArtShopScreen.CategoryPhotosList.name) {
                val selectedCategory = viewModel.selectedCategory
                val photoBySelectedArtist = listOfPhotos.filter {it.category == selectedCategory}
                PhotoGridScreen(
                    photos = photoBySelectedArtist,
                    onPhotoClicked = { selectedPhoto: Photo ->
                        viewModel.setSelectedPhoto(selectedPhoto)
                        navController.navigate(ArtShopScreen.PhotoViewer.name)
                    })
            }

            composable(route = ArtShopScreen.PhotoViewer.name) {
                val selectedPhoto = viewModel.selectedPhoto
                val selectedFrameOptions = viewModel.selectedFrameOptions
                val frameAdditionalPrice = calculateTotalPrice(
                    selectedFrameOptions.value?.first ?: "",
                    selectedFrameOptions.value?.second ?: "",
                    selectedFrameOptions.value?.third ?: ""
                )
                SelectedPhotoScreen(
                    photo = selectedPhoto,
                    viewModel = viewModel,
                    onAddToCartClicked = { photo ->
                        viewModel.addToCart(
                            selectedPhoto = photo,
                            frameType = selectedFrameOptions.value?.first ?: "medium",
                            frameWidth = selectedFrameOptions.value?.second ?: "10mm",
                            photoSize = selectedFrameOptions.value?.third ?: "small",
                            price = photo!!.price,
                            frameAdditionalPrice = frameAdditionalPrice)
                        navController.navigate(ArtShopScreen.Start.name)
                    },
                    onHomeClicked = {navController.navigate(ArtShopScreen.Start.name)})
            }


            /** SUMMARY/PAYMENT -- CAN SUCCESSFULLY BE NAVIGATED TO AND NAVIGATES TO POPUP-DIALOG -> HOMESCREEN */
            composable(route = ArtShopScreen.Summary.name) {
                val photosList = DataSource.listOfPhotos
                val cartItems = DataSource.cartItems

                val modifier = Modifier // Example modifier
                var showPopupDialog by remember { mutableStateOf(false) }

                if (showPopupDialog) {
                    PopupDialog(
                        viewModel = viewModel,
                        navController = navController
                    )
                } else {
                    SummaryScreen(
                        viewModel = viewModel,
                        onPay = { showPopupDialog = true  }, // Set showPopupDialog to true
                        modifier = modifier
                    )
                }
            }
        }
    }
}


@Composable
fun PopupDialog(
    viewModel: OrderViewModel,
    navController: NavHostController,
) {
    Dialog(onDismissRequest = { navController.popBackStack(ArtShopScreen.Start.name, inclusive = false)}) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0.5f)
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimaryContainer
            )

        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your payment was successful",
                    modifier = Modifier.padding(16.dp).align(Alignment.Start),
                    color = MaterialTheme.colorScheme.onPrimary)
                Text(
                    text = "Thank you for choosing us as your art provider",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onPrimary)


                TextButton(
                    onClick = {
                        viewModel.resetOrder()
                        navController.popBackStack(ArtShopScreen.Start.name, inclusive = false) },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text(
                        text  = "ok",
                        color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}


