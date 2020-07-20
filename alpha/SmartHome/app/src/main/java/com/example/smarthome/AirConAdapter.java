package com.example.smarthome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AirConAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DataAirCon> dataAirCons;

    public AirConAdapter(Context context, int layout, List<DataAirCon> dataAirCons) {
        this.context = context;
        this.layout = layout;
        this.dataAirCons = dataAirCons;
    }

    @Override
    public int getCount() {
        return dataAirCons.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView temparatureI;
        TextView tenmaylanhI;
        TextView texttangI;
        TextView statusI;
        TextView textgiamI;
        ImageView increaseTempI;
        ImageView buttonPowerI;
        ImageView decreaseTempI;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final boolean[] tempstatus = {false};
        //final String[] tempnhiet = new String[1];
        final int[] tempnhiet = new int[1];
        final ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            //anh xa :
            holder.temparatureI = convertView.findViewById(R.id.temparatureI);
            holder.tenmaylanhI = convertView.findViewById(R.id.tenmaylanhI);
            holder.increaseTempI = convertView.findViewById(R.id.increaseTempI);
            holder.decreaseTempI = convertView.findViewById(R.id.decreaseTempI);
            holder.buttonPowerI = convertView.findViewById(R.id.buttonPowerI);
            holder.statusI = convertView.findViewById(R.id.statusI);
            holder.texttangI = convertView.findViewById(R.id.texttangI);
            holder.textgiamI = convertView.findViewById(R.id.textgiamI);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        final DataAirCon dataAirCon = dataAirCons.get(position);
        holder.temparatureI.setText(dataAirCon.getTenmaylanh());

        holder.buttonPowerI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tempstatus[0] ==  false){
                    holder.statusI.setText("ON");
                    tempstatus[0] = true;
                }
                else {
                    holder.statusI.setText("OFF");
                    tempstatus[0] = false;
                }
                if (tempstatus[0] == false){
                    tempnhiet[0] = 0;
                    holder.temparatureI.setText(tempnhiet[0]+"\u2103");

                }
            }
        });

        holder.increaseTempI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempstatus[0] == true ){
                    if (tempnhiet[0]<30){
                        tempnhiet[0] += 1;
                        holder.temparatureI.setText(tempnhiet[0]+"\u2103");
                    }

                }
            }
        });
        holder.decreaseTempI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tempstatus[0] == true){
                    if (tempnhiet[0]>0){
                        tempnhiet[0] -= 1;
                        holder.temparatureI.setText(tempnhiet[0]+"\u2103");
                    }
                }
            }
        });





        return convertView;
    }



}
