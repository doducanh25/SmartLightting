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
 * Created by user on 22/07/2016.
 */
public class ScriptAdapter extends BaseAdapter {

    private Context mContext;


    private List<Scripts> mScript;

    private OnClickDelete mOnClickDelete;
    private OnScriptClick mOnScriptClick;

    public ScriptAdapter(Context context) {
        super();
        mContext = context;
        mScript = new ArrayList<>();
    }

    public void setScripts(List<Scripts> scriptses) {
        this.mScript = scriptses;
        notifyDataSetChanged();
    }

    public void clear () {
        if (mScript != null) {
            mScript.clear();
            notifyDataSetChanged();
        }
    }

    public void setOnClickDelete (OnClickDelete onClickDelete) {
        this.mOnClickDelete = onClickDelete;
    }

    public void setOnScriptClick (OnScriptClick onScriptClick) {
        this.mOnScriptClick = onScriptClick;
    }

    @Override
    public int getCount() {
        return mScript.size();
    }

    @Override
    public Scripts getItem(int position) {
        return mScript.get(position);
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

        final Scripts scripts = getItem(position);

        holder.mNameScript.setText("Kịch Bản"+ " "+scripts.getmNameScript());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnClickDelete != null) {
                    mOnClickDelete.onClickDelete(position,scripts.getmNameScript());
                }
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnScriptClick !=null) {
                    mOnScriptClick.onScriptClick(position);
                }
            }
        });


        return convertView;
    }

    static class ViewHolder {

        TextView mNameScript;

        ImageView delete;

        ViewHolder(View view) {
            mNameScript = (TextView) view.findViewById(R.id.name_group);

            delete = (ImageView) view.findViewById(R.id.iv_detele);
        }

    }

    public interface OnClickDelete {
        void onClickDelete(int postion,String nameScript);
    }

    public interface OnScriptClick {
        void onScriptClick (int postion);
    }

}
