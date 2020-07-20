package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.smarthome.ui.control.ControlFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PhoneLogin extends AppCompatActivity {

    private TextInputLayout phoneNo;

    private Button phoneLogin_btn;

    private ImageButton phoneNo_sendtoRegisterPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);

        phoneNo = findViewById(R.id.login_phoneNo);
        phoneLogin_btn = findViewById(R.id.phoneLogin_btn);
        phoneNo_sendtoRegisterPhone = findViewById(R.id.btn_sendRegisterPhone);

        phoneNo_sendtoRegisterPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtoRegisterPhone();
            }
        });

        phoneLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });


    }

    private Boolean validatePhoneNo(){
        String val = phoneNo.getEditText().getText().toString();

        if (val.isEmpty())
        {
            phoneNo.setError("Phone Number is Empty");
            return false;
        }
        else{
            phoneNo.setError(null);
            phoneNo.setErrorEnabled(false);

            return true;
        }

    }

    public void sendtoRegisterPhone(){
        Intent intent = new Intent(PhoneLogin.this,SignUp.class);
        startActivity(intent);

    }

    public void loginUser(){
        if (!validatePhoneNo())
        {
            return;
        }
        else{
            isUser();
        }

    }

    private void isUser() {

        final String userEnteredPhoneNo = phoneNo.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");

        Query checkUser = reference.orderByChild("phoneNo").equalTo(userEnteredPhoneNo);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {


                    String phoneNoFromDB = dataSnapshot.child(userEnteredPhoneNo).child("phoneNo").getValue(String.class);

                    if (phoneNoFromDB.equals(userEnteredPhoneNo)){


                        String nameFromDB = dataSnapshot.child(userEnteredPhoneNo).child("name").getValue(String.class);
                        String addressFromDB = dataSnapshot.child(userEnteredPhoneNo).child("address").getValue(String.class);
                        String passwordFromDB = dataSnapshot.child(userEnteredPhoneNo).child("password").getValue(String.class);
                        String emailFromDB = dataSnapshot.child(userEnteredPhoneNo).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);


                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("address",addressFromDB);
                        intent.putExtra("phoneNo",phoneNoFromDB);
                        intent.putExtra("email",emailFromDB);
                        intent.putExtra("password",passwordFromDB);

                        startActivity(intent);
                        Toast.makeText(PhoneLogin.this, "Login Succes !", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        phoneNo.setError("Phone No Wrong");
                        phoneNo.requestFocus();
                    }
                }
                else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}