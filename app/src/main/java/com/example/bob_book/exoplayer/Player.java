package com.example.bob_book.exoplayer;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.FrameworkSampleSource;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.TrackRenderer;

/**
 * Created by bob-book on 10/9/2017.
 */

public class Player {
    static ExoPlayer exoPlayer;
    static TrackRenderer audioRenderer;

    public static void start(String URL, MainActivity.MyTask context){
        //обьявляем URI со ссылкой на наш стрим, либо любой аудио файл в сети
        Uri URI= Uri.parse(URL);
        FrameworkSampleSource sampleSource=new FrameworkSampleSource(context,URI,null,2);
        audioRenderer=new MediaCodecAudioTrackRenderer(sampleSource,null,true);

        //инициализируем плеер
        exoPlayer=ExoPlayer.Factory.newInstance(1);
        exoPlayer.prepare(audioRenderer);

        //говорим ему начать проигрывать аудио, как только будет окончена буферизация
        exoPlayer.setPlayWhenReady(true);

        //добавляем listener для того, что бы знать когда он начал играть
        exoPlayer.addListener(new ExoPlayer.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                //playback 4 означает готовность и начало проигрывания,  здесь можно убрать диалог загрузки
                if(playbackState == 4){
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!НАЧИНАЕМ ПРОИГРЫВАТЬ ПОТОК!!!!!!!!!!!!!!");
                }
            }

            @Override
            public void onPlayWhenReadyCommitted() {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                System.out.println("!!!!!!!!!!!!!!ОШИБКА!!!!!!!!!!!");
            }

        });
    }

    // Функция для остановки проигрывания, после которой нужно будет вызвать start()
    public static void stop(){
        if(exoPlayer!=null){
            exoPlayer.stop();
        }
    }

    // Установка громкости также присутствует и принимает значения от 0.0 до 1.0
    public static void setVolume(float volume) {
        if (exoPlayer != null) {
            exoPlayer.sendMessage(audioRenderer, MediaCodecAudioTrackRenderer.MSG_SET_VOLUME, volume);
        }
    }
}
