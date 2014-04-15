package com.cynthisl.Homework_251;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignIn extends Activity {
    /**
     * Called when the activity is first created.
     */
    EditText emailInput;
    EditText passwordInput;
    Button signInButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        signInButton = (Button) findViewById(R.id.signInButton);

    }
}
