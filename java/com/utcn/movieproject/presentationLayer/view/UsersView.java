package com.utcn.movieproject.presentationLayer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.utcn.movieproject.R;
import com.utcn.movieproject.businessLayer.MyListAdapter;

public class UsersView extends AppCompatActivity {
    ListView listView;
    String[] maintitle = {
            "Name Surname","Name Surname",
            "Name Surname", "Name Surname",
            "Name Surname",
    };

    String[] subtitle = {
            "INFO", "INFO",
            "INFO", "INFO",
            "INFO",
    };

    Integer[] imgid = {
            R.drawable.useravatar, R.drawable.useravatar,
            R.drawable.useravatar, R.drawable.useravatar,
            R.drawable.useravatar,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_view);

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid);
        listView = findViewById(R.id.list_view_users);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(), "Place Your First Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(), "Place Your Second Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {

                    Toast.makeText(getApplicationContext(), "Place Your Third Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 3) {

                    Toast.makeText(getApplicationContext(), "Place Your Forth Option Code", Toast.LENGTH_SHORT).show();
                } else if (position == 4) {

                    Toast.makeText(getApplicationContext(), "Place Your Fifth Option Code", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}