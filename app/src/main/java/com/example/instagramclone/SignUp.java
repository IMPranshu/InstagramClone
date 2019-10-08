package com.example.instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity {

    private EditText playerName,getPunchPower,getPunchSpeed,getKickPower,getKickSpeed;
    private Button btnkickBoxer,btnboxer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        playerName=findViewById(R.id.playerName);
        getKickPower=findViewById(R.id.getKickPower);
        getKickSpeed=findViewById(R.id.getKickSpeed);
        getPunchPower=findViewById(R.id.getPunchPower);
        getPunchSpeed=findViewById(R.id.getPunchSpeed);
        btnkickBoxer=findViewById(R.id.btnKickBoxer);
        btnboxer=findViewById(R.id.btnBoxer);
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
