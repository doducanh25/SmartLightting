package com.example.arduinosensors;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 22/07/2016.
 */
public class DetailScriptAdapter extends BaseAdapter {

    private Context mContext;
    private List<Groups> mLight;

    private static Groups groups;
    //
    private String value;


    public static List<String> list;


    public DetailScriptAdapter(Context context) {
        super();
        mContext = context;
        mLight = new ArrayList<>();
        list = new ArrayList<>();
    }

    public void setScript(List<Groups> groupses) {
        this.mLight = groupses;
        notifyDataSetChanged();
    }

    public void clear() {
        if (mLight != null) {
            mLight.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mLight.size();
    }

    @Override
    public Groups getItem(int position) {
        return mLight.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_script, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        groups = getItem(position);


        try {
            for (int i = 0 ;i <list.size();i++) {
                Log.d("gia_tri_i",String.valueOf(list.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.mName.setText("Nhom" + " " + groups.getmNameGroupLight());

        holder.mResultDim.setText(String.valueOf(groups.getId())+"%");
        holder.mDimScript.setProgress(groups.getId());

        holder.mDimScript.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressBar = groups.getId();

            String name = groups.getmNameGroupLight();

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                holder.mResultDim.setText(progressBar + "%");
//                list.add(groups.getmNameGroupLight() + "-" + String.valueOf(progressBar));
//                saveArray(mContext, list, "key_value");

                groups.setmNameGroupLight(name);
                groups.setId(progressBar);

            }
        });




        return convertView;
    }

    static class ViewHolder {

        TextView mName;

        TextView mResultDim;

        SeekBar mDimScript;

        ViewHolder(View view) {

            mName = (TextView) view.findViewById(R.id.name_group);

            mResultDim = (TextView) view.findViewById(R.id.result_dim_script);

            mDimScript = (SeekBar) view.findViewById(R.id.sb_script_dim);




        }

    }



    public static void clearArray(Context mContext) {
        SharedPreferences preferences = mContext.getSharedPreferences("keyBar", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }


}
