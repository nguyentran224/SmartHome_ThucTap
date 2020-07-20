package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.smarthome.Helper.UserHelperClass;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private TextInputLayout regName, regAddress, regEmail, regPhoneNo, regPassword;
    private Button regBtn;
    private RelativeLayout rlayout;
    private Animation animation;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = findViewById(R.id.bgHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);

        regName = findViewById(R.id.name);
        regAddress = findViewById(R.id.address);
        regEmail = findViewById(R.id.email);
        regPhoneNo = findViewById(R.id.phoneNo);
        regPassword = findViewById(R.id.password);

        regBtn = findViewById(R.id.regBtn);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private Boolean validatename(){
        String val = regName.getEditText().getText().toString();

        if (val.isEmpty())
        {
            regName.setError("Name is Empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);

            return true;
        }

    }
    private Boolean validateAddress(){
        String val = regAddress.getEditText().getText().toString();

        if (val.isEmpty())
        {
            regAddress.setError("Address is Empty");
            return false;
        }
        else{
            regAddress.setError(null);
            regAddress.setErrorEnabled(false);

            return true;
        }

    }
    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();

        if (val.isEmpty())
        {
            regEmail.setError("Email is Empty");
            return false;
        }
        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);

            return true;
        }

    }
    private Boolean validatePhoneNo(){
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty())
        {
            regPhoneNo.setError("Phone Number is Empty");
            return false;
        }
        else{
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);

            return true;
        }

    }
    private Boolean validatePassword(){
        String val = regPassword.getEditText().getText().toString();

        if (val.isEmpty()){
            regPassword.setError("Password is Empty");
            return false;
        }
        else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void registerUser(){
        if (!validateAddress() | !validatePassword()| !validatename()| !validateEmail()| !validatePhoneNo())
        {
            return;
        }
        else{
            isRegis();
        }

    }


    private void isRegis() {


        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("user");
        String s = "0";
        int i = Integer.parseInt(s);
        String s1 = "1";
        int i1 = Integer.parseInt(s1);

        String name = regName.getEditText().getText().toString();
        String address = regAddress.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String phoneNo = regPhoneNo.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        UserHelperClass helperClass = new UserHelperClass(name,address,email,phoneNo,password);
        reference.child(phoneNo).setValue(helperClass);

        reference = rootNode.getReference(phoneNo);

        reference.child("control").child("device_1").setValue(i);
        reference.child("control").child("device_2").setValue(i);
        reference.child("control").child("device_3").setValue(i);
        reference.child("control").child("device_4").setValue(i);
        reference.child("control").child("device_5").setValue(i);
        reference.child("control").child("device_6").setValue(i);

        Toast.makeText(this, "Register Success !", Toast.LENGTH_SHORT).show();
        sendToLogin();
    }


    public void sendToLogin(){
        Intent intent = new Intent(SignUp.this,PhoneLogin.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}