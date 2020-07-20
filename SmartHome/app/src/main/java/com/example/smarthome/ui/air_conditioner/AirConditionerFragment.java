package com.example.smarthome.ui.air_conditioner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.smarthome.AirConAdapter;
import com.example.smarthome.DataAirCon;
import com.example.smarthome.R;

import java.util.ArrayList;

public class AirConditionerFragment extends Fragment {
    @Nullable
    TextView tv_temparature;
    TextView tv_status;
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

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_air_conditioner, container, false);
        tv_temparature = view.findViewById(R.id.temparature);
        increaseTemp = view.findViewById(R.id.increaseTemp);
        decreaseTemp = view.findViewById(R.id.decreaseTemp);
        buttonPower = view.findViewById(R.id.buttonPower);
        tv_status = view.findViewById(R.id.status);
        gridview = view.findViewById(R.id.gridview);
        daucong = view.findViewById(R.id.daucong);

        gridview.setVisibility(View.GONE);
        arrayaircon = new ArrayList<>();
        arrayaircon.add(new DataAirCon("id1","Máy lạnh 2"));
        arrayaircon.add(new DataAirCon("id2","Máy lạnh 3"));
        adapter = new AirConAdapter(getActivity(),R.layout.item_gridlist,arrayaircon);
        gridview.setAdapter(adapter);
        daucong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayaircon != null){
                    daucong.setVisibility(View.GONE);
                    gridview.setVisibility(View.VISIBLE);
                }
            }
        });

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
        return view;
    }

    private void showTemp() {
        tv_temparature.setText(defaultTemp+"\u2103");

    }

    private void showStatus() {
        tv_status.setText(statusPower);
    }

}