package com.cynthisl.Homework_251;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        setContentView(R.layout.sign_in);
        ActionBar ab = getActionBar();
        ab.hide();

        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        signInButton = (Button) findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInputs()){
                    launchAuthorizedActivity();
                }
            }
        });

    }

    private boolean validateInputs(){
        if(emailInput.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Please input email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(passwordInput.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Please input password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    /**
     * successful sign in
     */
    private void launchAuthorizedActivity(){
        Intent i = new Intent(this, Authorized.class);
        i.putExtra("email", emailInput.getText().toString());
        i.putExtra("password", passwordInput.getText().toString());
        startActivity(i);
    }
}
