package com.study.riseof.contactBookAndWeather.contactBook.model;

import java.util.Random;

public class RandomContact {
    private static final String[] COUNTRIES;
    private static final String[] STATES;
    private static final String[] CITIES;
    private static final String[] STREETS;

    static {
        COUNTRIES = new String[]{
                "", "Russia", "England", "Germany", "France", "Sweden",
                "Portugal", "Belgium", "Netherlands", "Spain", "Italy",
                "Greece", "Austria", "Switzerland", "Finland", "Iceland"
        };
        STATES = new String[]{
                "", "Crooked", "Curved", "Deep", "Flat", "High",
                "Hollow", "Low", "Narrow", "Refined", "Round",
                "Shallow", "Square", "Steep", "Straight", "Wide"
        };
        CITIES = new String[]{
                "", "Ancient", "Brief", "Early", "Fast", "Future",
                "Late", "Modern", "Old", "Old-fashioned", "Prehistoric",
                "Quick", "Rapid", "Slow", "Swift", "Young"
        };
        STREETS = new String[]{
                "", "Albino", "Black", "Blue", "Gray", "Green",
                "Indigo", "Magenta", "Ochre", "Orange", "Purple",
                "Red", "Ruby", "Sepia", "White", "Yellow"
        };
    }

    private final int LETTERS_QUANTITY = 26;
    private final int LETTER_CODE_UPPER_A = 65;
    private final int LETTER_CODE_LOWER_A = 97;
    private final int DIGIT_QUANTITY = 10;
    private final int POST_CODE_DIGIT_QUANTITY = 6;
    private final int FLATS_MAX_QUANTITY = 330;
    private final int HOUSES_MAX_QUANTITY = 150;
    private final int STRINGS_MIN_LENGTH = 3;
    private final int STRINGS_MAX_LENGTH = 10;
    private final int PHONE_NUMBER_MIN_LENGTH = 7;
    private final int PHONE_NUMBER_MAX_LENGTH = 11;
    private final String EMPTY_STRING = "";
    private final int PROBABILITY_OF_EMPTY_STRING = 25;
    private final int HUNDRED_PERCENT = 100;
    private final int INITIAL_LETTER_INDEX = 0;

    private String firstName;
    private String secondName;
    private String patronymic;
    private String lastName;
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
    private String firstNameInitialLetter;
    private String secondNameInitialLetter;
    private String patronymicInitialLetter;
    private String lastNameInitialLetter;

    private Random rand = new Random();

    public RandomContact() {
        setCity();
        setCountry();
        setEMail();
        setFirstName();
        setFlat();
        setHomePhone();
        setHouse();
        setLastName();
        setMobilePhone();
        setPatronymic();
        setPersonalWebsite();
        setPostCode();
        setSecondName();
        setState();
        setStreet();
        setFirstNameInitialLetter();
        setSecondNameInitialLetter();
        setPatronymicInitialLetter();
        setLastNameInitialLetter();
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

    private void setFirstName() {
        String firstName;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            firstName = EMPTY_STRING;
        } else {
            firstName = randomUpperLetter();
            firstName += wordImitationInLowerCase();
        }
        this.firstName = firstName;
    }

    private void setSecondName() {
        String secondName;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            secondName = EMPTY_STRING;
        } else {
            secondName = randomUpperLetter();
            secondName += wordImitationInLowerCase();
        }
        this.secondName = secondName;
    }

    private void setLastName() {
        String lastName;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            lastName = EMPTY_STRING;
        } else {
            lastName = randomUpperLetter();
            lastName += wordImitationInLowerCase();
        }
        this.lastName = lastName;
    }

    private void setPatronymic() {
        String patronymic;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            patronymic = EMPTY_STRING;
        } else {
            patronymic = randomUpperLetter();
            patronymic += wordImitationInLowerCase();
        }
        this.patronymic = patronymic;
    }

    private void setHomePhone() {
        StringBuilder phoneNumber;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            phoneNumber = new StringBuilder(EMPTY_STRING);
        } else {
            phoneNumber = new StringBuilder("+");
            int length = rand.nextInt(PHONE_NUMBER_MAX_LENGTH - PHONE_NUMBER_MIN_LENGTH) + PHONE_NUMBER_MIN_LENGTH;
            for (int i = 0; i < length; i++) {
                phoneNumber.append((Integer) rand.nextInt(DIGIT_QUANTITY));
            }
        }
        this.homePhone = phoneNumber.toString();
    }

    private void setEMail() {
        String eMail;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            eMail = EMPTY_STRING;
        } else {
            eMail = wordImitationInLowerCase();
            eMail += "@";
            eMail += wordImitationInLowerCase();
            eMail += ".com";
        }
        this.eMail = eMail;
    }

    private void setMobilePhone() {
        StringBuilder mobilePhone;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            mobilePhone = new StringBuilder(EMPTY_STRING);
        } else {
            mobilePhone = new StringBuilder("+");
            int length = rand.nextInt(PHONE_NUMBER_MAX_LENGTH - PHONE_NUMBER_MIN_LENGTH) + PHONE_NUMBER_MIN_LENGTH;
            for (int i = 0; i < length; i++) {
                mobilePhone.append((Integer) rand.nextInt(DIGIT_QUANTITY));
            }
        }
        this.mobilePhone = mobilePhone.toString();
    }

    private void setPersonalWebsite() {
        String personalWebsite;
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            personalWebsite = EMPTY_STRING;
        } else {
            personalWebsite = "www.";
            personalWebsite += wordImitationInLowerCase();
            personalWebsite += ".com";
        }
        this.personalWebsite = personalWebsite;
    }

    private void setCity() {
        this.city = CITIES[rand.nextInt(CITIES.length)];
    }

    private void setFlat() {
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            this.flat = EMPTY_STRING;
        } else {
            this.flat = ((Integer) rand.nextInt(FLATS_MAX_QUANTITY)).toString();
        }
    }

    private void setHouse() {
        if (rand.nextInt(HUNDRED_PERCENT) < PROBABILITY_OF_EMPTY_STRING) {
            this.house = EMPTY_STRING;
        } else {
            this.house = ((Integer) rand.nextInt(HOUSES_MAX_QUANTITY)).toString();
        }
    }

    private void setCountry() {
        this.country = COUNTRIES[rand.nextInt(COUNTRIES.length)];
    }

    private void setPostCode() {
        StringBuilder postCode = new StringBuilder(EMPTY_STRING);
        if (rand.nextInt(HUNDRED_PERCENT) >= PROBABILITY_OF_EMPTY_STRING) {
            for (int i = 0; i < POST_CODE_DIGIT_QUANTITY; i++) {
                postCode.append((Integer) rand.nextInt(DIGIT_QUANTITY));
            }
        }
        this.postCode = postCode.toString();
    }

    private void setState() {
        this.state = STATES[rand.nextInt(STATES.length)];
    }

    private void setStreet() {
        this.street = STREETS[rand.nextInt(STREETS.length)];
    }

    private String randomLowerLetter() {
        Character letter = (char) (rand.nextInt(LETTERS_QUANTITY) + LETTER_CODE_LOWER_A);
        return letter.toString();
    }

    private String randomUpperLetter() {
        Character letter = (char) (rand.nextInt(LETTERS_QUANTITY) + LETTER_CODE_UPPER_A);
        return letter.toString();
    }

    private String wordImitationInLowerCase() {
        StringBuilder word = new StringBuilder(EMPTY_STRING);
        int length = rand.nextInt(STRINGS_MAX_LENGTH - STRINGS_MIN_LENGTH) + STRINGS_MIN_LENGTH;
        for (int i = 0; i < length; i++) {
            word.append(randomLowerLetter());
        }
        return word.toString();
    }

    private String getInitialLetter(String word) {
        String letter;
        if (word.equals("")) {
            letter = EMPTY_STRING;
        } else {
            letter = String.valueOf(word.charAt(INITIAL_LETTER_INDEX));
        }
        return letter;
    }

    private void setFirstNameInitialLetter() {
        String letter = getInitialLetter(firstName);
        if (letter.equals("")) {
            letter = EMPTY_STRING;
        } else {
            letter += ".";
        }
        this.firstNameInitialLetter = letter;
    }

    private void setSecondNameInitialLetter() {
        String letter = getInitialLetter(secondName);
        if (letter.equals("")) {
            letter = EMPTY_STRING;
        } else {
            letter += ".";
        }
        this.secondNameInitialLetter = letter;
    }

    private void setPatronymicInitialLetter() {
        String letter = getInitialLetter(patronymic);
        if (letter.equals("")) {
            letter = EMPTY_STRING;
        } else {
            letter += ".";
        }
        this.patronymicInitialLetter = letter;
    }

    private void setLastNameInitialLetter() {
        String letter = getInitialLetter(lastName);
        if (letter.equals("")) {
            letter = EMPTY_STRING;
        } else {
            letter += ".";
        }
        this.lastNameInitialLetter = letter;
    }
}
