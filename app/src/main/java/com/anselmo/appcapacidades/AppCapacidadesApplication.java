package com.anselmo.appcapacidades;

import android.app.Application;
import android.content.ContextWrapper;

import com.anselmo.appcapacidades.utils.Constants;
import com.parse.Parse;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.UUID;

/**
 * Created by anselmo on 2/25/16.
 */
public class AppCapacidadesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        
        Parse.initialize(this, Constants.APPLICATION_ID_PARSE, Constants.CLIENT_KEY_PARSE);

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }
}
