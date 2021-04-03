package com.example.dietapp;

//This class was made in order to create the "array" in which we will save the data from contact form to Firebase DB
public class Contact {

    //private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    //Constructor 1 (Empty Constructor)
    public Contact() {

    }

    //Constructor 2
    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    //Setters and Getters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
