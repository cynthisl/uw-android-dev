package com.cynthisl.Homework_251;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Authorized extends Activity {

    TextView emailText;
    TextView passwordText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorized);
        //hide action bar
        ActionBar ab = getActionBar();
        ab.hide();

        Bundle b = getIntent().getExtras();
        String email = null;
        String pass = null;
        if (b != null) {
            email = b.getString("email");
            pass = b.getString("password");
        }

        emailText = (TextView) findViewById(R.id.emailText);
        passwordText = (TextView) findViewById(R.id.passwordText);

        emailText.setText("Email: "+email);
        passwordText.setText("Password: "+pass);

    }
}