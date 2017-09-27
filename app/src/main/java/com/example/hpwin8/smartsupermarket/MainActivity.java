package com.example.hpwin8.smartsupermarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    protected Button LogIn=null;
    protected TextView SignUP=null;
    protected EditText email=null;
    protected EditText password=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email= (EditText) findViewById(R.id.Email);
        password= (EditText) findViewById(R.id.passwordtxt);

        //<--  Log In  -->
        LogIn = (Button) findViewById(R.id.loginbutton);
        LogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                login( );

            }
        });
        try{
            Intent intent = getIntent();

            String userName = intent.getStringExtra("UserName");
            String passWordstr = intent.getStringExtra("Password");
            if(userName!=null)
            {
                email.setText(userName);
                password.setText(passWordstr);
                login();
            }
        }
        catch(Exception e)
        {
            Log.e("Login", e.getMessage());
        }

        //<--  Sign UP  -->

        SignUP = (TextView) findViewById(R.id.link_signup);
        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

    }

    public void login()
    {
        //  Log.d(TAG, "Login");

        if (!validate()  )  {
            onLoginFailed();

        }
        else {
            SmartmarketRestClientUsage oSmartmarketRestClientUsage = new SmartmarketRestClientUsage();
            oSmartmarketRestClientUsage.logIn(email.getText().toString(), password.getText().toString(), this);
        }
    }
    public boolean validate()
    {
        boolean valid = true;
        email= (EditText) findViewById(R.id.Email);
        password= (EditText) findViewById(R.id.passwordtxt);
        String emailstr = email.getText().toString();
        String passwordstr = password.getText().toString();

        //check email
        if(emailstr.isEmpty()) {
            email.setError("enter a valid email address");
            valid=false;
        }
        else
        {
            email.setError(null);
        }

        //check password

        if(passwordstr.isEmpty()) {
            password.setError("enter a valid email address");
            valid=false;
        }
        else
        {
            password.setError(null);
        }

        return valid;
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        // _loginButton.setEnabled(true);
    }

}
