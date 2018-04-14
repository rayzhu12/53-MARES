package com.example.michelleliu.homelessapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Zhu
 */

class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private final List<String[]> scoreList = new ArrayList<>();
    //private final List<Shelter> shelterList = new ArrayList<>();

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

// --Commented out by Inspection START (4/4/2018 3:23 PM):
    /**
     * Constructor that takes in context and textViewResourceId
     * @param context the context
     * @param textViewResourceId the textView Resource ID
     */
    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
// --Commented out by Inspection STOP (4/4/2018 3:23 PM)

// --Commented out by Inspection START (4/4/2018 3:24 PM):
//    /**
//     * Constructor that takes in context, textViewResourceId, and object.
//     * @param context the context
//     * @param textViewResourceId the textView Resource ID
//     * @param object the object
//     */
//    public ItemArrayAdapter(Context context, int textViewResourceId, List object) {
//        super(context, textViewResourceId, object);
//    }
// --Commented out by Inspection STOP (4/4/2018 3:24 PM)

    @Override
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

// --Commented out by Inspection START (4/4/2018 3:24 PM):
//    /**
//     * adds shelter to shelter list
//     * @param shelter the shelter to be added
//     */
//    public void add(Shelter shelter) {
//        shelterList.add(shelter);
//        super.add(shelter.toArray());
//    }
// --Commented out by Inspection STOP (4/4/2018 3:24 PM)

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.scoreList.get(index);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.key = row.findViewById(R.id.key);
            viewHolder.name = row.findViewById(R.id.name);
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
        assert stat != null;
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