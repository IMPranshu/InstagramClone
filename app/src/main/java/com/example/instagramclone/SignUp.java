package com.example.instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private EditText playerName,getPunchPower,getPunchSpeed,getKickPower,getKickSpeed;
    private Button btnkickBoxer,btnboxer,btnTransition;
    private TextView txtGetData,txtGetBoxerData;


    private String allKickBoxer,allBoxer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnTransition= findViewById(R.id.btnNextActivity);
        txtGetBoxerData = findViewById(R.id.txtGetBoxerData);
        playerName=findViewById(R.id.playerName);
        getKickPower=findViewById(R.id.getKickPower);
        getKickSpeed=findViewById(R.id.getKickSpeed);
        getPunchPower=findViewById(R.id.getPunchPower);
        getPunchSpeed=findViewById(R.id.getPunchSpeed);
        btnkickBoxer=findViewById(R.id.btnKickBoxer);
        btnboxer=findViewById(R.id.btnBoxer);
        txtGetData = findViewById(R.id.txtGetData);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxer = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("KickBoxer_Details");
                queryAll.whereGreaterThan("Punch_Power",100);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null){
                            if(objects.size()>0){
                                for (ParseObject kickboxer : objects) {
                                    allKickBoxer = allKickBoxer + kickboxer.get("name") +"\n";
                                }
                                txtGetData.setText(allKickBoxer);
                            }else{
                                Toast.makeText(SignUp.this, e.getMessage() + "", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });


            }
        });

        txtGetBoxerData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allBoxer = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Boxer_Details");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null){
                            if(objects.size()>0){
                                for (ParseObject boxer : objects) {
                                    allBoxer = allBoxer + boxer.get("name") +"\n";
                                }
                                txtGetBoxerData.setText(allBoxer);
                            }else{
                                Toast.makeText(SignUp.this, e.getMessage() + "", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });


            }
        });


        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,SignUpLoginActivity.class);
                startActivity(intent);
            }
        });



    }


    public void onKickBoxerPressed(View c){

        try {
            final ParseObject kickBoxer = new ParseObject("KickBoxer_Details");
            kickBoxer.put("name", playerName.getText().toString());
            kickBoxer.put("Punch_Power", getPunchPower.getText().toString());
            kickBoxer.put("Punch_Speed", getPunchSpeed.getText().toString());
            kickBoxer.put("Kick_Power", getKickPower.getText().toString());
            kickBoxer.put("Kick_Speed", getKickSpeed.getText().toString());
            kickBoxer.saveEventually(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {


                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is now a Kick Boxer.", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                        //  Toast.makeText(SignUp.this,kickBoxer.get("name")+" is now a Kick Boxer.",Toast.LENGTH_LONG).show();
                    } else

                        Toast.makeText(SignUp.this, e.getMessage() + "", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e){
            FancyToast.makeText(SignUp.this,e.getMessage()+"",FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

        }
    }

    public void onBoxerPressed(View c){
        final ParseObject boxer = new ParseObject("Boxer_Details");
        boxer.put("name",playerName.getText().toString());
        boxer.put("Punch_Power",getPunchPower.getText().toString());
        boxer.put("Punch_Speed",getPunchSpeed.getText().toString());
        boxer.put("Kick_Power",getKickPower.getText().toString());
        boxer.put("Kick_Speed",getKickSpeed.getText().toString());
        boxer.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e ==null){
                    Toast.makeText(SignUp.this,boxer.get("name")+" is now a Boxer.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
