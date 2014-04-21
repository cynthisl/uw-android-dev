package com.cynthisl.Homework_251;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
            /**
             * When clicked, validate the inputs and launch next activity
             * @param view
             */
            @Override
            public void onClick(View view) {
                if(validateInputs()){
                    launchAuthorizedActivity();
                }
            }
        });

    }

    /**
     * make sure both fields are full and email is valid
     */
    private boolean validateInputs(){
        if(emailInput.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Please input email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(Patterns.EMAIL_ADDRESS.matcher(emailInput.getText().toString()).matches() == false){
            Toast.makeText(getApplicationContext(), "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(passwordInput.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "Please input password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
    /**
     * successful sign in, launch next
     */
    private void launchAuthorizedActivity(){
        Intent i = new Intent(this, Authorized.class);
        i.putExtra("email", emailInput.getText().toString());
        i.putExtra("password", passwordInput.getText().toString());
        startActivity(i);
    }
}
