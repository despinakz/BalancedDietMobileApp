package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullScreenActivity extends AppCompatActivity {

    //Create an ImageView
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the layout
        setContentView(R.layout.activity_full_screen);

        //get the existed ImageView from the layout
        imageView = findViewById(R.id.imgView);


        if (getSupportActionBar() != null){ //This IF-Statement exists in order to prevent Null Pointer Exception

            //... set a title to action bar
            getSupportActionBar().setTitle("Full Screen Image");

            //... and show up the arrow icon in order to redirect to "Photos" screen
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //get the intent, as this Class has the role of the "receiver" in this Intent-concept
        Intent i = getIntent();

        //also get the value of id-key --> which is the position of photo which was clicked before from PhotoActivity front-end
                                                //..and shows us the position which this image has on imageArray (in ImageAdapter class)
        int position = (int) i.getExtras().get("id");

        //we create an object of ImageAdapter Class (which created before)
        ImageAdapter imageAdapter = new ImageAdapter(this);

        //..and finally we fill this blank ImageView with the photo that is located in imageArray (with specific position)
        imageView.setImageResource(imageAdapter.imageArray[position]);
    }
}
