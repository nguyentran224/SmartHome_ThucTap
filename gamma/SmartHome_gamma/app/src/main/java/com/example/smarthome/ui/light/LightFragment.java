package com.example.smarthome.ui.light;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.smarthome.DataLight;
import com.example.smarthome.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class LightFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imagelight;
    boolean tempbongden = false;
    View view;
    TextView adddevicetext;

    ListView listView;
    ArrayList<DataLight> dataLight;
    ImageView daucong;
    String userphone,user_PhoneNo;
    private DatabaseReference reference;
    public LightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LightFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LightFragment newInstance(String param1, String param2) {
        LightFragment fragment = new LightFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_light, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        daucong = (ImageView) view.findViewById(R.id.daucong);
        adddevicetext = view.findViewById(R.id.adddevicetext);

        user_PhoneNo = getActivity().getIntent().getExtras().get("phoneNo").toString();
        reference = FirebaseDatabase.getInstance().getReference(user_PhoneNo);
        adddevicetext.setText(user_PhoneNo);
        //Toast.makeText(getActivity(),user_PhoneNo,Toast.LENGTH_LONG).show();



//     lightcontrol();
        return view;
        //return inflater.inflate(R.layout.fragment_light, container, false);
    }

    private void lightcontrol(){
        imagelight = view.findViewById(R.id.image_light);

        imagelight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempbongden == false){
                    imagelight.setImageResource(R.drawable.batden);
                    tempbongden = true;
                }
                else {
                    imagelight.setImageResource(R.drawable.tatden);
                    tempbongden = false;
                }



            }
        });

    }
}