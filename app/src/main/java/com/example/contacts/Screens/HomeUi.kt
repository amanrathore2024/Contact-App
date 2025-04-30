package com.example.contact.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contact.Navigation.Routes
import com.example.contact.data.entities.Contact
import com.example.contacts.ViewModels.DataViewModel
import com.example.contacts.utils.PhoneCall


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: DataViewModel
) {

    val appState = viewModel.state.collectAsState()

    when {
        appState.value.loading -> {
            Box {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        appState.value.error != null -> {
            // Show error message
            Text(text = "Error: ${appState.value.error}")
        }
        else -> {

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Contacts") } ,
                        actions = {
                            IconButton( onClick = {
                                navHostController.navigate(Routes.AddEditScreen.route)
                            }) {
                                Icon(Icons.Default.Add , "")
                            }
                            IconButton(onClick = {
                                navHostController.navigate(Routes.DeletedScreen.route)
                            }) {
                                Icon(Icons.Default.MoreVert, "")
                            }
                        },
                    )
                }
            ) { it ->

                val contactList = viewModel.getAllContact.collectAsState(listOf())


                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(it)
                ) {
                    items( items =  contactList.value , key = { Contact -> Contact.id}){
                            contact ->
                        ContactItem(contact, viewModel , navHostController )
                    }
                }

            }

        }
    }






}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactItem(contact : Contact, viewModel: DataViewModel, navHostController: NavHostController ){

    val isExtended = remember { mutableStateOf(false) }
    val expanded = isExtended.value
    val targetSize = if (expanded) 125.dp else 80.dp
    val size by animateDpAsState(targetValue = targetSize, label = "Card Size")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .size(size)
            .combinedClickable(
                onClick = {
                    isExtended.value = !expanded
                }
//                onLongClick = {
//                    viewModel.Fname.value = contact.Fname
//                    viewModel.lname.value = contact.Lname
//                    viewModel.phoneNo.value = contact.PhoneNo
//                    viewModel.id.value = contact.id
//                    viewModel.email.value = contact.email
//                    navHostController.navigate(Routes.AddEditScreen.route)
//                }
            )
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { isExtended.value = !isExtended.value } // Toggle visibility
        ) {
                Column(modifier = Modifier.padding(start = 8.dp).fillMaxWidth()) {
                    Row {
                        Text(text = contact.Fname)
                        Spacer(Modifier.width(8.dp))
                        Text(text = contact.Lname)
                    }
                    Spacer(Modifier.height(5.dp))
                    Text(text = contact.PhoneNo)
                }

            AnimatedVisibility(visible = isExtended.value) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding( 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Call,
                        contentDescription = "Call",
                        modifier = Modifier
                            .background(
                                Color.Green.copy(alpha = 0.8f),
                                shape = CircleShape)
                            .clickable {
                               val phoneCall =  PhoneCall( viewModel.context )
                                phoneCall.makeCall(viewModel.phoneNo.value)
                            }
                    )

                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier.background(
                            Color.Green.copy(alpha = 0.8f),
                            shape = CircleShape
                        ).clickable {
                            viewModel.Fname.value = contact.Fname
                            viewModel.lname.value = contact.Lname
                            viewModel.phoneNo.value = contact.PhoneNo
                            viewModel.id.value = contact.id
                            viewModel.email.value = contact.email
                            navHostController.navigate(Routes.AddEditScreen.route)
                        }
                    )

                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier
                            .background(
                                Color.Green.copy(alpha = 0.8f),
                                shape = CircleShape)
                            .clickable {
                                viewModel.delContact(contact)
                                viewModel.upsertDeletedContact(contact)
                            }
                    )
                }
            }
        }

        }

}

