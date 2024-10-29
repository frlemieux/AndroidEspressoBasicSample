package com.example.android.testing.espresso.BasicSample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.startActivity
import com.lemieux.tasks.ui.theme.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold { innerPaddings ->
                    Screen(modifier = Modifier.padding(innerPaddings))
                }
            }
        }
    }
}


@Composable
fun Screen(
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }
    var title by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Text(
            modifier = Modifier.testTag("tag_hello_world"),
            text = title
        )
        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.testTag("tag_text_field")
        )
        Button(onClick = {
            title = text
        },
            modifier = Modifier.testTag("tag_change_text")) {
            Text(text = stringResource(R.string.change_text))
        }
        Button(onClick = {
                val intent = ShowTextActivity
                    .newStartIntent(context, text)
                startActivity(context, intent, null)
        },
            modifier= Modifier.testTag("tag_open_activity_and_change_text")) {
            Text(text = stringResource(R.string.open_activity_and_change_text))
        }
    }
}

@Preview
@Composable
private fun Preview() {
    Screen()
}