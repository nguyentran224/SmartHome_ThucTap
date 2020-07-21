package com.example.smarthome.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthome.Helper.User;
import com.example.smarthome.R;
import com.example.smarthome.editprofile;
import com.example.smarthome.ui.light.LightFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    TextView tv_phoneno,tv_email,tv_address,tv_name,tv_username;
    String name,address,sdt,email;
    Button editbtn;
    String userPhone,username;
    ArrayList<User> list = new ArrayList<>();


    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_profile, container, false);
        tv_phoneno = (TextView) view.findViewById(R.id.tv_phoneno);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        editbtn = view.findViewById(R.id.editbtn);

        final Bundle bundle = getArguments();
        final String user_phoneNo =  bundle.getString("key");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(buttonClick);
                //String temple = bundle.getString("key");
                Intent intent = new Intent(getActivity(), editprofile.class);
                //intent.putExtra("keyne",temple);
                intent.putExtra("keyne",sdt);
                intent.putExtra("keyemail",email);
                startActivity(intent);
            }


        });

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                email=dataSnapshot.child(user_phoneNo).child("email").getValue().toString();
                address=dataSnapshot.child(user_phoneNo).child("address").getValue().toString();
                name=dataSnapshot.child(user_phoneNo).child("name").getValue().toString();
                sdt=dataSnapshot.child(user_phoneNo).child("phoneNo").getValue().toString();



                tv_address.setText(address);
                tv_email.setText(email);
                tv_name.setText(name);
                tv_phoneno.setText(sdt);
                tv_username.setText(name);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }



    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);



}
