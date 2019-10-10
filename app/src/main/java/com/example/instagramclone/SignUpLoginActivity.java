package com.example.instagramclone;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    private EditText edtNameSignUp,edtPasswordSignUp,edtNameLogin,edtPasswordLogin;
    private Button btnSignUp,btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);

        edtNameLogin=findViewById(R.id.edtNameLogin);
        edtNameSignUp=findViewById(R.id.edtNameSignUp);
        edtPasswordLogin=findViewById(R.id.edtPasswordLogin);
        edtPasswordSignUp=findViewById(R.id.edtPasswordSignUp);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtNameSignUp.getText().toString());
                appUser.setPassword(edtPasswordSignUp.getText().toString());

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e== null)
                        {
                            FancyToast.makeText(SignUpLoginActivity.this,appUser.get("username")+" is Signed Up Sucessfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }else {
                            Toast.makeText(SignUpLoginActivity.this, e.getMessage() + "", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ParseUser.logInInBackground(edtNameLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        if(user != null && e ==null){
                            FancyToast.makeText(SignUpLoginActivity.this,user.get("username")+" is Signed Up Sucessfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        }else {
                            FancyToast.makeText(SignUpLoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                    }
                });

            }
        });

    }
}
