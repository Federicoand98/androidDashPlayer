package Application.Player;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;

import org.dom4j.DocumentException;

import java.io.IOException;

import Application.Network.Downloader;
import Application.Utils.TesterDownloader;

public class PlayerActivity extends AppCompatActivity {

    String videoURL = "http://www.bok.net/dash/tears_of_steel/cleartext/stream.mpd";

    private PlaybackStateListener playbackStateListener;
    private static final String TAG = PlayerActivity.class.getName();

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        playerView = findViewById(R.id.idExoPlayerVIew);

        Downloader downloader = new Downloader();
        downloader.run();

        Handler handler = new Handler();
        handler.removeCallbacks(downloader);
/*
        TesterDownloader t = new TesterDownloader("audio");
        TesterDownloader t2 = new TesterDownloader("video");
*/
        playbackStateListener = new PlaybackStateListener();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSystemUi();
        if ((Util.SDK_INT <= 23 || player == null)) {
            initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    private void initializePlayer() {
        if (player == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
            trackSelector.setParameters(
                    trackSelector.buildUponParameters().setMaxVideoSizeSd());
            player = new SimpleExoPlayer.Builder(this)
                    .setTrackSelector(trackSelector)
                    .build();
        }

        playerView.setPlayer(player);
        MediaItem mediaItem = new MediaItem.Builder()
                .setUri(getString(R.string.media_url_dash))
                .setMimeType(MimeTypes.APPLICATION_MPD)
                .build();
        player.setMediaItem(mediaItem);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.addListener(playbackStateListener);
        player.prepare();
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.removeListener(playbackStateListener);
            player.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private class PlaybackStateListener implements Player.EventListener {

        @Override
        public void onPlaybackStateChanged(int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.d(TAG, "changed state to " + stateString);
        }
    }
}

