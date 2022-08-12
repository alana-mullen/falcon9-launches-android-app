package co.uk.thewirelessguy.falconninelaunches.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.uk.thewirelessguy.falconninelaunches.model.State
import co.uk.thewirelessguy.falconninelaunches.model.app.LaunchSummary
import co.uk.thewirelessguy.falconninelaunches.ui.component.CustomImage
import co.uk.thewirelessguy.falconninelaunches.ui.component.ErrorScreen
import co.uk.thewirelessguy.falconninelaunches.ui.component.LoadingScreen
import co.uk.thewirelessguy.falconninelaunches.ui.preview.LaunchItemProvider
import co.uk.thewirelessguy.falconninelaunches.ui.theme.listItemTextColor
import co.uk.thewirelessguy.falconninelaunches.viewmodel.LaunchesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.launch

@Composable
fun LaunchesListScreen(
    viewModel: LaunchesViewModel = hiltViewModel()
) {
    val composableScope = rememberCoroutineScope()
    val uiState = viewModel.launchesListUIState.collectAsState().value
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchLaunchesList()
    })

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            composableScope.launch {
                viewModel.fetchLaunchesList(refresh = true)
            }
        },
    ) {
        when (uiState) {
            is State.Success -> LaunchesList(items = uiState.data.toMutableList())
            is State.Error -> ErrorScreen()
            else -> LoadingScreen()
        }
    }
}

@Composable
fun LaunchesList(items: MutableList<LaunchSummary>) {
    LazyColumn {
        itemsIndexed(
            items = items,
            key = { _, item ->
                item.id
            }
        ) { index, item ->
            LaunchItem(item)
            if (index < items.lastIndex) Divider(modifier = Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun LaunchItem(item: LaunchSummary) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        CustomImage(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            context = context,
            image = item.image
        )

        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = item.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                color = listItemTextColor,
                style = MaterialTheme.typography.h6
            )
            AnimatedVisibility(visible = item.launchDate.isNotEmpty()) {
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    text = "Launch Date: " + item.launchDate,
                    overflow = TextOverflow.Ellipsis,
                    color = listItemTextColor,
                    style = MaterialTheme.typography.body1
                )
            }
            AnimatedVisibility(visible = item.missionSuccess != null) {
                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()) {
                    Text(
                        text = "Mission Success:",
                        overflow = TextOverflow.Ellipsis,
                        color = listItemTextColor,
                        style = MaterialTheme.typography.body1
                    )
                    Icon(
                        modifier = Modifier.padding(start = 4.dp),
                        imageVector =
                            if (item.missionSuccess == true) Icons.Default.Check
                            else Icons.Default.Close,
                        contentDescription = item.missionSuccess.toString(),
                        tint =
                            if (item.missionSuccess == true) Color.Green
                            else Color.Red
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    group = "LaunchItem",
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun LaunchItemLightModePreview(
    @PreviewParameter(LaunchItemProvider::class) launchSummary: LaunchSummary
) {
    LaunchItem(item = launchSummary)
}

@Preview(
    showBackground = false,
    group = "LaunchItem",
    uiMode = UI_MODE_NIGHT_YES,
)
@Composable
fun LaunchItemDarkModePreview(
    @PreviewParameter(LaunchItemProvider::class) launchSummary: LaunchSummary
) {
    LaunchItem(item = launchSummary)
}