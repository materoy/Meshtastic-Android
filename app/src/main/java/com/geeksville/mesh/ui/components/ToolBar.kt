package com.geeksville.mesh.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geeksville.mesh.R
import com.geeksville.mesh.ui.theme.AppTheme


@Composable
fun AppToolBar(modifier: Modifier = Modifier) {
    val contentColor = MaterialTheme.colors.let {
        return@let if (it.isLight) it.onPrimary else it.primary
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.let {
                return@let if (it.isLight) it.primary else it.background
            })
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.app_icon),
            contentDescription = stringResource(R.string.application_icon),
            modifier = modifier.size(36.dp),
            tint = contentColor,
        )
        Text(
            text = stringResource(R.string.app_name),
            modifier = modifier.padding(start = 12.dp),
            style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp, color = contentColor),
        )
        Spacer(modifier = Modifier.weight(1f))
        // TODO: Change this to show according to the connection status
        Icon(
            imageVector = Icons.Outlined.CloudDone,
            contentDescription = stringResource(R.string.connected),
            modifier = modifier.padding(end = 8.dp),
            tint = contentColor,
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = stringResource(R.string.more_details),
                modifier = modifier,
                tint = contentColor,
            )
        }
    }
}


@Preview
@Composable
private fun AppToolBarPreview() {
    AppTheme { AppToolBar() }
}

@Preview
@Composable
private fun AppToolBarPreviewDark() {
    AppTheme(darkTheme = true) { AppToolBar() }
}
