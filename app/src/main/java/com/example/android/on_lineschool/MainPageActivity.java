package com.example.android.on_lineschool;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAUTH = FirebaseAuth.getInstance();
    DatabaseReference myRef = database.getReference("users");
    //List<users> lista;
    //users usuario;
    String user_uid;
    String emailreg;
    ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        user_uid = getIntent().getStringExtra("uid");
        emailreg = getIntent().getStringExtra("Email");

       //logout
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                      //  .setAction("Action", null).show();
                firebaseAUTH.signOut();
                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent i = new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        NoticiasFragment noticiasFragment= new NoticiasFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_main_page, noticiasFragment).commit();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot d:
                     dataSnapshot.getChildren()) {
                    if(user_uid.contentEquals(d.getKey())){

                        users u = d.getValue(users.class);

                        TextView email = (TextView) findViewById(R.id.emailprofile);
                        email.setText(emailreg);

                        TextView name = (TextView) findViewById(R.id.tituloprofile);
                        name.setText(u.getDisplay_name());

                        pic = (ImageView) findViewById(R.id.userprofilepic);

                        Picasso.with(getApplicationContext())
                                .load(u.getPic())
                                .placeholder(R.drawable.geral)   // optional
                                .error(R.drawable.erropic)      // optional
                                .resize(100,100)                        // optional
                                .into(pic);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Read database", "Failed to read value.", error.toException());
            }
        });


    }

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }

        }else{
            getFragmentManager().popBackStack();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_noticias) {
            NoticiasFragment noticiasFragment= new NoticiasFragment();
            String backStateName = noticiasFragment.getClass().getName();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(
                    R.id.content_main_page,
                    noticiasFragment,
                    noticiasFragment.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
        } else if (id == R.id.nav_horarios) {
            HorariosFragment horariosFragment= new HorariosFragment();
            String backStateName = horariosFragment.getClass().getName();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(
                    R.id.content_main_page,
                    horariosFragment,
                    horariosFragment.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
        } else if (id == R.id.nav_calendario) {
            CalendarioFragment calendarioFragment= new CalendarioFragment();
            String backStateName = calendarioFragment.getClass().getName();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()
                    .replace(
                    R.id.content_main_page,
                    calendarioFragment,
                    calendarioFragment.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
        } else if (id == R.id.nav_avaliacoes) {
            AvaliacoesFragment avaliacoesFragment= new AvaliacoesFragment();
            String backStateName = avaliacoesFragment.getClass().getName();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()

                    .replace(
                    R.id.content_main_page,
                    avaliacoesFragment,
                    avaliacoesFragment.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
        } else if (id == R.id.nav_sobre) {
            SobreFragment sobreFragment= new SobreFragment();
            String backStateName = sobreFragment.getClass().getName();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction()

                    .replace(
                    R.id.content_main_page,
                    sobreFragment,
                    sobreFragment.getTag()
            )
                    .addToBackStack(backStateName)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
