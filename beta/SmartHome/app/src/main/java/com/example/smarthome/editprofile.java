package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.smarthome.Helper.UserHelperClass;
import com.example.smarthome.Helper.UserUpdateHelperClass;
import com.example.smarthome.ui.profile.ProfileFragment;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class editprofile extends AppCompatActivity {
    TextInputLayout editName , editAddress , editPassword;
    Button updatebtn;
    String phone,sdt;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        anhxa();
        //c1 :
        /*
        if (savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if (extras == null){
                phone = null;
            }
            else{
                phone = (String) extras.get("keyne");
            }
        }else{
            phone = (String) savedInstanceState.getSerializable("keyne");
        }

         */

        //c2 :
        laysdt();



        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
            }
        });

        /*
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                sdt = dataSnapshot.child(phone).child("phoneNo").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

         */

    }

    private void laysdt() {
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            phone =(String) b.get("keyne");
            //Toast toast = Toast.makeText(this,phone,Toast.LENGTH_LONG);
            //toast.show();

        }
    }


    private void anhxa() {
        editAddress = findViewById(R.id.editAddress);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        updatebtn = findViewById(R.id.updatebtn);



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
        String val = editName.getEditText().getText().toString();

        if (val.isEmpty())
        {
            editName.setError("Name is Empty");
            return false;
        }
        else{
            editName.setError(null);
            editName.setErrorEnabled(false);

            return true;
        }

    }
    private Boolean validateAddress(){
        String val = editAddress.getEditText().getText().toString();

        if (val.isEmpty())
        {
            editAddress.setError("Address is Empty");
            return false;
        }
        else{
            editAddress.setError(null);
            editAddress.setErrorEnabled(false);

            return true;
        }

    }


    private Boolean validatePassword(){
        String val = editPassword.getEditText().getText().toString();

        if (val.isEmpty()){
            editPassword.setError("Password is Empty");
            return false;
        }
        else{
            editPassword.setError(null);
            editPassword.setErrorEnabled(false);
            return true;
        }
    }
    private void updateUser(){
        if (!validateAddress() | !validatePassword()| !validatename())
        {
            return;
        }
        else{
            editall();
        }

    }

    public void editall() {
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("user");
        String name = editName.getEditText().getText().toString();
        String address = editAddress.getEditText().getText().toString();
        String password = editPassword.getEditText().getText().toString();

        //UserHelperClass helperClass = new UserHelperClass(name,address,password);
        //reference.child(phoneNo).setValue(helperClass);

        UserUpdateHelperClass updateHelperClass = new UserUpdateHelperClass(name,address,password);
        reference.child(phone).setValue(updateHelperClass);
        reference = rootNode.getReference(phone);

        reference.child("control").child("device_1").setValue(name);
        reference.child("control").child("device_2").setValue(address);
        reference.child("control").child("device_3").setValue(password);
        //reference.push().child("control").child("device_1").setValue(name);
        //reference.push().child("control").child("device_2").setValue(address);
        //reference.push().child("control").child("device_5").setValue(password);

        Toast.makeText(this, "Update Success !", Toast.LENGTH_SHORT).show();
        gotoProfile();
    }

    private void gotoProfile() {
        Intent intent = new Intent(editprofile.this, ProfileFragment.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}