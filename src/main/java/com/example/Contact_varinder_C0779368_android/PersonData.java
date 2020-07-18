package com.example.Contact_varinder_C0779368_android;

public class PersonData {

    int id;
    String firstName;
    String lastName;
    String phoneNumber;
    String address;
String email;

    public PersonData(int id, String firstName, String lastName, String phoneNumber, String address, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}
