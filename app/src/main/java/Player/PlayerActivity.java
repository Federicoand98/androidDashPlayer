package Player;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class PlayerActivity extends AppCompatActivity {

    SimpleExoPlayer exoPlayer;
    PlayerView exoPlayerView;
    String videoURL = "http://www.bok.net/dash/tears_of_steel/cleartext/stream.mpd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        exoPlayerView = findViewById(R.id.idExoPlayerVIew);

        try {
            DataSource.Factory dataSourceFactory = new DefaultHttpDataSourceFactory();
            MediaSource mediaSource =
                    new DashMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(MediaItem.fromUri(videoURL));
            SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();
            player.setMediaSource(mediaSource);
            player.prepare();
            /*
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory());
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videouri = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);

            exoPlayer.prepare(mediaSource);

            exoPlayer.setPlayWhenReady(true);
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

