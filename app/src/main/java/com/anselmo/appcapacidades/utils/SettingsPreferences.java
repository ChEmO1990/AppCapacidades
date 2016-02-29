package com.anselmo.appcapacidades.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Kartik_ch on 12/15/2015.
 */
public class SettingsPreferences {
    private static String SHARED_SP = "DEFAULT_SP";
    private static String NEW_INSTALL = "new_install";

    public static void init(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static boolean isNewInstall(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_SP, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(NEW_INSTALL, true);
    }

    public static void setNewInstall(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_SP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NEW_INSTALL, false);
        editor.commit();
    }
}
