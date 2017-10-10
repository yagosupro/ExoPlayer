package com.example.bob_book.exoplayer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by bob-book on 10/9/2017.
 */

public class RadioService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
