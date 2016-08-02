package com.example.arduinosensors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 21/07/2016.
 */
public class DetailGroupLightAdapter extends BaseAdapter {

    private List<Light> mLight;
    private Context mContext;
    private OnClickDeleteLight mOnClickDeleteLight;

    public DetailGroupLightAdapter(Context context) {
        super();
        this.mContext = context;
        mLight = new ArrayList<>();
    }

    public void setGroupDetail (List<Light> lights) {
        this.mLight = lights;
        notifyDataSetChanged();
    }

    public void setOnClickDeleteLight (OnClickDeleteLight onClickDeleteLight) {
        this.mOnClickDeleteLight = onClickDeleteLight;
    }

    @Override
    public int getCount() {
        return mLight.size();
    }

    @Override
    public Light getItem(int position) {
        return mLight.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_detail_group_light,null);
            holder = new ViewHolder (convertView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        final Light light = mLight.get(position);

        holder.mNameLight.setText(light.getmNameLight());
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickDeleteLight != null) {
                    mOnClickDeleteLight.onClickDeleteLight(position,light.getmNameLight());
                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView mNameLight;
        ImageView mDelete;

        ViewHolder (View view) {
            mNameLight = (TextView)view.findViewById(R.id.name_light);
            mDelete = (ImageView)view.findViewById(R.id.iv_detele);
        }

    }

    public interface OnClickDeleteLight {
        void onClickDeleteLight(int postion,String light);
    }


}
