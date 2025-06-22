package com.robosoft.androidtv.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.Text
import coil.compose.AsyncImage

@Composable
fun TVProfileScreen(
    userName: String = "Vijay Santosh Kumar",
    userEmail: String = "vijay@example.com",
    userRole: String = "Android TV Developer",
    onEditClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    TvLazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            ProfileHeaderSection(
                name = userName,
                email = userEmail,
                role = userRole
            )
        }

        item {
            ActionButton("Edit Profile", onEditClick)
        }

        item {
            ActionButton("Log Out", onLogoutClick)
        }
    }
}

@Composable
fun ProfileHeaderSection(name: String, email: String, role: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://fastly.picsum.photos/id/1005/300/200.jpg?hmac=lHIhPWhvbWa7AZZq2s88_5BtG8__JNFOhFbp37WOMrI", // Placeholder avatar
            contentDescription = "User Avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Column {
            Text(name, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(email, fontSize = 18.sp, color = Color.LightGray)
            Text(role, fontSize = 16.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ActionButton(text: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .width(300.dp)
            .height(70.dp)
            .focusTarget(),
        shape = CardDefaults.shape(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(text, color = Color.Black, fontSize = 20.sp)
        }
    }
}