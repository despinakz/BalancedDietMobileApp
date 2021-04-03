package com.example.dietapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

//This class make the validation for each edit text form the layout and if all edit texts' values are valid then we save the data to
    //a Firebase Realtime DB
public class ContactActivity extends AppCompatActivity implements View.OnFocusChangeListener, View.OnClickListener {

    //Define all view-objects will be useful in order to get these views from layout activity_contact.xml
    private TextInputEditText fNameTextInputEditText;
    private TextInputEditText lNameTextInputEditText;
    private TextInputEditText phoneTextInputEditText;
    private TextInputEditText emailTextInputEditText;

    private TextInputLayout fNameTextInputLayout;
    private TextInputLayout lNameTextInputLayout;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout emailTextInputLayout;

    private Button saveBtn;

    //Define a DatabaseReferance which will be very useful in order to make the connection with BD
    DatabaseReference db_conn;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //load the layout
        setContentView(R.layout.activity_contact);

        if (getSupportActionBar() != null){ //This IF-Statement exists in order to prevent Null Pointer Exception

            //... set a title to action bar
            getSupportActionBar().setTitle(getText(R.string.contactTitle));

            //...change the color of action bar
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#90d0c2")));

            //... and show up the arrow icon in order to redirect to "Who We Are?" screen
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        //change the color of status bar as well
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorLightBlue));

        //get the views from layout
        fNameTextInputEditText = findViewById(R.id.fnameTextInputEditText);
        lNameTextInputEditText = findViewById(R.id.lnameTextInputEditText);
        phoneTextInputEditText = findViewById(R.id.phoneTextInputEditText);
        emailTextInputEditText = findViewById(R.id.emailTextInputEditText);

        fNameTextInputLayout = findViewById(R.id.fNameTextInputLayout);
        lNameTextInputLayout = findViewById(R.id.lNameTextInputLayout);
        phoneTextInputLayout = findViewById(R.id.phoneTextInputLayout);
        emailTextInputLayout = findViewById(R.id.emailTextInputLayout);

        saveBtn = findViewById(R.id.saveButton);

        //connect all edit text views with OnFocus listener
        fNameTextInputEditText.setOnFocusChangeListener(this);
        lNameTextInputEditText.setOnFocusChangeListener(this);
        phoneTextInputEditText.setOnFocusChangeListener(this);
        emailTextInputEditText.setOnFocusChangeListener(this);

        //we connect the button with onClick listener
        saveBtn.setOnClickListener(this);

        //Connect first name edit text with addTextChanged listener
        fNameTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //When user writing on this edit text then we clear the error message about field validity
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fNameTextInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Connect last name edit text with addTextChanged listener
        lNameTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //When user writing on this edit text then we clear the error message about field validity
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                lNameTextInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Connect phone name edit text with addTextChanged listener
        phoneTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //When user writing on this edit text then we clear the error message about field validity
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneTextInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Connect email edit text with addTextChanged listener
        emailTextInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //When user writing on this edit text then we clear the error message about field validity
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailTextInputLayout.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //make the connection with Firebase DB and create a child name "Contact" in which we will save data from each user
        db_conn = FirebaseDatabase.getInstance().getReference().child("Contact");


    }

    //When Save button is clicked then we call f_start function
    @Override
    public void onClick(View v) {
        f_start();
    }

    public void f_start(){

        boolean validationResult = contactFormValidation(); //call contactFormValidation which will return if all edit texts views' values
                                                            // were valid or not
        String first_name = "";
        String  last_name = "";
        String  phone_number = "";
        String  email_address = "";

        if (validationResult) { //If all edit text views' values were VALID..then..


            if (fNameTextInputEditText.getText() !=null){
                //get what user wrote in this edit text view
                first_name = fNameTextInputEditText.getText().toString();
            }

            if (lNameTextInputEditText.getText() !=null){
                //get what user wrote in this edit text view
                last_name = lNameTextInputEditText.getText().toString().trim();
            }

            if (phoneTextInputEditText.getText() != null){
                //get what user wrote in this edit text view
                phone_number = phoneTextInputEditText.getText().toString().trim();
            }

            if (emailTextInputEditText.getText() != null){
                //get what user wrote in this edit text view
                email_address = emailTextInputEditText.getText().toString().trim();
            }

            //create a Contact object with parameters be the data which user wrote
            final Contact contact = new Contact(first_name, last_name, phone_number, email_address);

            //create a query that checks ifthere is a child in Contact-child in firebase which has the email which user wrote
            Query email_query = db_conn.orderByChild("emailAddress").equalTo(email_address);

            //on this query we set a listener
            email_query.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //if email exists in Firebase already..
                    if (snapshot.getChildrenCount() > 0) {
                        //..then make an appopriate toast
                        Toast.makeText(ContactActivity.this, "You are already registered to our contacts!", Toast.LENGTH_SHORT).show();
                    }
                    else { //otherwise

                        //save the data on a new child inside Contact-child in Firebase
                        db_conn.push().setValue(contact);

                        //make an appopriate toast
                        Toast.makeText(ContactActivity.this, "Your data submitted successfully!", Toast.LENGTH_SHORT).show();

                        //clear the context from all edti text views
                        fNameTextInputEditText.getText().clear();
                        lNameTextInputEditText.getText().clear();
                        phoneTextInputEditText.getText().clear();
                        emailTextInputEditText.getText().clear();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }


    }




    public boolean contactFormValidation(){

        //make the assumption that all edit text views' values are invalid
        boolean allInputsAreValid = false;

        //call the appopriate function which make the validation for first name edit text
        boolean fnameIsValid = fnameIsValid();

        //call the appopriate function which make the validation for last name edit text
        boolean lnameIsValid = lnameIsValid();

        //call the appopriate function which make the validation for phone edit text
        boolean phoneIsValid = phoneIsValid();

        //call the appopriate function which make the validation for email edit text
        boolean emailIsValid = emailIsValid();

        if ((fnameIsValid)&&(lnameIsValid)&&(phoneIsValid)&&(emailIsValid)){ //if all edit text views' values are valid
            //change the variable below to true
            allInputsAreValid = true;

            //return this  variable
            return allInputsAreValid;
        }
        else{ //return false
            return allInputsAreValid;
        }
}

    //First Name Validation
    public boolean fnameIsValid(){
        //First Name input (EditText) validation

        //make the assumption that this field is invalid
        boolean isValid = true;

        if (fNameTextInputEditText.getText().toString().isEmpty()){ //if this edit text is empty
            //show an error message and make the editext red
            fNameTextInputLayout.setError(getText(R.string.fNameEmptyNotice));

            //change the isValid variable to false
            isValid = false;
        }
        else{//if this edit text is NOT empty

            //if first name edit text value is valid according to following regex
            if (fNameTextInputEditText.getText().toString().matches("^[a-zA-Z ]{3,76}$")){

                //..then remove the error message and the red color from edit text
                fNameTextInputLayout.setErrorEnabled(false);
            }
            else{ //if does not satisfy the regex rule above

                //then show another error message and make the editext red
                fNameTextInputLayout.setError(getText(R.string.fNameValidNotice));

                //change the isValid variable to false
                isValid = false;
            }

        }
        return isValid; //return if first name edit text view value is valid or not
    }

    //Last Name Validation
        //Exactly we use same logic here as first name validation
    public boolean lnameIsValid(){
        //Last Name input (EditText) validation

        boolean isValid = true;

        if (lNameTextInputEditText.getText().toString().isEmpty()){
            lNameTextInputLayout.setError(getText(R.string.lNameEmptyNotice));
            isValid = false;
        }
        else{
            if (lNameTextInputEditText.getText().toString().matches("^[a-zA-Z ]{3,76}$")){
                lNameTextInputLayout.setErrorEnabled(false);
            }
            else{
                lNameTextInputLayout.setError(getText(R.string.lNameValidNotice));
                isValid = false;
            }
        }
        return isValid;
    }

    //Phone nymber Validation
    //Exactly we use same logic here as first name validation
    public boolean phoneIsValid(){
        //Phone Number input (EditText) validation

        boolean isValid = true;

        if (phoneTextInputEditText.getText().toString().isEmpty()){
            phoneTextInputLayout.setError(getText(R.string.phoneEmptyNotice));
            isValid = false;
        }
        else{
            if (phoneTextInputEditText.getText().toString().matches("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$")
                    && (phoneTextInputEditText.getText().toString().trim().length()>=10) ){
                phoneTextInputLayout.setErrorEnabled(false);
            }
            else{
                phoneTextInputLayout.setError(getText(R.string.phoneValidNotice));
                isValid = false;
            }

        }
        return isValid;
    }

    //Email Validation
    //Exactly we use same logic here as first name validation
    public boolean emailIsValid(){
        //Email Address input (EditText) validation

        boolean isValid = true;

        if (emailTextInputEditText.getText().toString().isEmpty()){
            emailTextInputLayout.setError(getText(R.string.emailEmptyNotice));
            isValid = false;
        }
        else{
            if (emailTextInputEditText.getText().toString().matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
                emailTextInputLayout.setErrorEnabled(false);
            }
            else{
                emailTextInputLayout.setError(getText(R.string.emailValidNotice));
                isValid = false;
            }
        }
        return isValid;
    }

    //If one of edit texts gets focus then we remove error messages from them
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (v.getId() == fNameTextInputEditText.getId()) { //focus on first name edit text
                fNameTextInputLayout.setErrorEnabled(false);
            }

            if (v.getId() == lNameTextInputEditText.getId()) { //focus on last name edit text
                lNameTextInputLayout.setErrorEnabled(false);
            }

            if (v.getId() == phoneTextInputEditText.getId()) { //focus on phone edit text
                phoneTextInputLayout.setErrorEnabled(false);
            }

            if (v.getId() == emailTextInputEditText.getId()) { //focus on email edit text
                emailTextInputLayout.setErrorEnabled(false);
            }
        }
    }



}
