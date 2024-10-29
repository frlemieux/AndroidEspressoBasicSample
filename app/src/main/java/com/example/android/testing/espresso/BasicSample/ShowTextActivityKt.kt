package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.testing.espresso.BasicSample.ShowTextActivityKt.Companion.KEY_EXTRA_MESSAGE
import com.lemieux.tasks.ui.theme.AppTheme

class ShowTextActivityKt : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Scaffold { innerPaddings ->
                    ScreenB(
                        modifier = Modifier.padding(innerPaddings)
                    )
                }

            }
        }
    }

    companion object {
        const val KEY_EXTRA_MESSAGE: String = "extra_message"

        fun newStartIntent(context: Context, text: String): Intent {
            val newIntent = Intent(context, ShowTextActivityKt::class.java)
            newIntent.putExtra(KEY_EXTRA_MESSAGE, text)
            return newIntent
        }
    }

}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

@Composable
fun ScreenB(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val intent = activity?.intent
    val text = intent?.getStringExtra(KEY_EXTRA_MESSAGE) ?: "No text provided"
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            modifier = Modifier.testTag("tag_text_screenB")
        )
    }
}

@Preview
@Composable
private fun PreviewScreenB() {
    ScreenB()
}