package com.anselmo.appcapacidades.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.models.ContactInfoEvent;
import com.anselmo.appcapacidades.models.DisabilityInfoEvent;
import com.anselmo.appcapacidades.utils.Constants;
import com.pixplicity.easyprefs.library.Prefs;
import com.vstechlab.easyfonts.EasyFonts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anselmo on 1/26/16.
 */
public class DisabilityInfoFragment extends Fragment {
    @Bind(R.id.lbl_title_info_disability)
    TextView lbl_info;

    @Bind(R.id.spinnerStudy)
    Spinner spinner0;

    @Bind(R.id.spinnerTypeDisability)
    Spinner spinner1;

    @Bind(R.id.spinnerLevelDisbility)
    Spinner spinner2;

    @Bind(R.id.spinnerFamiliy)
    Spinner spinner3;


    /*
    +NIVEL TERMINADO : PRIMARIA, SECUNDARIA, BACHILLERATO, LICENCIATURA, OTRO

    +TIPO DE DISCAPACIDAD
    MOTRIZ, VISIUAL, AUDITIVO, NEUROLOGICA

    +GRADO DE LA DISCAPACIDAD
    LEVE, MODERADO, SEVERO

    +NUMERO DE MIEMBROS DE SU FAMILIA
    1, 2 ,3 , 4, 5, 6,
     */

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disability_info_fragment, container, false);
        ButterKnife.bind(this, view);

        //Set title fragment
        lbl_info.setText(getString(R.string.info_disability));
        lbl_info.setTypeface(EasyFonts.robotoBold(getActivity()));

        //Init spinners
        String[] ITEMS_STUDY = getResources().getStringArray(R.array.level_study);
        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS_STUDY);
        adapter0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner0.setAdapter(adapter0);

        String[] ITEMS_TYPE_DISABILITY = getResources().getStringArray(R.array.type_disability);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS_TYPE_DISABILITY);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        String[] ITEMS_LEVEL_DISABILITY = getResources().getStringArray(R.array.level_disability);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS_LEVEL_DISABILITY);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        String[] ITEMS_LEVEL_FAMILY = getResources().getStringArray(R.array.level_familiy);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS_LEVEL_FAMILY);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        return view;
    }

    @Subscribe
    public void saveCurrentDisabilityInfo( DisabilityInfoEvent event ) {
        Prefs.putString(Constants.LEVEL_STUDY_PREF, spinner0.getSelectedItem().toString());
        Prefs.putString(Constants.TYPE_DISABILITY_PREF, spinner1.getSelectedItem().toString());
        Prefs.putString(Constants.LEVEL_DISABILITY_PREF, spinner2.getSelectedItem().toString());
        Prefs.putString(Constants.COUNT_FAMILY_PREF, spinner3.getSelectedItem().toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
