package com.anselmo.appcapacidades.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.AlertDialogWrapper;
import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.adapters.SplashPagerAdapter;
import com.anselmo.appcapacidades.db.Querys;
import com.anselmo.appcapacidades.models.BasicInfoEvent;
import com.anselmo.appcapacidades.models.ContactInfoEvent;
import com.anselmo.appcapacidades.models.DisabilityInfoEvent;
import com.anselmo.appcapacidades.utils.Constants;
import com.anselmo.appcapacidades.utils.FadePageTransformerUtil;
import com.anselmo.appcapacidades.utils.SettingsPreferences;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.pixplicity.easyprefs.library.Prefs;
import com.vstechlab.easyfonts.EasyFonts;

import org.greenrobot.eventbus.EventBus;

import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.container)
    ViewPager viewPager;

    @Bind(R.id.image_button_next)
    ImageButton imgBtnNext;

    @Bind(R.id.image_button_abort)
    ImageButton imgAbort;

    private SplashPagerAdapter mSplashPagerAdapter;

    private boolean isNewRecord = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isNewRecord = extras.getBoolean(Constants.NEW_USER_RECORD);
        }

        if( isNewRecord ) {
            imgAbort.setVisibility(ImageButton.VISIBLE);
        } else {
            imgAbort.setVisibility(ImageButton.INVISIBLE);
            runOnce();
        }

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSplashPagerAdapter = new SplashPagerAdapter(getFragmentManager());
        viewPager.setAdapter(mSplashPagerAdapter);
        viewPager.setOffscreenPageLimit(3);
        //set page transformer
        viewPager.setPageTransformer(false, new FadePageTransformerUtil());

        viewPager.addOnPageChangeListener(this);
    }

    private void runOnce() {
        if (!SettingsPreferences.isNewInstall(SplashActivity.this)) {
            runIntent(HomeActivity.class);
            finish();
        } else {
            String uuid = UUID.randomUUID().toString();
            Prefs.putString(Constants.ID_USER_PREF, uuid);
            Querys.addUUID(this, uuid);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                imgBtnNext.setImageResource(R.drawable.ic_arrow_forward_24dp);
                break;
            case 1:
                imgBtnNext.setImageResource(R.drawable.ic_arrow_forward_24dp);
                break;
            case 2:
                imgBtnNext.setImageResource(R.drawable.ic_arrow_forward_24dp);
                break;
            case 3:
                imgBtnNext.setImageResource(R.drawable.ic_done_24dp);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.image_button_next)
    public void OnNext() {
        if (viewPager.getCurrentItem() == 3) {

            EventBus.getDefault().post(new BasicInfoEvent());
            EventBus.getDefault().post(new ContactInfoEvent());
            EventBus.getDefault().post(new DisabilityInfoEvent());

            ParseObject user = new ParseObject("DisabilityUser");
            user.put(Constants.ADDRESS_PREF, Prefs.getString(Constants.ADDRESS_PREF, null));
            user.put(Constants.CELLPHONE_PREF, Prefs.getString(Constants.CELLPHONE_PREF, null));
            user.put(Constants.COUNT_FAMILY_PREF, Prefs.getString(Constants.COUNT_FAMILY_PREF, null));
            user.put(Constants.BIRTHDAY_PREF, Prefs.getString(Constants.BIRTHDAY_PREF, null));
            user.put(Constants.FATHER_LASTNAME_PREF, Prefs.getString(Constants.FATHER_LASTNAME_PREF, null));
            user.put(Constants.GENDER_PREF, Prefs.getString(Constants.GENDER_PREF, null));
            user.put(Constants.LEVEL_DISABILITY_PREF, Prefs.getString(Constants.LEVEL_DISABILITY_PREF, null));
            user.put(Constants.LEVEL_STUDY_PREF, Prefs.getString(Constants.LEVEL_STUDY_PREF, null));
            user.put(Constants.MOTHER_LASTNAME_PREF, Prefs.getString(Constants.MOTHER_LASTNAME_PREF, null));
            user.put(Constants.MUNICIPALITY_PREF, Prefs.getString(Constants.MUNICIPALITY_PREF, null));
            user.put(Constants.NAME_PREF, Prefs.getString(Constants.NAME_PREF, null));
            user.put(Constants.NEIGHBORHOOD_PREF, Prefs.getString(Constants.NEIGHBORHOOD_PREF, null));
            user.put(Constants.PHONE_PREF, Prefs.getString(Constants.PHONE_PREF, null));
            user.put(Constants.TYPE_DISABILITY_PREF, Prefs.getString(Constants.TYPE_DISABILITY_PREF, null));
            user.put(Constants.EMAIL_PREF, Prefs.getString(Constants.EMAIL_PREF, null));

            if( isNewRecord ) {
                user.put(Constants.ID_USER_PREF, UUID.randomUUID().toString());
                user.put(Constants.ID_USER_FATHER_PREF, Querys.getUUID(this));
            } else {
                user.put(Constants.ID_USER_PREF, Querys.getUUID(this));
            }

            user.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        new AlertDialogWrapper.Builder(SplashActivity.this)
                                .setTitle("¡Creación exitosa!")
                                .setMessage("El usuario ha sido creado exitosamente.")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //Save install
                                        SettingsPreferences.setNewInstall(SplashActivity.this);

                                        //Clear all preference information
                                        Prefs.clear();

                                        dialog.dismiss();

                                        Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                                        startActivity(i);

                                        finish();
                                    }
                                }).show();
                    } else {
                        e.printStackTrace();
                        new AlertDialogWrapper.Builder(SplashActivity.this)
                                .setTitle("¡Error!")
                                .setMessage("Ocurrio un error. Porfavor inténtelo de nuevo.\n")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                }
            });

        } else if (viewPager.getCurrentItem() < 3) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        }
    }

    @OnClick(R.id.image_button_abort)
    public void OnAbort() {
        finish();
    }

    private void runIntent(Class resultActivity) {
        Intent intent = new Intent(SplashActivity.this, resultActivity);
        startActivity(intent);
    }
}