package com.androidclass.listviewusingarrays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jsingh on 8/3/16.
 */
public class MyCustomAdapter extends ArrayAdapter<Contact> {


    public MyCustomAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_row, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.txt_phone);
            viewHolder.delBtn = (Button) convertView.findViewById(R.id.btn_del);

            // Attach the click event handler
            viewHolder.delBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = (Integer) view.getTag();
                    // Access the row position here to get the correct data item
                    Contact user = getItem(position);
                    remove(user);
                    notifyDataSetChanged();
                }
            });


            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.name.setText(user.getName());
        viewHolder.phone.setText(user.getPhone());
        viewHolder.delBtn.setTag(position);

        // Return the completed view to render on screen
        return convertView;


    }



    // View lookup cache
    private static class ViewHolder {
        TextView name;
        TextView phone;
        Button delBtn;
    }


}
