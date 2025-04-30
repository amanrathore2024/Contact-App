package com.example.contact.Navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.contact.Screens.AddEditScreenUi
import com.example.contact.Screens.HomeUi
import com.example.contacts.Screens.DeletedContactsScreen

import com.example.contacts.ViewModels.DataViewModel
import com.example.contacts.ViewModels.DeletedContactViewModel

@Composable
fun AppNavigation(){

    val  viewModel: DataViewModel = hiltViewModel()
    val viewModel2: DeletedContactViewModel = hiltViewModel()
    val navcontroller = rememberNavController()

    NavHost(navController = navcontroller , startDestination = Routes.HomeScreen.route) {

        composable(Routes.HomeScreen.route){
                  HomeUi( navHostController = navcontroller, viewModel = viewModel)
        }

        composable(Routes.AddEditScreen.route){
                AddEditScreenUi(viewModel = viewModel , navController = navcontroller)
        }

        composable(Routes.DeletedScreen.route){
            DeletedContactsScreen(viewModel = viewModel2 , navHostController = navcontroller)
        }
    }
}