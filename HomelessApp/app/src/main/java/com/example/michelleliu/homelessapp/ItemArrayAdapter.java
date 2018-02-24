package com.example.michelleliu.homelessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond on 2/24/2018.
 */

public class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private List<String[]> scoreList = new ArrayList<String[]>();

    static class ItemViewHolder {
        TextView key;
        TextView name;
//        TextView capacity;
//        TextView gender;
//        TextView longitude;
//        TextView latitude;
//        TextView address;
//        TextView number;
    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.key = (TextView) row.findViewById(R.id.key);
            viewHolder.name = (TextView) row.findViewById(R.id.name);
//            viewHolder.capacity = (TextView) row.findViewById(R.id.capacity);
//            viewHolder.gender = (TextView) row.findViewById(R.id.gender);
//            viewHolder.longitude = (TextView) row.findViewById(R.id.longitude);
//            viewHolder.latitude = (TextView) row.findViewById(R.id.latitude);
//            viewHolder.address = (TextView) row.findViewById(R.id.address);
//            viewHolder.number = (TextView) row.findViewById(R.id.number);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = getItem(position);
        viewHolder.key.setText(stat[0]);
        viewHolder.name.setText(stat[1]);
//        viewHolder.capacity.setText(stat[2]);
//        viewHolder.gender.setText(stat[3]);
//        viewHolder.longitude.setText(stat[4]);
//        viewHolder.latitude.setText(stat[5]);
//        viewHolder.address.setText(stat[6]);
//        viewHolder.number.setText(stat[7]);
        return row;
    }
}