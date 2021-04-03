package com.example.dietapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {

    // "ImageAdapter" Class was created in order to fill the GridView (in fron-end) with images from drawable folder
    //... also this class is called by PhotosActivity.java Class

    private Context mContext;

    //Array with 4 images which will fill the grid
    public int[] imageArray = {
        R.drawable.diet_center1,
        R.drawable.diet_center2,
        R.drawable.diet_center3,
        R.drawable.diet_center4
    };

    //Create the constructor for this class
    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //Functions below are inherited by BaseAdapter Class

    //This function returns the length of images' array
    @Override
    public int getCount() {
        return imageArray.length;
    }

    //This function gets the element = image of array according to a given position
    @Override
    public Object getItem(int position) {
        return imageArray[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //This function returns all photos (#4 photos from array) to GridView and make them visible to user
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //For each element (= photo) in array...

        //... 1) create an image view
        ImageView imageView = new ImageView(mContext);

        //... 2) take the photo from array and set it to the ImageView
        imageView.setImageResource(imageArray[position]);

        //... 3) crop the ImageView (crop the photo in ImageView) to the center
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        //... 4) define the size of this ImageView (width & height)
        imageView.setLayoutParams(new GridView.LayoutParams(660,  670 ));

        //... and finally return this image view
        return imageView;
    }
}
