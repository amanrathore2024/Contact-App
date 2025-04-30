package com.example.contact.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.contact.data.entities.Contact
import com.example.contacts.ViewModels.DataViewModel
import com.example.contacts.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreenUi(
    viewModel : DataViewModel,
    navController: NavHostController
)
{
    val liked  = remember {  mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                { Text("Add Contact" , fontSize = 24.sp) }
            )
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = if (liked.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder ,
                    contentDescription = "" ,
                    modifier = Modifier.align(Alignment.TopEnd).size(50.dp)
                                    .clickable { liked.value = !liked.value }
                                    .padding( end = 16.dp),
                    tint = if (liked.value) Color.Red else Color.Gray
                )

                Image(
                    painter = painterResource(R.drawable.ic_launcher_background),
                    modifier = Modifier.size(200.dp).clip(CircleShape).align(Alignment.Center),
                    contentDescription = ""
                )
            }


            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.Fname.value,
                onValueChange = { viewModel.Fname.value = it},
                placeholder = { Text("First name") },
                label = { Text("First Name") }
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.lname.value,
                onValueChange = { viewModel.lname.value = it},
                placeholder = { Text("Last name") },
                label = { Text("last Name") }
            )
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.phoneNo.value,
                onValueChange = { viewModel.phoneNo.value = it},
                placeholder = { Text("Phone no") },
                label = { Text("Phone no") }
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.email.value = it},
                placeholder = { Text("Email") },
                label = { Text("Email") }
            )

            Spacer(Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    viewModel.upsertContact(
                        Contact(
                            id = viewModel.id.value,
                            Fname = viewModel.Fname.value,
                            Lname = viewModel.lname.value,
                            PhoneNo = viewModel.phoneNo.value,
                            email = viewModel.email.value
                     )
                    )

                    navController.popBackStack()

                    viewModel.Fname.value = ""
                    viewModel.lname.value = ""
                    viewModel.phoneNo.value = ""
                    viewModel.id.value = 0
                    viewModel.email.value = ""

                },
            ) {
                Text("Save")
            }

        }
    }

}

//@Preview(showBackground = true , showSystemUi = true)
//@Composable
//fun AddEditPreview(){
//    AddEditScreenUi()
//}