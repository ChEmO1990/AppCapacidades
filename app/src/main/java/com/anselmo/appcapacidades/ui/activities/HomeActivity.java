package com.anselmo.appcapacidades.ui.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anselmo.appcapacidades.R;
import com.anselmo.appcapacidades.adapters.DisabilityUserAdapter;
import com.anselmo.appcapacidades.db.Querys;
import com.anselmo.appcapacidades.models.DisabilityUser;
import com.anselmo.appcapacidades.ui.widget.DividerItemDecoration;
import com.anselmo.appcapacidades.utils.Constants;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.toolbar_actionbar)
    Toolbar toolbar;

    @Bind(R.id.lstLista)
    RecyclerView lstLista;

    @Bind(R.id.ctlLayout)
    CollapsingToolbarLayout ctlLayout;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.btnFab)
    FloatingActionButton btnFab;

    private ArrayList<DisabilityUser> items;
    private DisabilityUserAdapter adapter;

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        items = new ArrayList<>();

        adapter = new DisabilityUserAdapter(this, items);
        lstLista.setAdapter(adapter);

        lstLista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lstLista.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        lstLista.setItemAnimator(new DefaultItemAnimator());

        //Floating Action Button
        btnFab = (FloatingActionButton)findViewById(R.id.btnFab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SplashActivity.class);
                intent.putExtra(Constants.NEW_USER_RECORD, true);
                startActivity(intent);
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("DisabilityUser");
        query.whereEqualTo("id_user", Querys.getUUID(this));
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {
                    name = scoreList.get(0).getString("name");
                    ctlLayout.setTitle(name);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        loadMyProfiles();
    }

    private void loadMyProfiles() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("DisabilityUser");
        query.whereEqualTo("id_user_father", Querys.getUUID(HomeActivity.this));
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> scoreList, ParseException e) {
                if (e == null) {

                    try {
                        for (int i = 0; i < scoreList.size(); i++) {
                            String address = scoreList.get(i).getString("address");
                            String cellphone = scoreList.get(i).getString("cellphone");
                            String count_family = scoreList.get(i).getString("count_family");
                            String date_birthday = scoreList.get(i).getString("date_birthday");
                            String email = scoreList.get(i).getString("email");
                            String father_lastname = scoreList.get(i).getString("father_lastname");
                            String gender = scoreList.get(i).getString("gender");
                            String id_user = scoreList.get(i).getString("id_user");
                            String id_user_father = scoreList.get(i).getString("id_user_father");
                            String level_disability = scoreList.get(i).getString("level_disability");
                            String level_study = scoreList.get(i).getString("level_study");
                            String mother_lastname = scoreList.get(i).getString("mother_lastname");
                            String municipality = scoreList.get(i).getString("municipality");
                            String name = scoreList.get(i).getString("name");
                            String neighborhood = scoreList.get(i).getString("neighborhood");
                            String phone = scoreList.get(i).getString("phone");
                            String type_disability = scoreList.get(i).getString("type_disability");
                            String objectId = scoreList.get(i).getString("objectId");

                            //Add Item
                            items.add( new DisabilityUser(address,
                                    cellphone,
                                    count_family,
                                    date_birthday,
                                    email,
                                    father_lastname,
                                    gender,
                                    id_user,
                                    id_user_father,
                                    level_disability,
                                    level_study,mother_lastname,
                                    municipality,
                                    name,
                                    neighborhood,
                                    phone,
                                    type_disability,
                                    objectId));
                        }
                        adapter.notifyDataSetChanged();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }

    private void loadAllUsers() {
        ParseQuery query = ParseQuery.getQuery("DisabilityUser");

        try {
            List<ParseObject> parseItems = query.find();

            for (int i = 0; i < parseItems.size(); i++) {
                String address = parseItems.get(i).getString("address");
                String cellphone = parseItems.get(i).getString("cellphone");
                String count_family = parseItems.get(i).getString("count_family");
                String date_birthday = parseItems.get(i).getString("date_birthday");
                String email = parseItems.get(i).getString("email");
                String father_lastname = parseItems.get(i).getString("father_lastname");
                String gender = parseItems.get(i).getString("gender");
                String id_user = parseItems.get(i).getString("id_user");
                String id_user_father = parseItems.get(i).getString("id_user_father");
                String level_disability = parseItems.get(i).getString("level_disability");
                String level_study = parseItems.get(i).getString("level_study");
                String mother_lastname = parseItems.get(i).getString("mother_lastname");
                String municipality = parseItems.get(i).getString("municipality");
                String name = parseItems.get(i).getString("name");
                String neighborhood = parseItems.get(i).getString("neighborhood");
                String phone = parseItems.get(i).getString("phone");
                String type_disability = parseItems.get(i).getString("type_disability");
                String objectId = parseItems.get(i).getString("objectId");

                //Add Item
                items.add( new DisabilityUser(address,
                        cellphone,
                        count_family,
                        date_birthday,
                        email,
                        father_lastname,
                        gender,
                        id_user,
                        id_user_father,
                        level_disability,
                        level_study,mother_lastname,
                        municipality,
                        name,
                        neighborhood,
                        phone,
                        type_disability,
                        objectId));
            }
            adapter.notifyDataSetChanged();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_view_profile) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
