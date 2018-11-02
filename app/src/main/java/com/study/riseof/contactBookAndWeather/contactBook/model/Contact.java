package com.study.riseof.contactBookAndWeather.contactBook.model;

public class Contact {
    private final String EMPTY_STRING = "";
    private final String POINT = ".";
    private final int INITIAL_LETTER_INDEX = 0;

    private int id;
    private String firstName;
    private String firstNameInitialLetter;
    private String secondName;
    private String secondNameInitialLetter;
    private String patronymic;
    private String patronymicInitialLetter;
    private String lastName;
    private String lastNameInitialLetter;
    private String mobilePhone;
    private String homePhone;
    private String personalWebsite;
    private String eMail;
    private String flat;
    private String house;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postCode;

    public Contact() {
        this.firstName = EMPTY_STRING;
        this.firstNameInitialLetter = EMPTY_STRING;
        this.secondName = EMPTY_STRING;
        this.secondNameInitialLetter = EMPTY_STRING;
        this.patronymic = EMPTY_STRING;
        this.patronymicInitialLetter = EMPTY_STRING;
        this.lastName = EMPTY_STRING;
        this.lastNameInitialLetter = EMPTY_STRING;
        this.mobilePhone = EMPTY_STRING;
        this.homePhone = EMPTY_STRING;
        this.personalWebsite = EMPTY_STRING;
        this.eMail = EMPTY_STRING;
        this.flat = EMPTY_STRING;
        this.house = EMPTY_STRING;
        this.street = EMPTY_STRING;
        this.city = EMPTY_STRING;
        this.state = EMPTY_STRING;
        this.country = EMPTY_STRING;
        this.postCode = EMPTY_STRING;
    }

    public Contact(String firstName,
                   String firstNameInitialLetter,
                   String secondName,
                   String secondNameInitialLetter,
                   String patronymic,
                   String patronymicInitialLetter,
                   String lastName,
                   String lastNameInitialLetter,
                   String mobilePhone,
                   String homePhone,
                   String personalWebsite,
                   String eMail,
                   String flat,
                   String house,
                   String street,
                   String city,
                   String state,
                   String country,
                   String postCode) {
        this.firstName = firstName;
        this.firstNameInitialLetter = firstNameInitialLetter;
        this.secondName = secondName;
        this.secondNameInitialLetter = secondNameInitialLetter;
        this.patronymic = patronymic;
        this.patronymicInitialLetter = patronymicInitialLetter;
        this.lastName = lastName;
        this.lastNameInitialLetter = lastNameInitialLetter;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.personalWebsite = personalWebsite;
        this.eMail = eMail;
        this.flat = flat;
        this.house = house;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public Contact(int id,
                   String firstName,
                   String firstNameInitialLetter,
                   String secondName,
                   String secondNameInitialLetter,
                   String patronymic,
                   String patronymicInitialLetter,
                   String lastName,
                   String lastNameInitialLetter,
                   String mobilePhone,
                   String homePhone,
                   String personalWebsite,
                   String eMail,
                   String flat,
                   String house,
                   String street,
                   String city,
                   String state,
                   String country,
                   String postCode) {
        this.id = id;
        this.firstName = firstName;
        this.firstNameInitialLetter = firstNameInitialLetter;
        this.secondName = secondName;
        this.secondNameInitialLetter = secondNameInitialLetter;
        this.patronymic = patronymic;
        this.patronymicInitialLetter = patronymicInitialLetter;
        this.lastName = lastName;
        this.lastNameInitialLetter = lastNameInitialLetter;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.personalWebsite = personalWebsite;
        this.eMail = eMail;
        this.flat = flat;
        this.house = house;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
    }

    public Contact(RandomContact randomContact) {
        this.firstName = randomContact.getFirstName();
        this.firstNameInitialLetter = randomContact.getFirstNameInitialLetter();
        this.secondName = randomContact.getSecondName();
        this.secondNameInitialLetter = randomContact.getSecondNameInitialLetter();
        this.patronymic = randomContact.getPatronymic();
        this.patronymicInitialLetter = randomContact.getPatronymicInitialLetter();
        this.lastName = randomContact.getLastName();
        this.lastNameInitialLetter = randomContact.getLastNameInitialLetter();
        this.mobilePhone = randomContact.getMobilePhone();
        this.homePhone = randomContact.getHomePhone();
        this.personalWebsite = randomContact.getPersonalWebsite();
        this.eMail = randomContact.getEMail();
        this.flat = randomContact.getFlat();
        this.house = randomContact.getHouse();
        this.street = randomContact.getStreet();
        this.city = randomContact.getCity();
        this.state = randomContact.getState();
        this.country = randomContact.getCountry();
        this.postCode = randomContact.getPostCode();
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEMail() {
        return eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFlat() {
        return flat;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getHouse() {
        return house;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getFirstNameInitialLetter() {
        return firstNameInitialLetter;
    }

    public String getSecondNameInitialLetter() {
        return secondNameInitialLetter;
    }

    public String getPatronymicInitialLetter() {
        return patronymicInitialLetter;
    }

    public String getLastNameInitialLetter() {
        return lastNameInitialLetter;
    }
}
