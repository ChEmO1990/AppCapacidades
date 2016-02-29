package com.anselmo.appcapacidades.ui.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.models.BasicInfoEvent;
import com.anselmo.appcapacidades.models.ContactInfoEvent;
import com.anselmo.appcapacidades.utils.Constants;
import com.github.florent37.materialtextfield.MaterialTextField;
import com.pixplicity.easyprefs.library.Prefs;
import com.vstechlab.easyfonts.EasyFonts;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by anselmo on 1/26/16.
 */
public class ContactInfoFragment extends Fragment {
    @Bind(R.id.lbl_title_info_contact)
    TextView lbl_info;

    @Bind(R.id.edit_address)
    MaterialTextField editAddress;

    @Bind(R.id.edit_municipio)
    MaterialTextField editMunicipio;

    @Bind(R.id.edit_colonia)
    MaterialTextField editColonia;

    @Bind(R.id.edit_telefono)
    MaterialTextField editTelefono;

    @Bind(R.id.edit_cellphone)
    MaterialTextField editCellphone;

    @Bind(R.id.edit_email)
    MaterialTextField editEmail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_info_fragment, container, false);
        ButterKnife.bind(this, view);

        editAddress.expand();
        editMunicipio.expand();
        editColonia.expand();
        editTelefono.expand();
        editCellphone.expand();
        editEmail.expand();

        //Set title fragment
        lbl_info.setText(getString(R.string.info_contact));
        lbl_info.setTypeface(EasyFonts.robotoBold(getActivity()));

        return view;
    }

    @Subscribe
    public void saveCurrentContactInfo( ContactInfoEvent event ) {
        if( event != null ) {
            Prefs.putString(Constants.ADDRESS_PREF, editAddress.getEditText().getText().toString());
            Prefs.putString(Constants.MUNICIPALITY_PREF, editMunicipio.getEditText().getText().toString());
            Prefs.putString(Constants.NEIGHBORHOOD_PREF, editColonia.getEditText().getText().toString());
            Prefs.putString(Constants.PHONE_PREF, editTelefono.getEditText().getText().toString());
            Prefs.putString(Constants.CELLPHONE_PREF, editCellphone.getEditText().getText().toString());
            Prefs.putString(Constants.EMAIL_PREF, editEmail.getEditText().getText().toString());
        }
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
