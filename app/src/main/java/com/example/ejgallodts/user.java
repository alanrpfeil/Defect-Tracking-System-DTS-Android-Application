package com.example.ejgallodts;

public class user {

    String firstname;
    String lastname;
    String email;
    boolean isAdmin;
    String department;
    String primarylocation;
    String secondarylocation;
    String user_site;
    String user_subdepartment;

    public void user() {
        firstname = "";
        lastname = "";
        email = "";
        isAdmin = false;
        department = "";
        primarylocation = "";
        secondarylocation = "";
        user_site = "";
        user_subdepartment = "";
    }

    public void user(String firstname, String lastname, String email, boolean isAdmin, String department, String primarylocation, String secondarylocation, String user_site, String user_subdepartment) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.isAdmin = isAdmin;
        this.department = department;
        this.primarylocation = primarylocation;
        this.secondarylocation = secondarylocation;
        this.user_site = user_site;
        this.user_subdepartment = user_subdepartment;

    }


}
