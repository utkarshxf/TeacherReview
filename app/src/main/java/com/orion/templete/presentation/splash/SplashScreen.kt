package com.orion.templete.presentation.splash

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.orion.templete.Domain.use_case.Screen

private fun Context.buildExoPlayer(uri: Uri, onVideoFinished: () -> Unit) =
    ExoPlayer.Builder(this).build().apply {
        setMediaItem(MediaItem.fromUri(uri))
        repeatMode = Player.REPEAT_MODE_OFF
        playWhenReady = true
        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_ENDED) {
                    onVideoFinished.invoke()
                }
            }
        })
        prepare()
    }

private fun Context.buildPlayerView(exoPlayer: ExoPlayer) =
    StyledPlayerView(this).apply {
        player = exoPlayer
        layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        useController = false
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    }

@Composable
fun Context.SplashScreen(navController: NavHostController) {
    fun getVideoUri(): Uri {
        val rawId: Int = resources.getIdentifier("splash", "raw", packageName)
        val videoUri = "android.resource://$packageName/$rawId"
        return Uri.parse(videoUri)
    }
    PlayUri(getVideoUri() , navController)
}

@Composable
fun PlayUri(videoUri: Uri, navController: NavHostController) {
    val context = LocalContext.current
    val exoPlayer = remember {
        context.buildExoPlayer(videoUri, onVideoFinished = {
            navController.popBackStack()
            navController.navigate(Screen.LoginScreen.route)
        })
    }
    DisposableEffect(
        AndroidView(
            factory = { it.buildPlayerView(exoPlayer) },
            modifier = Modifier.fillMaxSize()
        )
    ) {
        onDispose { exoPlayer.release() }
    }
}