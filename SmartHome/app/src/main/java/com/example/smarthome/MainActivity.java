package com.example.smarthome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.smarthome.ui.camera.CameraFragment;
import com.example.smarthome.ui.chatbot.ChatBotFragment;
import com.example.smarthome.ui.control.ControlFragment;
import com.example.smarthome.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    LinearLayout layout_tab;
    TabLayout tabLayout;
    TabLayout tab;
    ViewPager viewPager;
    RelativeLayout relativeLayout;
    Button scanBtn;

    private DatabaseReference reference;
    private String user_PhoneNo,user_name,user_address,user_email;

    boolean rac = true;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.control:
                    layout_tab.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.GONE);
                    tab.setVisibility(View.VISIBLE);
                    runaddcontrol();
                    return true;

                case R.id.camera:
                    layout_tab.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    load_fragment_bottom(new CameraFragment());
                    return true;

                case R.id.chatbot:
                    layout_tab.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                    load_fragment_bottom(new ChatBotFragment());
                    return true;

                case R.id.profile:
                    //layout_tab.setVisibility(View.GONE);
                    //frameLayout.setVisibility(View.VISIBLE);
                    //load_fragment_bottom(new ProfileFragment());
                    selectedFragment = new ProfileFragment();
                    user_PhoneNo = getIntent().getExtras().get("phoneNo").toString();
                    reference = FirebaseDatabase.getInstance().getReference(user_PhoneNo);

                    Bundle bundle = new Bundle();
                    bundle.putString("key", user_PhoneNo);

                    selectedFragment.setArguments(bundle);

                    //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    //return true;
                    break;

            }
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_frame,selectedFragment).commit();
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        layout_tab = findViewById(R.id.layout_tab);
        frameLayout = findViewById(R.id.layout_frame);

        scanBtn = findViewById(R.id.scanBtn);

        tab = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpaper);

        scanBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScannerActivity.class));
            }
        });

        CallControlFragment();


    }

    public void runaddcontrol(){
        if(rac == true){
            addControl();
            rac = false;
        }
    }
    
    public void addControl() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        TabLayoutAdapter adapter = new TabLayoutAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setTabsFromPagerAdapter(adapter);
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        tab.getTabAt(1).setIcon(getDrawable(R.drawable.airconicon));
        tab.getTabAt(0).setIcon(getDrawable(R.drawable.lighticon));

    }

    private void CallControlFragment() {
        viewPager.setVisibility(View.VISIBLE);
        layout_tab.setVisibility(View.VISIBLE);
        tab.setVisibility(View.VISIBLE);
        load_fragment_bottom(new ControlFragment());
        runaddcontrol();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //Yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(MainActivity.this,PhoneLogin.class);
                startActivity(intent);


            }
        });

        //No
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setMessage("Are you sure to log out ?");
        builder.create().show();
    }

    Boolean load_fragment_bottom(Fragment fragment){
        if(fragment != null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.layout_frame, fragment).commit();
            return true;
        }
        return false;
    }

}