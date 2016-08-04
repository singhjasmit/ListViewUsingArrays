package com.androidclass.listviewusingarrays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {



    private ArrayList<Contact> contactsList = new ArrayList<Contact>();

    Button addBtn;
    EditText addName, addPhone;
    ListView myListView;
    MyCustomAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createContactsList();


        addBtn = (Button) findViewById(R.id.btn_add);
        addBtn.setOnClickListener(this);

        addName = (EditText) findViewById(R.id.usr_name);
        addPhone = (EditText) findViewById(R.id.usr_phone);


        myListView = (ListView) findViewById(R.id.myListView);


        // Create the adapter to convert the array to views
        myAdapter = new MyCustomAdapter(this, contactsList);

        myListView.setAdapter(myAdapter);


        // React to user clicks on item
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long id) {
                // We know the View is a TextView so we can cast it
                Toast.makeText(MainActivity.this, "Item with id [" + id + "] - Position [" + position + "]  clicked", Toast.LENGTH_SHORT).show();

            }
        });


    }


    private void createContactsList() {
        contactsList.add(new Contact("James Hardy", "206-111-2222"));
        contactsList.add(new Contact("Jane Eyre", "206-123-2424"));
        contactsList.add(new Contact("John Balding", "206-333-2424"));

    }


    @Override
    public void onClick(View view) {
        String name = addName.getText().toString();
        String phone = addPhone.getText().toString();

        if ((name != null) && (phone != null)) {
            contactsList.add(new Contact(name, phone));
            myAdapter.notifyDataSetChanged();

        } else {
            Toast.makeText(getBaseContext(), "Name and Phone number are required", Toast.LENGTH_SHORT).show();
        }
    }
}
