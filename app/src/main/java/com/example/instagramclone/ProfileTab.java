package com.example.instagramclone;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName,edtProfession,edtBio,edtSport,edtHobby;
    private Button btnUpdateInfo;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtBio = view.findViewById(R.id.edtBio);
        edtHobby =view.findViewById(R.id.edtHobbies);
        edtProfession=view.findViewById(R.id.edtProfession);
        edtSport=view.findViewById(R.id.edtSports);
        btnUpdateInfo=view.findViewById(R.id.btnUpdateInfo);

        final ParseUser parseUser = ParseUser.getCurrentUser();
        if(parseUser.get("profileName")!=null) {
            edtProfileName.setText(parseUser.get("profileName") + "");


        }else {
            edtProfileName.setText("");

        }
        if(parseUser.get("profileSport")!= null) {
            edtSport.setText(parseUser.get("profileSport") + "");

        }else {

            edtSport.setText("");

        }
           if(parseUser.get("profileHobbies")!= null){
               edtHobby.setText(parseUser.get("profileHobbies") + "");

           }else {

               edtHobby.setText("");

           }
           if(parseUser.get("profileProfession")!= null){
               edtProfession.setText(parseUser.get("profileProfession") + "");

           }else {
               edtProfession.setText("");

           }
           if(parseUser.get("profileBio")!= null){
                        edtBio.setText(parseUser.get("profileBio") + "");

           }else {
               edtBio.setText("");

           }
            btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    parseUser.put("profileName", edtProfileName.getText().toString());
                    parseUser.put("profileBio", edtBio.getText().toString());
                    parseUser.put("profileProfession", edtProfession.getText().toString());
                    parseUser.put("profileHobbies", edtHobby.getText().toString());
                    parseUser.put("profileSport", edtSport.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Please Wait While info Is being Updated");
                    progressDialog.show();

                    parseUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(getContext(), "Info Updated", FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();

                            } else {
                                FancyToast.makeText(getContext(), e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
                }
            });
        return view;
    }

}
