package com.example.bob_book.exoplayer;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


/**
 * Created by bob-book on 10/9/2017.
 */

public class RadioService extends Service {
    private SimpleExoPlayer player;
    private BandwidthMeter bandwidthMeter;
    private ExtractorsFactory extractorsFactory;
    private TrackSelection.Factory trackSelectionFactory;
    private TrackSelector trackSelector;
    private DefaultBandwidthMeter defaultBandwidthMeter;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource;
    private String URL="http://relay4.181.fm:8128";
    private String URL2="http://http-live.sr.se/p3-mp3-192";




    public void onCreate() {
        super.onCreate();

        bandwidthMeter = new DefaultBandwidthMeter();
        extractorsFactory = new DefaultExtractorsFactory();

        trackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

        trackSelector = new DefaultTrackSelector(trackSelectionFactory);

/*        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "mediaPlayerSample"),
                (TransferListener<? super DataSource>) bandwidthMeter);*/

        defaultBandwidthMeter = new DefaultBandwidthMeter();
        dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, "mediaPlayerSample"), defaultBandwidthMeter);

//        mediaSource = new ExtractorMediaSource(Uri.parse(URL), dataSourceFactory, extractorsFactory, null, null);
//
//        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//
//
//        player.prepare(mediaSource);
   }

  public int onStartCommand(Intent intent, int flags, int startId) {


     URL2=intent.getStringExtra("radioURL2");

     System.out.println(URL);
      System.out.println(URL2);

      mediaSource = new ExtractorMediaSource(Uri.parse(URL2), dataSourceFactory, extractorsFactory, null, null);
      player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
      player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        player.setPlayWhenReady(false);
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
