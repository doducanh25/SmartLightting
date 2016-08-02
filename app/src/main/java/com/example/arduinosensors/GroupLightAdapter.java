package com.example.arduinosensors;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19/07/2016.
 */
public class GroupLightAdapter extends BaseAdapter {

    private Context mContext;
    private List<Groups> mLight;
    private OnClickDelete mOnClickDelete;
    private OnGroupClick mOnGroupClick;


    public GroupLightAdapter(Context context) {
        super();
        mContext = context;
        mLight = new ArrayList<>();
    }

    public void setLight(List<Groups> groupses) {
        this.mLight = groupses;
        notifyDataSetChanged();
    }

    public void clear () {
        if (mLight != null) {
            mLight.clear();
            notifyDataSetChanged();
        }
    }


    public void setOnClickDelete (OnClickDelete onClickDelete) {
        this.mOnClickDelete = onClickDelete;
    }

    public void setOnGroupClick (OnGroupClick onGroupClick) {
        this.mOnGroupClick = onGroupClick;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_light_group,null);
            holder = new ViewHolder(convertView);
            convertView.setTag( holder );
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        final Groups groups = mLight.get(position);

        holder.mNameGroup.setText(groups.getmNameGroupLight());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnClickDelete != null) {
                    mOnClickDelete.onClickDelete(position,groups.getmNameGroupLight());
                }
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnGroupClick !=null) {
                    mOnGroupClick.onGroupClick(position);
                }
            }
        });


        return convertView;
    }

    static class ViewHolder {

        TextView mNameGroup;

        ImageView delete;

        ViewHolder(View view) {
            mNameGroup = (TextView) view.findViewById(R.id.name_group);

            delete = (ImageView) view.findViewById(R.id.iv_detele);
        }

    }

    public interface OnClickDelete {
        void onClickDelete(int postion,String name);
    }

    public interface OnGroupClick {
        void onGroupClick (int postion);
    }

}
