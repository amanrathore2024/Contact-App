package com.example.contact.Screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.contact.Navigation.Routes
import com.example.contact.data.entities.Contact
import com.example.contacts.ViewModels.DataViewModel
import com.example.contacts.utils.PhoneCall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
//    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: DataViewModel
) {
    val appState = viewModel.state.collectAsState()
    var showMenu by remember { mutableStateOf(false) }

    when {
        appState.value.loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        appState.value.error != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${appState.value.error}")
            }
        }

        else -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                "Contacts",
                                style = MaterialTheme.typography.headlineSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        actions = {
                            IconButton(onClick = {
                                navHostController.navigate(Routes.AddEditScreen.route)
                            }) {
                                Icon(Icons.Default.Add, contentDescription = "Add Contact", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                            }

                            IconButton(onClick = { showMenu = true }) {
                                Icon(Icons.Default.MoreVert, contentDescription = "Menu", tint = MaterialTheme.colorScheme.onPrimaryContainer)
                            }

                            DropdownMenu(
                                expanded = showMenu,
                                onDismissRequest = { showMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            modifier = Modifier.padding(vertical = 4.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.primary,
                                                modifier = Modifier.size(18.dp)
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Deleted Contacts",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                        }
                                    },
                                    onClick = {
                                        showMenu = false
                                        navHostController.navigate(Routes.DeletedScreen.route)
                                    }
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    )
                }
            ) { padding ->
                val contactList = viewModel.getAllContact.collectAsState(initial = listOf())

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    items(
                        items = contactList.value,
                        key = { it.id }
                    ) { contact ->
                        ContactItem(contact, viewModel, navHostController)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContactItem(
    contact: Contact,
    viewModel: DataViewModel,
    navHostController: NavHostController
) {
    val isExtended = remember { mutableStateOf(false) }
    val expanded = isExtended.value
    val targetSize = if (expanded) 125.dp else 90.dp
    val size by animateDpAsState(targetValue = targetSize, label = "Card Size")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(size)
            .animateContentSize()
            .combinedClickable(
                onClick = { isExtended.value = !expanded }
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                            CircleShape
                        )
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "${contact.Fname} ${contact.Lname}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = contact.PhoneNo,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            AnimatedVisibility(visible = expanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoundedIconButton(
                        icon = Icons.Rounded.Call,
                        color = Color(0xFF4CAF50),
                        onClick = {
                            val phoneCall = PhoneCall(viewModel.context)
                            phoneCall.makeCall(viewModel.phoneNo.value)
                        }
                    )
                    RoundedIconButton(
                        icon = Icons.Rounded.Edit,
                        color = Color(0xFF2196F3),
                        onClick = {
                            viewModel.Fname.value = contact.Fname
                            viewModel.lname.value = contact.Lname
                            viewModel.phoneNo.value = contact.PhoneNo
                            viewModel.id.value = contact.id
                            viewModel.email.value = contact.email
                            navHostController.navigate(Routes.AddEditScreen.route)
                        }
                    )
                    RoundedIconButton(
                        icon = Icons.Rounded.Delete,
                        color = Color(0xFFF44336),
                        onClick = {
                            viewModel.delContact(contact)
                            viewModel.upsertDeletedContact(contact)
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun RoundedIconButton(
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(color)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}



