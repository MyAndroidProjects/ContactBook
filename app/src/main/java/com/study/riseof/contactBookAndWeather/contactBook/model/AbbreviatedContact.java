package com.study.riseof.contactBookAndWeather.contactBook.model;

public class AbbreviatedContact {

    private String contactAbbrName;
    private int itemId;
    private String firstName;
    private String secondNameInitialLetter;
    private String patronymicInitialLetter;
    private String lastName;

    public AbbreviatedContact(int id, String contactAbbrName) {
        this.itemId = id;
        this.contactAbbrName = contactAbbrName;
    }

    public AbbreviatedContact(Contact contact) {
        this.itemId = contact.getId();
        this.lastName = contact.getLastName();
        this.firstName = contact.getFirstName();
        this.secondNameInitialLetter = contact.getSecondNameInitialLetter();
        this.patronymicInitialLetter = contact.getPatronymicInitialLetter();
        this.contactAbbrName = lastName + " " + firstName + " " + secondNameInitialLetter + " " + patronymicInitialLetter;
    }

    public AbbreviatedContact(int id, String lastName, String firstName,
                              String secondNameInitialLetter, String patronymicInitialLetter) {
        this.itemId = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondNameInitialLetter = secondNameInitialLetter;
        this.patronymicInitialLetter = patronymicInitialLetter;

        this.contactAbbrName = this.lastName + " " + this.firstName + " " + this.secondNameInitialLetter
                + " " + this.patronymicInitialLetter;
    }


    public void setItemId(int databaseId) {
        this.itemId = databaseId;
    }

    public void setContactName(String contactName) {
        this.contactAbbrName = contactName;
    }

    public int getItemId() {
        return itemId;
    }

    public String getContactName() {
        return contactAbbrName;
    }
}
