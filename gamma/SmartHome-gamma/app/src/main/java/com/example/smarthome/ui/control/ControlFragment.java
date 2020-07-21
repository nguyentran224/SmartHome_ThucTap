package com.example.smarthome.ui.control;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.smarthome.AirConAdapter;
import com.example.smarthome.Helper.User;
import com.example.smarthome.R;
import com.example.smarthome.TabLayoutAdapter;
import com.example.smarthome.ui.air_conditioner.AirConditionerFragment;
import com.example.smarthome.ui.light.LightFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.view.View.OnClickListener;

public class ControlFragment extends Fragment {
    boolean rac = false;
    Context context;
    TabLayout tab;
    ViewPager viewPager;
    FloatingActionButton add_devices;
    String userphone,user_PhoneNo;
    private DatabaseReference reference;
    public ControlFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_control, container, false);
        tab = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.viewpaper);
        /*
        user_PhoneNo = getActivity().getIntent().getExtras().get("phoneNo").toString();
        reference = FirebaseDatabase.getInstance().getReference(user_PhoneNo);
        Toast.makeText(getActivity(),user_PhoneNo,Toast.LENGTH_LONG).show();

         */





        runaddcontrol();
        add_devices = view.findViewById(R.id.add_devices);

        add_devices.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_add_devices, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.inputNumberLight);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        //result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });


        return view;
    }

    public void runaddcontrol() {
        if(rac == true){
            addControl();
            rac = false;
        }
    }

    public void addControl() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        TabLayoutAdapter adapter = new TabLayoutAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setTabsFromPagerAdapter(adapter);
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));



        //final Bundle bundle = getArguments();
        //userphone =  bundle.getString("keyphone");
        LightFragment lightFragment = new LightFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("keyfone",userphone);
        lightFragment.setArguments(bundle1);
        getChildFragmentManager().beginTransaction().replace(R.id.layout_frame,lightFragment).commit();
        //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame,lightFragment).commit();
        //fragmentManager.getFragment(bundle1,"keyfone");

        tab.getTabAt(1).setIcon(getActivity().getDrawable(R.drawable.airconicon));
        tab.getTabAt(0).setIcon(getActivity().getDrawable(R.drawable.lighticon));
    }
}