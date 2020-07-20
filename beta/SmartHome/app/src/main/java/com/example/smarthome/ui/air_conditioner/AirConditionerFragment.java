package com.example.smarthome.ui.air_conditioner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Placeholder;
import androidx.fragment.app.Fragment;

import com.example.smarthome.AirConAdapter;
import com.example.smarthome.DataAirCon;
import com.example.smarthome.R;

import java.util.ArrayList;

public class AirConditionerFragment extends Fragment {
    @Nullable
    TextView tv_temparature;
    TextView tv_status;
    TextView adddevicetext;
    ImageView increaseTemp;
    ImageView decreaseTemp;
    int defaultTemp = 25;
    ImageView buttonPower;
    String statusPower = "OFF";
    boolean temptv_status = false;
    GridView gridview;
    ImageView daucong;
    ArrayList<DataAirCon> arrayaircon;
    AirConAdapter adapter;

    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_air_conditioner, container, false);


        adddevicetext = view.findViewById(R.id.adddevicetext);
        gridview = view.findViewById(R.id.gridview);
        daucong = view.findViewById(R.id.daucong);

        arrayaircon = new ArrayList<>();
        adapter = new AirConAdapter(getActivity(),R.layout.item_gridlist,arrayaircon);
        gridview.setAdapter(adapter);

        //lấy action của layout item trong gridlist
        final View devicelayout = getLayoutInflater().inflate(R.layout.item_gridlist,null);

        tv_temparature = devicelayout.findViewById(R.id.temparatureI);
        increaseTemp = devicelayout.findViewById(R.id.increaseTempI);
        decreaseTemp = devicelayout.findViewById(R.id.decreaseTempI);
        buttonPower = devicelayout.findViewById(R.id.buttonPowerI);
        tv_status = devicelayout.findViewById(R.id.statusI);

        daucong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.startAnimation(buttonClick);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                final View customlayout = getLayoutInflater().inflate(R.layout.input_item, null);
                builder.setView(customlayout);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText devicename = (EditText) customlayout.findViewById(R.id.Devicename);
                        EditText deviceid = (EditText) customlayout.findViewById(R.id.Deviceid);
                        String tentb = devicename.getText().toString();
                        String idtb = deviceid.getText().toString();

                        if(tentb.equals("")){
                            Toast.makeText(getContext(),"Hãy nhập tên và id thiết bị",Toast.LENGTH_SHORT).show();

                        }
                        else{
                            arrayaircon.add(new DataAirCon(idtb,tentb));
                            adddevicetext.setVisibility(View.GONE);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();

//                if (arrayaircon != null){
//
//                    gridview.setVisibility(View.VISIBLE);
//                }
            }
        });
      Tempchange();
        return view;
    }

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);

    private void Tempchange(){
        increaseTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temptv_status == true){
                    defaultTemp += 1;
                    showTemp();
                }

            }
        });
        decreaseTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temptv_status == true){
                    if (defaultTemp > 0){
                        defaultTemp -= 1;
                        showTemp();
                    }
                }
            }
        });
        buttonPower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temptv_status == false){
                    statusPower = "ON";
                    showStatus();
                    temptv_status = true;
                }
                else if (temptv_status == true){
                    statusPower = "OFF";
                    showStatus();
                    temptv_status = false;
                }

                if(temptv_status == false){
                    defaultTemp = 25;
                    showTemp();
                }
            }
        });

        showTemp();
        tv_status.setText(statusPower);
    }

    private void showTemp() {
        tv_temparature.setText(defaultTemp+"\u2103");

    }

    private void showStatus() {
        tv_status.setText(statusPower);
    }

}