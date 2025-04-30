package com.example.contacts.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contacts.ViewModels.DeletedContactViewModel
import com.example.contacts.data.entities.DeletedContact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeletedContactsScreen(viewModel: DeletedContactViewModel, navHostController: NavHostController) {
    val deletedContacts by viewModel.deletedContacts.collectAsState(initial = emptyList())

    if (deletedContacts.isEmpty()) {
        EmptyStateView()
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Deleted Contacts") },
                    navigationIcon = {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                items(deletedContacts) { contact ->
                    DeletedContactItem(contact )
                }
            }
        }
    }


}



@Composable
fun DeletedContactItem(contact: DeletedContact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Name: ${contact.firstName} ${contact.lastName}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "Phone: ${contact.phoneNumber}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun DeletedContactItem(contact: DeletedContact, viewModel: DeletedContactViewModel, navHostController: NavHostController) {
//    val isExtended = remember { mutableStateOf(false) }
//    val expanded = isExtended.value
//    val targetSize = if (expanded) 125.dp else 80.dp
//    val size by animateDpAsState(targetValue = targetSize, label = "Card Size")
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .size(size)
//            .combinedClickable(
//                onClick = {
//                    isExtended.value = !expanded
//                }
//            )
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//                .clickable { isExtended.value = !isExtended.value } // Toggle visibility
//        ) {
//            Column(modifier = Modifier.padding(start = 8.dp).fillMaxWidth()) {
//                Row {
//                    Text(text = contact.firstName)
//                    Spacer(Modifier.width(8.dp))
//                    Text(text = contact.lastName)
//                }
//                Spacer(Modifier.height(5.dp))
//                Text(text = contact.phoneNumber)
//            }
//
//            AnimatedVisibility(visible = isExtended.value) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    // Restore Button
//                    Icon(
//                        imageVector = Icons.Rounded.Refresh,
//                        contentDescription = "Restore",
//                        modifier = Modifier
//                            .background(Color.Blue.copy(alpha = 0.8f), shape = CircleShape)
//                            .clickable {
//                                viewModel. // Call restore function in ViewModel
//                            }
//                    )
//                }
//            }
//        }
//    }
//}
//}
//


@Composable
fun EmptyStateView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "No deleted contacts", style = MaterialTheme.typography.headlineLarge)
    }

}

