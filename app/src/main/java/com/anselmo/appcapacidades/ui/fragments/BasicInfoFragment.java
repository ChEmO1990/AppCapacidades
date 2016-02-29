package com.anselmo.appcapacidades.ui.fragments;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.models.BasicInfoEvent;
import com.anselmo.appcapacidades.utils.Constants;
import com.github.florent37.materialtextfield.MaterialTextField;
import com.pixplicity.easyprefs.library.Prefs;
import com.vstechlab.easyfonts.EasyFonts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.ganfra.materialspinner.MaterialSpinner;


/**
 * Created by anselmo on 1/26/16.
 */
public class BasicInfoFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    @Bind(R.id.name)
    MaterialTextField edit_name;

    @Bind(R.id.name_father)
    MaterialTextField edit_paterno;

    @Bind(R.id.name_mother)
    MaterialTextField edit_materno;

    @Bind(R.id.date_birthday)
    MaterialTextField edit_birthday;

    @Bind(R.id.spinner)
    Spinner spinner;

    @Bind(R.id.lbl_title_info_basicinfo)
    TextView lbl_info;

    private Calendar calendar;
    private DateFormat dateFormat;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basic_info_fragment, container, false);
        ButterKnife.bind(this, view);

        edit_name.expand();
        edit_materno.expand();
        edit_paterno.expand();
        edit_birthday.expand();

        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());


        String[] ITEMS = getResources().getStringArray(R.array.array_gender);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, ITEMS);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set title fragment
        lbl_info.setText(getString(R.string.info_basic));
        lbl_info.setTypeface(EasyFonts.robotoBold(getActivity()));

        edit_birthday.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.newInstance(BasicInfoFragment.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
            }
        });

        return view;
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        edit_birthday.getEditText().setText( dateFormat.format(calendar.getTime()) );
    }

    @Subscribe
    public void saveCurrentBasicInfo( BasicInfoEvent event ) {
        if( event != null ) {
            Prefs.putString(Constants.NAME_PREF, edit_name.getEditText().getText().toString());
            Prefs.putString(Constants.FATHER_LASTNAME_PREF, edit_paterno.getEditText().getText().toString());
            Prefs.putString(Constants.MOTHER_LASTNAME_PREF, edit_materno.getEditText().getText().toString());
            Prefs.putString(Constants.BIRTHDAY_PREF, edit_birthday.getEditText().getText().toString());
            Prefs.putString(Constants.GENDER_PREF, spinner.getSelectedItem().toString());
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
