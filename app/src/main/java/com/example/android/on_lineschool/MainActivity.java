package com.example.android.on_lineschool;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText leditTextEmail;
    private EditText leditTextPassword;
    private FirebaseAuth firebaseAuth;
    public static String PREFS_NAME="mypre";
    public static String PREF_USERNAME="username";
    public static String PREF_PASSWORD="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        leditTextEmail = (EditText) findViewById(R.id.leditTextEmail);
        leditTextPassword = (EditText) findViewById(R.id.leditTextPassword);
        firebaseAuth = firebaseAuth.getInstance();
    }

    public void onStart(){
        super.onStart();
        //read username and password from SharedPref
        getUser();
    }

    public void bLogin_click (View v) {

        String username="myusername";
        String password="mypassword";



        final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Por favor aguarde...", "A processar...", true);

        (firebaseAuth.signInWithEmailAndPassword(leditTextEmail.getText().toString(),leditTextPassword.getText().toString())).addOnCompleteListener((new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login realizado com sucesso!", Toast.LENGTH_LONG).show();

                            CheckBox ch = (CheckBox) findViewById(R.id.ch_rememberme);
                            String username = leditTextEmail.getText().toString();
                            String password = leditTextPassword.getText().toString();
                            if(ch.isChecked()) rememberMe(username,password);

                            Intent i = new Intent(MainActivity.this, MainPageActivity.class);
                            i.putExtra ("Email", firebaseAuth.getCurrentUser().getEmail());
                            i.putExtra("uid",firebaseAuth.getCurrentUser().getUid());
                            startActivity(i);
                            finish(); // finish é so se quiseres fechar a atividade que estas a sair
                        } else {

                            Toast.makeText(MainActivity.this, "Um dos dados encontra se incorrecto por favor tente novamente.", Toast.LENGTH_LONG).show();

                        }
                    }
                }
                )
        );

    }

    public void getUser(){
        SharedPreferences pref = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
            String password = pref.getString(PREF_PASSWORD, null);

            if (username != null || password != null) {
                Intent i = new Intent(MainActivity.this, MainPageActivity.class);
                i.putExtra ("Email", firebaseAuth.getCurrentUser().getEmail());
                i.putExtra("uid",firebaseAuth.getCurrentUser().getUid());
                startActivity(i);
                finish();
        }

    }

    public void rememberMe (String username,String password){
        getSharedPreferences(PREFS_NAME,MODE_PRIVATE)
        .edit()
        .putString(PREF_USERNAME,username)
        .putString(PREF_PASSWORD,password)
        .commit();
    }
}
