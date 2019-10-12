package com.example.instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNameSignUp,edtPasswordSignUp,edtEmailSignUp;
    private Button btnSignUp,btnLoginPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up");

        edtEmailSignUp=findViewById(R.id.edtEmailSignUp);
        edtNameSignUp=findViewById(R.id.edtNameSignUp);
        edtPasswordSignUp=findViewById(R.id.edtPasswordSignUp);


        btnSignUp=findViewById(R.id.btnSignUp);
        btnLoginPage=findViewById(R.id.btnLoginPage);

        edtPasswordSignUp.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignUp);


                }

                return false;
            }
        });

        btnLoginPage.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){
            transitionToSocialMediaActivity();
        }

    }

    private void transitionToSocialMediaActivity(){
        Intent intent =new Intent(SignUpActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnLoginPage:

                Intent login =new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(login);

                break;
            case R.id.btnSignUp:
                if(edtEmailSignUp.getText().toString().equals("") ||
                        edtNameSignUp.getText().toString().equals("")||
                        edtPasswordSignUp.getText().toString().equals("")){


                    FancyToast.makeText(SignUpActivity.this,"Email,Username,Password is required", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                }
                else {
                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmailSignUp.getText().toString());
                    appUser.setUsername(edtNameSignUp.getText().toString());
                    appUser.setPassword(edtPasswordSignUp.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this);
                    progressDialog.setMessage("Signing Up " + edtNameSignUp.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(SignUpActivity.this, appUser.get("username") + " is Signed Up Sucessfully.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                transitionToSocialMediaActivity();


                            } else {
                                FancyToast.makeText(SignUpActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                            progressDialog.dismiss();
                        }
                    });

                }


                break;
        }
    }
    public void rootLayoutTapped(View view){


        try {


            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }


