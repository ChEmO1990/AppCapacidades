package com.anselmo.appcapacidades.adapters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.anselmo.appcapacidades.ui.fragments.BasicInfoFragment;
import com.anselmo.appcapacidades.ui.fragments.ContactInfoFragment;
import com.anselmo.appcapacidades.ui.fragments.DisabilityInfoFragment;
import com.anselmo.appcapacidades.ui.fragments.WelcomeFragment;

/**
 * Created by anselmo on 1/26/16.
 */
public class SplashPagerAdapter extends FragmentPagerAdapter {

    public SplashPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment currentFragment = null;

        switch ( position ) {
            case 0: currentFragment = new WelcomeFragment();        break;
            case 1: currentFragment = new BasicInfoFragment();      break;
            case 2: currentFragment = new ContactInfoFragment();    break;
            case 3: currentFragment = new DisabilityInfoFragment(); break;

        }

        return currentFragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}