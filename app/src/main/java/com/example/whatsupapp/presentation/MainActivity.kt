package com.example.whatsupapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.whatsupapp.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsAppMainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WhatsAppMainScreen() {
    var selectedTab by remember { mutableStateOf(BottomTab.Chats) }
    var menuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("WhatsUpApp")
                },
                actions = {
                    IconButton(onClick = { /* Search clicked */ }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /* Scanner clicked */ }) {
                        Icon(
                            painter = painterResource(R.drawable.outline_document_scanner_24),
                            contentDescription = "Scanner"
                        )
                    }
                    IconButton(onClick = { /* Camera clicked */ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                            contentDescription = "Camera"
                        )
                    }
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(text = { Text("New group") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("New community") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("New broadcast") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("Linked devices") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("Starred") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("Payments") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("Read all") }, onClick = { /* TODO */ })
                        DropdownMenuItem(text = { Text("Settings") }, onClick = { /* TODO */ })
                    }
                }
            )
        },

        bottomBar = {
            NavigationBar {
                BottomTab.values().forEach { tab ->
                    NavigationBarItem(
                        icon = {
                            Icon (
                                painter = painterResource(id = tab.icon),
                                contentDescription = tab.label
                            ) },
                        label = { Text(tab.label) },
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab }
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTab) {
                BottomTab.Chats -> Text("Chats")
                BottomTab.Updates -> Text("Updates")
                BottomTab.Communities -> Text("Communities")
                BottomTab.Calls -> Text("Calls")
            }
        }
    }
}

enum class BottomTab(val label: String, val icon: Int) {
    Chats("Chats", R.drawable.baseline_chat_24),
    Updates("Updates", R.drawable.baseline_whatshot_24),
    Communities("Communities", R.drawable.baseline_people_24),
    Calls("Calls", R.drawable.baseline_call_24)
}
