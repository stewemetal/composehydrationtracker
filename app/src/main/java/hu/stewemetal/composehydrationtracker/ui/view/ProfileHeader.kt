package hu.stewemetal.composehydrationtracker.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hu.stewemetal.composehydrationtracker.ui.theme.HydrationTrackerTheme

@Composable
fun ProfileHeader(
    username: String,
    dailyIntakeGoal: Int,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Profile picture",
                tint = Color.White,
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primary,
                        shape = CircleShape,
                    )
                    .padding(8.dp)
                    .size(48.dp)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(username)
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 8.dp)
        ) {
            Text("Daily intake goal: $dailyIntakeGoal")
            Icon(
                Icons.Default.Edit,
                contentDescription = "Edit daily intake goal",
                modifier = Modifier
                    .size(22.dp)
                    .padding(start = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    HydrationTrackerTheme {
        ProfileHeader(
            username = "Test user",
            dailyIntakeGoal = 2500,
        )
    }
}