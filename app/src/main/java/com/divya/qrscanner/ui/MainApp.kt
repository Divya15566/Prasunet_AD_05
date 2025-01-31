package com.divya.qrscanner.ui

import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.divya.qrscanner.R
import com.divya.qrscanner.ui.component.BottomBar
import com.divya.qrscanner.ui.component.FloatingButton
import com.divya.qrscanner.ui.component.TopBar
import com.divya.qrscanner.ui.screen.GenerateScreen
import com.divya.qrscanner.ui.screen.HistoryScreen
import com.divya.qrscanner.ui.screen.HomeScreen
import com.divya.qrscanner.ui.utils.MainViewModel
import com.divya.qrscanner.ui.utils.Screen

@Composable
fun MainApp(viewModel: MainViewModel,checkCameraPermission:()->Unit){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar(navController,backStackEntry)},
        floatingActionButton = { FloatingButton(navController,checkCameraPermission) },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = colorResource(id = R.color.blue_tertiary)
    ) {
        innerPadding ->
        NavHost(navController = navController, startDestination = Screen.QrScreen.route ){
            composable(route = Screen.QrScreen.route){
                HomeScreen(innerPadding = innerPadding, viewModel = viewModel)
            }
            composable(route = Screen.HistoryScreen.route){
                HistoryScreen(innerPadding = innerPadding,viewModel = viewModel)
            }
            composable(route = Screen.GenerateScreen.route){
                GenerateScreen(innerPadding = innerPadding)
            }
        }
    }
}
