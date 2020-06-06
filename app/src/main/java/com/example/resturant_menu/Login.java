package com.example.resturant_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText Edtemail = (EditText) findViewById(R.id.login_et_emails);
        final EditText Edtpassword = (EditText) findViewById(R.id.login_et_passwords);
        final Button btnlogin = (Button) findViewById(R.id.login_btn_login);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        final SharedPreferences sharedPreferences = getSharedPreferences("My Prefs",MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name" ,"");
        String password = sharedPreferences.getString("password" ,"");



        if(user_name != "" && password != ""){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);

        }
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked() == true){
                    String user = Edtemail.getText().toString();
                    String pass= Edtpassword.getText().toString();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_name",user);
                    editor.putString("password",pass);
                    editor.commit();
                    Toast.makeText(getBaseContext(), "Wellcome "+user, Toast.LENGTH_SHORT).show();
                    Intent j = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(j);
                    finish();

                }
                else{
                    Toast.makeText(getBaseContext(), "No Data to show", Toast.LENGTH_SHORT).show();
                }




            }
        });


    }
}