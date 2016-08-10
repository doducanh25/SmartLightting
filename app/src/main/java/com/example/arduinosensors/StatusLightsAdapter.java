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
 * Created by user on 10/08/2016.
 */
public class StatusLightsAdapter extends BaseAdapter {

    private Context mContext;
    private List<Status> mStatus;


    public StatusLightsAdapter(Context context) {
        super();
        mContext = context;
        mStatus = new ArrayList<>();
    }

    public void setStatus(List<Status> statuses) {
        this.mStatus = statuses;
        notifyDataSetChanged();
    }

    public void clear () {
        if (mStatus != null) {
            mStatus.clear();
            notifyDataSetChanged();
        }
    }



    @Override
    public int getCount() {
        return mStatus.size();
    }

    @Override
    public Status getItem(int position) {
        return mStatus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_status_light,null);
            holder = new ViewHolder(convertView);
            convertView.setTag( holder );
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        final Status status = mStatus.get(position);

        holder.mId.setText(status.getId());

        holder.mValueDim.setText(status.getValueDim()+"%");


        return convertView;
    }

    static class ViewHolder {

        TextView mId;
        TextView mValueDim;

        ViewHolder(View view) {

            mId = (TextView) view.findViewById(R.id.id_light);
            mValueDim = (TextView) view.findViewById(R.id.value_dim);


        }

    }

}
