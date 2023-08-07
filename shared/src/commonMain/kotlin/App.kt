import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

const val TAB_WIDTH = 4

@Composable
fun App() {
    var text by remember { mutableStateOf(
        buildString {
            appendLine("`\t` tab")
            appendLine("` ` space")
        }
    ) }

    TextField(text, { text = it }, visualTransformation = VisualTransformation { original ->
        val builder = StringBuilder()

        for (offset in 0 until original.text.length) {
            val char = original.text[offset]

            if (char == '\t') {
                for (i in 0 until TAB_WIDTH) {
                    builder.append(' ')
                }
            } else {
                builder.append(char)
            }
        }

        TransformedText(AnnotatedString(builder.toString()), OffsetMapping.Identity)
    })
}
