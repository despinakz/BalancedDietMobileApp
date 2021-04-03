package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;



public class PhotosActivity extends AppCompatActivity {

    //Define a GridView which will be filled with photos from ImageAdapter Class and make it visible to the front-end
    GridView galleryGridView;

     @Override
    protected void onCreate(Bundle savedInstanceState) {

         super.onCreate(savedInstanceState);

         //load the layout
         setContentView(R.layout.activity_photos);


         //Get the GridView from front-end...
         galleryGridView = findViewById(R.id.gridView);

         //... and set an adapter to it, more specifically we create an ImageAdapter (= the class which we created before)
         galleryGridView.setAdapter(new ImageAdapter(this));


         if (getSupportActionBar() != null) //This IF-Statement exists in order to prevent Null Pointer Exception
         {
             //... set a title to action bar
             getSupportActionBar().setTitle(getText(R.string.menu_photos));

             //... and show up the arrow icon in order to redirect to "Who We Are?" screen
             getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         }


         //At this point we set a click-listener for each item (= photo) in GridView
        galleryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 //Create an intent which will send it to FullScreenActivity
                 Intent intent = new Intent (getApplicationContext(), FullScreenActivity.class);

                 //we put some values to this intent, especially we will send the id of the GridView's item (photo) which was clicked
                 intent.putExtra("id", position);

                 //and finally we don't forget to "fire"(=start) the intent
                 startActivity(intent);
             }
         });
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

         // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
