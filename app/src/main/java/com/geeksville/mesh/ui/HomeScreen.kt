package com.geeksville.mesh.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Chat
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Contactless
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.geeksville.mesh.databinding.SettingsFragmentBinding
import com.geeksville.mesh.ui.components.AppToolBar
import com.geeksville.mesh.ui.map.MapView
import com.geeksville.mesh.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenViewModel : ViewModel() {
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab = _selectedTab.asStateFlow()

    fun onTabSelected(index: Int) {
        _selectedTab.value = index
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, homeScreenViewModel: HomeScreenViewModel = viewModel()
) {
    val selectedTabIndex by homeScreenViewModel.selectedTab.collectAsState()
    Scaffold { innerPadding ->
        Column(modifier = modifier.padding(innerPadding)) {
            AppToolBar()
            Box(modifier = Modifier.weight(1f)) {
                when (selectedTabIndex) {
                    0 -> ContactScreen()
                    1 -> NodesScreen { }
                    2 -> MapView()
                    3 -> ChannelScreen()
                    4 -> AndroidViewBinding(SettingsFragmentBinding::inflate) {

                    }
                }
            }
            AppTabRow(
                onTabSelected = { homeScreenViewModel.onTabSelected(it) },
                selectedTabIndex = selectedTabIndex
            )
        }
    }
}

data class Tabs(val title: String, val icon: ImageVector)

val tabs = listOf(
    Tabs("Messages", Icons.AutoMirrored.Rounded.Chat),
    Tabs("Users", Icons.Default.People),
    Tabs("Map", Icons.Default.Map),
    Tabs("Channel", Icons.Outlined.Contactless),
    Tabs("Settings", Icons.Default.Settings),
)

@Composable
private fun AppTabRow(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier,
    ) {
        for ((i, tab) in tabs.withIndex()) {
            val selectedTab = i == selectedTabIndex
            Tab(
                modifier = Modifier.padding(top = 16.dp, bottom = 28.dp),
                selected = selectedTab,
                onClick = { onTabSelected(i) },
            ) {
                Icon(
                    imageVector = tab.icon,
                    contentDescription = tab.title,
                    modifier = Modifier.size(32.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AppTheme { HomeScreen() }
}

@Preview
@Composable
private fun HomeScreenPreviewDark() {
    AppTheme(darkTheme = true) { HomeScreen() }
}

@Preview(device = Devices.PIXEL_C)
@Composable
private fun HomeScreenPreviewLandscape() {
    AppTheme { HomeScreen() }
}
