package com.example.dietapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;



//Create a class called SlideAdapter which extends the PageAdapter class and will help us to make a slider functionality on Our Team screen
//... also this class is called by TeamActivity.java Class
public class SlideAdapter extends PagerAdapter{

    //Define a Context object
    private Context context;

    //Define a LayoutInflater object
    private LayoutInflater layoutInflater;


    //Constructor of SlideAdapter Class
    public SlideAdapter (Context context)
    {
        this.context = context;
    }


    //4 arrays below will be used to fill each CardView of the slider with info which are saved to these arrays
        //...Note: we didn't find a better way to save these info to strings.xml (and fill each array with string-array)..
            //..so we decided to write the values with the way you see below

    //Array with 4 images --> one image for each CardView (each CardView illustrates a team member)
    private int[] sliderImages = {R.drawable.lemon, R.drawable.pink, R.drawable.kiwi, R.drawable.pineapples};

    //Array with names of each team member
    public String[] sliderHeadings = {"Jessica Hugle-Foster","Melissa Wadolowski","Melissa Parisi","Cheri R. Leahy"};

    //Array with the specialties of each team member
    public String[] sliderSp = {"Health Coach","Nutrition Education Specialist","Dietitian Expert","Nutritionist and Health expert"};

    //Array with the bio of each team member
    public String[] sliderDetails = {
            "Jessica Hugle-Foster is the health coach for the Nutrition Center at Jefferson Health in New Jersey.Jessica graduated Summa Cum Laude from Rowan University, where she specialized in Health Promotion and Fitness Management." +
                    "At Rowan, Jessica also earned her certification in Motivational Interviewing for the purposes of facilitating health behavior change." +
                    "She is also a certified Health Coach and has a specialization in Behavior Change through the American Council on Exercise (ACE).",

            "Melissa Wadolowski, RD, LDN, CHC, a 2011 Cum Laude graduate of Queens College, Flushing, NY is a Nutrition Education Specialist," +
                    "Registered Dietitian under the Commission of Dietetic Registration and a Licensed Dietitian Nutritionist" +
                    "Melissa completed her dietetic internship with Sodexo in Philadelphia, PA. She has been with the Jefferson nutrition team since 2016" +
                    "and earned her Certified Health Coach credential in January of 2017.",

            "Melissa Parisi, RD, is a 2019 Summa Cum Laude graduate from the Rutgers School of Health Professions program," +
                    "a Registered Dietitian through the Commission on Dietetic Registration, and Nutrition Education Specialist." +
                    "Originally obtaining a bachelors degree in Advertising from Rowan University and working in television for five years," +
                    "Melissa decided to make a career change into the Nutrition and Dietetics field in order to pursue her passion of helping others" +
                    "build healthier lifestyles through a “food first” approach whenever possible.",

            "Cheri Leahy, RDN is the Director of Nutrition for Bariatric Surgery at Jefferson Health in New Jersey." +
                    "A passionate nutritionist and health expert, Cheri earned her Bachelor of Science Degree in Nutrition and Dietetics from Meredith" +
                    "College in Raleigh, NC, and completed a graduate program and clinical internship at Duke University Medical Center in Durham, NC." +
                    "Certified by the Academy of Nutrition and Dietetics, Cheri has presented nutrition and wellness" +
                    "seminars nationally and authored a cookbook titled, “Portion 8.”"
    };


    //This function returns the length of the sliderHeadings array
    @Override
    public int getCount() {
        return sliderHeadings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }


    //The function below fills the layout with arrays' context
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;

        //inflate the slide_layout.xml layout
        View view = layoutInflater.inflate(R.layout.slide_layout, container,false);

        //we take each view from the slide_layout.xml
        ImageView slideImageView = (ImageView) view.findViewById(R.id.slideImageView);
        TextView slideHeading = (TextView) view.findViewById(R.id.memberTextView);
        TextView slideDetails = (TextView) view.findViewById(R.id.memberDetailsTextView);
        TextView slideSp = (TextView) view.findViewById(R.id.memberSpTextView);

        //We "fill" each view from the slide_layout.xml with values from arrays we defined before
        //take the image with x position from sliderImages array
        slideImageView.setImageResource(sliderImages[position]);

        //take the name with x position from sliderHeadings array
        slideHeading.setText(sliderHeadings[position]);

        //take the bio with x position from sliderDetails array
        slideDetails.setText(sliderDetails[position]);

        //take the specialty with x position from slideSp array
        slideSp.setText(sliderSp[position]);

        //add view to container
        container.addView(view);

        //finally return the view
        return view;
    }

    //This function remove view from layout
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
