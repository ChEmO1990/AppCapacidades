package com.anselmo.appcapacidades.ui.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.adapters.DisabilityUserAdapter;
import com.anselmo.appcapacidades.models.DisabilityUser;
import com.anselmo.appcapacidades.ui.widget.DividerItemDecoration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {
    @Bind(R.id.toolbar_actionbar)
    Toolbar toolbar;

    @Bind(R.id.lstLista)
    RecyclerView lstLista;

    @Bind(R.id.ctlLayout)
    CollapsingToolbarLayout ctlLayout;

    @Bind(R.id.btnFab)
    FloatingActionButton btnFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        ArrayList<DisabilityUser> datos = new ArrayList<>();

        for( int i = 0; i < 20; i++ ) {
            datos.add( new DisabilityUser("Anselmo", "Hernandez", "Visual", "Leve", "15/01/1990", "Masculino"));
        }

        DisabilityUserAdapter adaptador = new DisabilityUserAdapter(this, datos);
        lstLista.setAdapter(adaptador);

        lstLista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstLista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        lstLista.setItemAnimator(new DefaultItemAnimator());

        //Floating Action Button
        btnFab = (FloatingActionButton)findViewById(R.id.btnFab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Esto es una prueba", Snackbar.LENGTH_LONG).show();
            }
        });

        //CollapsingToolbarLayout
        ctlLayout = (CollapsingToolbarLayout)findViewById(R.id.ctlLayout);
        ctlLayout.setTitle("Mi Aplicación");
    }
}
