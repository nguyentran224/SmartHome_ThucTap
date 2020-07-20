package com.example.smarthome.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.smarthome.Helper.User;
import com.example.smarthome.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    TextView tv_phoneno,tv_email,tv_address,tv_name,tv_username;
    String name,address,sdt,email;
    ArrayList<User> list = new ArrayList<>();



    public ProfileFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.fragment_profile, container, false);
        tv_phoneno = (TextView) view.findViewById(R.id.tv_phoneno);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        tv_address = (TextView) view.findViewById(R.id.tv_address);
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        Bundle bundle = getArguments();

        final String user_phoneNo =  bundle.getString("key");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

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

}
