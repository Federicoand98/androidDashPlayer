package Player;

import android.annotation.SuppressLint;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;

import java.net.URI;
import java.net.URL;

public class PlayerActivity extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    PlayerView exoPlayerView;
    String videoURL = "http://www.bok.net/dash/tears_of_steel/cleartext/stream.mpd";
    String video = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        exoPlayerView = findViewById(R.id.idExoPlayerVIew);
/*
        try {

            DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();
            //MediaSource mediaSource = new DashMediaSource.Factory(dataSourceFactory).createMediaSource(MediaItem.fromUri(Uri.parse(video)));
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(videoURL), dataSourceFactory, extractorsFactory, null, null);

            TrackSelection.Factory adaptiveTrackSelection = new AdaptiveTrackSelection.Factory();
            TrackSelector trackSelector = new DefaultTrackSelector(adaptiveTrackSelection);

            DefaultLoadControl loadControl = new DefaultLoadControl.Builder().setBufferDurationsMs(64 * 1024, 128 * 1024, 1024, 1024).createDefaultLoadControl();

            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            exoPlayerView.setPlayer(exoPlayer);

            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
 */
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
        if ((Util.SDK_INT <= 23 || exoPlayer == null)) {
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
        if(exoPlayer == null) {
            DefaultTrackSelector trackSelector = new DefaultTrackSelector(this);
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxVideoSizeSd());

            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

            MediaItem mediaItem = new MediaItem.Builder().setUri(Uri.parse(videoURL)).setMimeType(MimeTypes.APPLICATION_MPD).build();
            exoPlayer.setMediaItem(mediaItem);
            exoPlayer.prepare();
            exoPlayer.setPlayWhenReady(true);
        }
    }

    private void hideSystemUi() {
        exoPlayerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void releasePlayer() {
        exoPlayer.release();
    }
}

