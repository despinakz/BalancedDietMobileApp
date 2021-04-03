package com.example.dietapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TeamActivity extends AppCompatActivity {

    //Define a ViewPager object
    private ViewPager slideViewPager;

    //Define a LinearLayout object
    private LinearLayout dotsLinearLayout;

    //Define a TextView array
    private TextView[] dots;

    //Define a SlideAdapter object
    private SlideAdapter slideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the layout
        setContentView(R.layout.activity_team);


        if (getSupportActionBar() != null){ //This IF-Statement exists in order to prevent Null Pointer Exception

            //... set a title to action bar
            getSupportActionBar().setTitle(getText(R.string.ourTeam));

            //... and show up the arrow icon in order to redirect to "Who We Are?" screen
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Take the slider view pager from layout
        slideViewPager = findViewById(R.id.slideViewPager);

        //Take the linear layout from activity layout
        dotsLinearLayout = findViewById(R.id.dotsLinearLayout);

        //Create a SliderAdapter object
        slideAdapter = new SlideAdapter(this);

        //and set this SlideAdapter object to slide view pager object
        slideViewPager.setAdapter(slideAdapter);

        //call addDotsIndicators function
        addDotsIndicators(0);

        //connect the slide view pager with OnClick listener
        slideViewPager.addOnPageChangeListener(viewListener);

    }

    //This function creates the slider dots which are the indicators of our slider
    public void addDotsIndicators(int position){
        //we want 4 dots as we have 4 team members to show
        dots = new TextView[4];

        //clear the linear layout from other views
        dotsLinearLayout.removeAllViews();

        //dots = new TextView[4];
        //For each value of dots array = for each indicator we make the look and feel of them...
        for (int i = 0; i< dots.length; i++){
            //create a tex view
            dots[i] = new TextView(this);

            //give the dot-look and feel on indicator
            dots[i].setText(Html.fromHtml("&#8226"));

            //define the size of the indicator
            dots[i].setTextSize(35);

            //give a color --> light grey
            dots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));

            //add each indicator to the linear layout
            dotsLinearLayout.addView(dots[i]);
        }

        //according to what slide we see every time, the indicator (= the dot) change color --> white color
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    //Connect the view pager with onPageChange listener
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //while slide changes, then call the addDotsIndicators in order to change the color of the appropriate indicator
        @Override
        public void onPageSelected(int position) {
            addDotsIndicators(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
