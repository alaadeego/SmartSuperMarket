package com.example.hpwin8.smartsupermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText username,email,phone,password;
    Button  SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username= (EditText) findViewById(R.id.usernameText);
        phone= (EditText) findViewById(R.id.phoneText);
        email= (EditText) findViewById(R.id.emailText);
        password= (EditText) findViewById(R.id.passwordText);


        SignUp = (Button) findViewById(R.id.signupbtn);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              signup();
            }
        });




    }



    public void signup()
    {
        //  Log.d(TAG, "Login");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        else
        {
            String namestr=username.getText().toString();
            String emailstr = email.getText().toString();
            String passwordstr = password.getText().toString();
            String phonestr = phone.getText().toString();
            MyApp mApp = ((MyApp)getApplicationContext());
            SmartmarketRestClientUsage oSmartmarketRestClientUsage=new SmartmarketRestClientUsage();
            oSmartmarketRestClientUsage.signUp(namestr,passwordstr,emailstr,phonestr
                    ,this,mApp);


        }
    }
    public boolean validate()
    {
        boolean valid = true;
        String namestr=username.getText().toString();
        String emailstr = email.getText().toString();
        String passwordstr = password.getText().toString();
        String phonestr = phone.getText().toString();
        //check email
        if(emailstr.isEmpty()) {
            email.setError("Enter a Valid Email Address");
            valid=false;
        }
        else
        {
            email.setError(null);
        }

        //check password

        if(passwordstr.isEmpty()) {
            password.setError("Enter a Valid Password");
            valid=false;
        }
        else
        {
            password.setError(null);
        }
        if(namestr.isEmpty()) {
            username.setError("Enter a Nameame");
            valid=false;
        }
        else
        {
            username.setError(null);
        }
        if(phonestr.isEmpty()) {
            phone.setError("Enter a PhoneNumber");
            valid=false;
        }
        else
        {
            phone.setError(null);
        }


        return valid;
    }

    public void  onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();

        // _loginButton.setEnabled(true);
    }



}
