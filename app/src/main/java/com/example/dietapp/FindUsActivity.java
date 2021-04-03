package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FindUsActivity extends AppCompatActivity implements View.OnClickListener {

    //Define 2 TextViews
    private TextView phone;
    private TextView location;

    //We make the assumption that user gave permission for call-dial to the app
    private static final int REQUEST_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the layout
        setContentView(R.layout.activity_find_us);

        if (getSupportActionBar() != null) //This IF-Statement exists in order to prevent Null Pointer Exception
        {
            //... set a title to action bar
            getSupportActionBar().setTitle(getText(R.string.menu_info));

            //... and show up the arrow icon in order to redirect to "Who We Are?" screen
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Create an instance of the existent TextViews from layout --> especially get id of them
        //...get the id of 1st TextView = location address
        phone = findViewById(R.id.phoneTextView);

        //...get the id of 2nd TextView = phone number of "Balanced Diet" center
        location = findViewById(R.id.locationTextView);

        //Connect each of existent TextViews with OnClick Listener
        phone.setOnClickListener(this);
        location.setOnClickListener(this);
    }

    //This function checks if the user gave permission of makings calls on dial to application
        //...and if user gave permission --> we open the mobile's phone call dial
    public void openDial(){

        //if user gave permission
        if (ContextCompat.checkSelfPermission(FindUsActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){

            //get the phone which is showing up in the layout -> get the content = value of the TextView
            String phoneNumber = phone.getText().toString();

            //create an intent which practically opens the device's call dial
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +phoneNumber));

            //start the intent
            startActivity(phoneIntent);
        }
        else{
            //open a dialog which question the user to give permission
            ActivityCompat.requestPermissions(FindUsActivity.this,new String[] {Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }

    }


    //This function checking if user gave call-dial-permission to application or not
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){ //if user gave permission

            //if package manager saved the info about permission approval
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openDial(); //call the openDial function
            }
            else{
                //show up a toast --> "Access DENIED!"
                Toast.makeText(FindUsActivity.this, "Permission DENIED!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //This function redirects to Google Maps app with tagged-location of Diet Center
    public void openMap(){

        //parse in URI  type the coordinates of center's location and also we create a search query which is going to be intented to Google Map app
        Uri gmmIntentUri = Uri.parse("geo:40.0234123, -75.3231361?q="+location.getText().toString());

        //create an intent in which we set the package of google maps
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        //If user has installed the Google Maps application on his/her device...
        if (mapIntent.resolveActivity(getPackageManager()) != null){
            startActivity(mapIntent); //...then start the intent
        }
        else{ //..otherwise
            //..make a toast --> Your device not support Google Maps application!
            Toast.makeText(FindUsActivity.this, "Your device not support Google Maps application!", Toast.LENGTH_SHORT).show();
        }


    }

    //OnClick Listener
    @Override
    public void onClick(View v) {

        switch (v.getId()) { //Get the id of view which clicked
            case R.id.phoneTextView: //if the TextView which clicked was phone number TextView
                openDial(); //call the openDial function
                break; //break the switch
            case R.id.locationTextView: //if the TextView which clicked was location address TextView
                openMap(); //call the openMap function
                break; //break the switch
        }
    }
}
