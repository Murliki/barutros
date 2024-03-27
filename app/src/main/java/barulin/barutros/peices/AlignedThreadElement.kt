package barulin.barutros.peices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlignedThreadElement(content: @Composable ColumnScope.() -> Unit){
    Column {
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
        content()
        Spacer(modifier = Modifier.padding(vertical = 4.dp))
    }
}