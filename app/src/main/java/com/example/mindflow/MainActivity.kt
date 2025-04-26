package com.example.mindflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mindflow.ui.theme.ButtonBlue
import com.example.mindflow.ui.theme.LightBackground
import com.example.mindflow.ui.theme.MindFlowTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MindFlowTheme {

                }
            }
        }
    }

@Composable
fun MindFlowScreen() {
    var text by remember { mutableStateOf("") }
    Surface(color = LightBackground, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MindFlow",
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "5:00",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Start writing...") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 24.dp)
            )

            ElevatedButton(
                onClick = { /* TODO */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonBlue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text("Start Writing")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MindFlowScreenPreview(){
    MindFlowScreen()
}