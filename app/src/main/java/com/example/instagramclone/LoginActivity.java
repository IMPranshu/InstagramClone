package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmailLogin, edtPasswordLogin;
    private Button btnLogin, btnSignUpPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LogIn");

        edtPasswordLogin = findViewById(R.id.edtPasswordLogin);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUpPage = findViewById(R.id.btnSignUpPage);

        edtPasswordLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    onClick(btnLogin);


                }

                return false;
            }
        });
        btnSignUpPage.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSignUpPage:

                Intent signup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signup);

                break;
            case R.id.btnLogin:

                if (edtEmailLogin.getText().toString().equals("") ||
                        edtPasswordLogin.getText().toString().equals("")) {


                    FancyToast.makeText(LoginActivity.this, "Email & Password are required", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();


                } else {

                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Logging In.... ");
                    progressDialog.show();
                    ParseUser.logInInBackground(edtEmailLogin.getText().toString(), edtPasswordLogin.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if (user != null && e == null) {
                                FancyToast.makeText(LoginActivity.this, user.get("username") + " is now Logged In.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionToSocialMediaActivity();
                            } else {
                                FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                            }
                            progressDialog.dismiss();
                        }


                    });
                }

                break;



        }

    }


    private void transitionToSocialMediaActivity(){
        Intent intent =new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
    public void rootLayoutLogin(View view) {

        try {
            InputMethodManager input = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}







