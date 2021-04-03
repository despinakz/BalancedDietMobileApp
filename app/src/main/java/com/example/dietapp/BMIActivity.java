package com.example.dietapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;



public class BMIActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnFocusChangeListener{

    //Define all view-objects will be useful in order to get these views from layout activity_main.xml
    private Spinner genderSpinner;
    private EditText weightEditText;
    private EditText heightEditText;
    private Button bmiButton;
    private TextView bmiResultTextView;
    private TextInputLayout weightTextInputLayout;
    private TextInputLayout heightTextInputLayout;

    //Define some additional values will be useful for bmi calculation
    private float bmi;
    private String bmi_result;
    private String gender;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //load the layout
        setContentView(R.layout.activity_bmi);

        //take the image view from layout
        ImageView imageView = findViewById(R.id.bmiImageView);

        //give a source (= an image) to this imageview
        imageView.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.grapes, 500, 500 ));

        if (getSupportActionBar() != null){ //This IF-Statement exists in order to prevent Null Pointer Exception

            //... set a title to action bar
            getSupportActionBar().setTitle(getText(R.string.bmiTitle1));

            //... and show up the arrow icon in order to redirect to "Who We Are?" screen
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Get views from layout
        genderSpinner = findViewById(R.id.genderSpinner);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        bmiButton = findViewById(R.id.bmiButton);
        bmiResultTextView = findViewById(R.id.bmiResultTextView);
        weightTextInputLayout = findViewById(R.id.weightTextInputLayout);
        heightTextInputLayout = findViewById(R.id.heightTextInputLayout);

        //connect the spinner with OnItemSelected listener
        genderSpinner.setOnItemSelectedListener(this);

        //connect weigth & height edit texts with OnFocus listener
        weightEditText.setOnFocusChangeListener(this);
        heightEditText.setOnFocusChangeListener(this);

        //Connect weight edit text with addTextChanged listener
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //When user writing on this edit text then we clear the bmi result and error message about field validity
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                weightTextInputLayout.setErrorEnabled(false);
                bmiResultTextView.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Connect height edit text with addTextChanged listener
        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //When user writing on this edit text then we clear the bmi result and hide the error message about field validity
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                heightTextInputLayout.setErrorEnabled(false);
                bmiResultTextView.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


    //This function calculates the bmi for Females
    public String calcBMI4Female(EditText w, EditText h){

        //get the user input from weight edit text and parse it to float number
        String weightString = w.getText().toString();
        float weight = Float.parseFloat(weightString);

        //get the user input from height edit text and parse it to float number
        String heightString = h.getText().toString();
        float height = Float.parseFloat(heightString);

        //Calculate the bmi = bmi formula
        bmi = (weight/height/height)*10000;

        //we rounded in 2 places the bmi result using the round function which created before
        bmi = round(bmi, 2);


        if (bmi < 18.50)
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Underweight"; //bmi result
            return bmi_result; //return bmi result
        }
        else if ( (bmi >= 18.50) && (bmi <= 23.59))
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Normal Weight";
            return bmi_result;
        }
        else if ( (bmi >= 23.60) && (bmi <= 28.69))
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Obese 1st degree";
            return bmi_result;
        }
        else if ( (bmi >= 28.70) && (bmi <= 40))
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Obese 2nd degree";
            return bmi_result;
        }
        else if (bmi > 40)
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Obese 3rd degree";
            return bmi_result;
        }
        else
        {
            bmi_result = "Something went wrong with BMI calculation!";
            return bmi_result;
        }

    }

    //This function calculates the bmi for Males
    public String calcBMI4Male(EditText w, EditText h){

        //get the user input from weight edit text and parse it to float number
        String weightString = w.getText().toString();
        float weight = Float.parseFloat(weightString);

        //get the user input from height edit text and parse it to float number
        String heightString = h.getText().toString();
        float height = Float.parseFloat(heightString);

        //Calculate the bmi = bmi formula
        bmi = (weight/height/height)*10000;

        //we rounded in 2 places the bmi result using the round function which created before
        bmi = round(bmi, 2);

        if (bmi < 19.50)
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Underweight"; //bmi result
            return bmi_result; //return bmi result
        }
        else if ( (bmi >= 19.50) && (bmi <= 24.99))
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Normal Weight";
            return bmi_result;
        }
        else if ( (bmi >= 25) && (bmi <= 29.99))
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Obese 1st degree";
            return bmi_result;
        }
        else if ( (bmi >= 30) && (bmi <= 40))
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Obese 2nd degree";
            return bmi_result;
        }
        else if (bmi > 40)
        {
            bmi_result = "Your BMI is: " +bmi +"\n -Obese 3rd degree";
            return bmi_result;
        }
        else
        {
            bmi_result = "Something went wrong with BMI calculation!";
            return bmi_result;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        bmiResultTextView.setText("");
        if (position==0) //Female gender selected from Spinner
        {
            gender = "Female"; //give value to gender variable
        }
        if (position==1) //Male gender selected from Spinner
        {
            gender = "Male"; //give value to gender variable
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void calc(View v){

        //Make the appopriate validation for weigth and heigth edit text values
        boolean formVlidationResult = bmiFormValidation(); //returns true or false

        if (formVlidationResult) //If weight and height edit text values are valid..
        {
            if (gender.equals("Female")) //..then if gender which selected was female
            {
                String res = calcBMI4Female(weightEditText, heightEditText); //then call the bmi calc function for females
                                                                                //and save the bmi result to a variable
                bmiResultTextView.setText(res); //take the bmi result and write it to a text view in layout
            }
            else {
                String res = calcBMI4Male(weightEditText, heightEditText); //otherwise call the bmi calc function for males
                                                                            //and save the bmi result to a variable
                bmiResultTextView.setText(res); //take the bmi result and write it to a text view in layout
            }
        }

    }

    //With this function we make the validation for weight and height edit text values
    public boolean bmiFormValidation(){

        //make the assumption that all are valid
        boolean isValid = true;

        //Weigth input field (Edit TextView) validation
        if (weightEditText.getText().toString().isEmpty()){ //if this edit text is empty

            //show an error message and make the editext red
            weightTextInputLayout.setError(getText(R.string.weightEmptyNotice));

            //change the isValid variable to false
            isValid = false;
        }
        else //if this edit text is NOT empty
        {
            //if weight edit text value is valid according to following regex & weight has maximum 7 characters-length
            if ( (weightEditText.getText().toString().matches("^(?:.\\d+|(?:\\d|[1-9]\\d|[1-7]\\d{2})(?:.\\d*)?|800(?:.0*)?)(?:;\\s*(?:.\\d+|(?:\\d|[1-9]\\d|[1-7]\\d{2})(?:.\\d*)?|800(?:.0*)?))*$"))
                    && (weightEditText.getText().toString().trim().length()<=7) )
            {
                //..then remove the error message and the red color from edit text
                weightTextInputLayout.setErrorEnabled(false);
            }
            else { //if does not satisfy the rules above

                //then show another error message and make the editext red
                weightTextInputLayout.setError(getText(R.string.weightValidNotice));

                //change the isValid variable to false
                isValid = false;
            }
        }

        //Heigth input field (Edit TextView) validation
        //Here we follow the same logic as weight validation above
        if (heightEditText.getText().toString().isEmpty()){
            heightTextInputLayout.setError(getText(R.string.heightEmptyNotice));
            isValid = false;
        }
        else
        {
            if (heightEditText.getText().toString().matches("[1-2]\\d+(?:[.]\\d+)?(?=\\s*)") && (heightEditText.getText().toString().trim().length()<=7))
            {
                heightTextInputLayout.setErrorEnabled(false);
            }
            else {
                heightTextInputLayout.setErrorEnabled(false);
                heightTextInputLayout.setError(getText(R.string.heightValidNotice));
                isValid = false;
            }
        }

        return isValid; //return if some value is valid or not
    }

    //The function below exists in order to prevent the load image exception which sometimes happens due to height image size
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
        {
            bmiResultTextView.setText("");

            if (v.getId() == weightEditText.getId()){
                weightTextInputLayout.setErrorEnabled(false);
            }

            if (v.getId() == heightEditText.getId()){
                heightTextInputLayout.setErrorEnabled(false);
            }

        }
    }


    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static float round(float value, int places){
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp/factor;
    }
}
