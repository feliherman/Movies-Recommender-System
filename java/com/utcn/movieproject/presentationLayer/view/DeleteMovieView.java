package com.utcn.movieproject.presentationLayer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.utcn.movieproject.R;
import com.utcn.movieproject.businessLayer.MyListAdapter;

public class DeleteMovieView extends AppCompatActivity {
    ListView listView;
    String[] maintitle = {
            "Title 1", "Title 2",
            "Title 3", "Title 4",
            "Title 5",
    };

    String[] subtitle = {
            "Sub Title 1", "Sub Title 2",
            "Sub Title 3", "Sub Title 4",
            "Sub Title 5",
    };

    Integer[] imgid = {
            R.drawable.draw2, R.drawable.draw2,
            R.drawable.draw2, R.drawable.draw2,
            R.drawable.draw2,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_movie_view);

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid);
        listView = findViewById(R.id.list_view_deleteMovie);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if (position == 0) {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(), "DELETE THIS?", Toast.LENGTH_SHORT).show();
                } else if (position == 1) {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(), "DELETE THIS?", Toast.LENGTH_SHORT).show();
                } else if (position == 2) {

                    Toast.makeText(getApplicationContext(), "DELETE THIS?", Toast.LENGTH_SHORT).show();
                } else if (position == 3) {

                    Toast.makeText(getApplicationContext(), "DELETE THIS?", Toast.LENGTH_SHORT).show();
                } else if (position == 4) {

                    Toast.makeText(getApplicationContext(), "DELETE THIS?", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}