package co.uk.thewirelessguy.falconninelaunches.ui.component

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest

/**
 * A composable that executes an ImageRequest asynchronously and renders the result
 */
@Composable
fun CustomImage(
    modifier: Modifier = Modifier,
    context: Context,
    image: String,
    contentDescription: String? = "",
    contentScale: ContentScale = ContentScale.Crop
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(context)
            .data(image)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription,
        contentScale = contentScale,
    )
}