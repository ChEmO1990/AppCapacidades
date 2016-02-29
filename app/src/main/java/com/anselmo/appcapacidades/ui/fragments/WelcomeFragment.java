package com.anselmo.appcapacidades.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.anselmo.appcapacidades.R;
import com.vstechlab.easyfonts.EasyFonts;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anselmo on 2/15/16.
 */
public class WelcomeFragment extends Fragment {
    @Bind(R.id.lbl_welcome_title)
    TextView lbl_welcome;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_fragment, container, false);
        ButterKnife.bind(this, view);

        lbl_welcome.setText("App Capacidades");
        lbl_welcome.setTypeface(EasyFonts.robotoBold(getActivity()));


        return view;
    }
}
