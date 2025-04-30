package com.example.contact.Navigation

import kotlinx.serialization.Serializable

sealed class Routes (val route:String){

   object HomeScreen : Routes("home")

   object AddEditScreen : Routes("add")

   object DeletedScreen : Routes("deleted")
}