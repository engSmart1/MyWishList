package com.example.hytham.mywishlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;

public class WishDetailActivity extends AppCompatActivity {
    private TextView title , content , date ;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_detail);
        title = (TextView) findViewById(R.id.detailsTitle);
        content = (TextView) findViewById(R.id.detailsTextView);
        date = (TextView) findViewById(R.id.detailsDateText);

        deleteButton = (Button) findViewById(R.id.deleteButton);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            title.setText(extras.getString("title"));
            date.setText("Created: " + extras.getString("date"));
            content.setText(" \" " + extras.getString("content") + " \"");

            final int id = extras.getInt("id");

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                    dba.deleteWish(id);

                    Toast.makeText(getApplicationContext() , " This wish is deleted now !" ,Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(WishDetailActivity.this , DisplayWishesActivity.class));
                }
            });
        }
    }
}
