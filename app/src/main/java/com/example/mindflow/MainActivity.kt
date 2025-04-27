package com.example.mindflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay


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
    var isTextFieldEnabled by remember { mutableStateOf(false) }
    var isTimerRunning by remember {mutableStateOf(false)}
    fun onTimerStart() {
        isTextFieldEnabled = true
        isTimerRunning = true
    }

    fun onTimerPause() {
        isTimerRunning = false
        isTextFieldEnabled= false
    }

    fun onTimerReset() {
        isTimerRunning = false
        isTextFieldEnabled = false
        text= ""
    }

    Surface(color = LightBackground, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MindFlow",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top=32.dp, bottom= 32.dp)
            )
            CountdownTimer(
                totalTime = 300,
                isRunning = isTimerRunning,
                onStart = {onTimerStart()},
                onPause = {onTimerPause()},
                onReset= {onTimerReset()},
                modifier = Modifier.padding( bottom=24.dp),
            )
            TextField(
                value = text,
                onValueChange = { text = it },
                placeholder = { Text("Start writing...") },
                enabled = isTextFieldEnabled,
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

@Composable
fun CountdownTimer(
    totalTime: Int = 300,
    isRunning: Boolean,
    onStart: () -> Unit,
    onPause: () -> Unit,
    onReset: () -> Unit,
    modifier: Modifier = Modifier,

) {
    var timeLeft by remember { mutableStateOf(totalTime) }

    LaunchedEffect(key1 = isRunning, key2 = timeLeft) {
        if (isRunning && timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = formattedTime,
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { onStart()},
                enabled = !isRunning // Disable Start if already running
            ) {
                Text("Start")
            }
            Button(
                onClick = { onPause() },
                enabled = isRunning // Disable Pause if already paused
            ) {
                Text("Pause")
            }
            Button(
                onClick = {
                    onReset()
                    timeLeft = totalTime
                }
            ) {
                Text("Reset")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MindFlowScreenPreview(){
    MindFlowScreen()
}