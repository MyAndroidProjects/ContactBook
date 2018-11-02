package com.study.riseof.contactBookAndWeather.contactBook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.study.riseof.contactBookAndWeather.contactBook.model.RandomContact;

class ContactsBaseSQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database_contacts.db";
    public static final int VERSION = 1; // версия базы данных
    public static final String TABLE_CONTACTS = "table_contacts"; // название таблицы в бд

    // ??? названия столбцов или их лучше в enum объединить?
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_FIRST_NAME_INITIAL_LETTER = "first_name_initial_letter";
    public static final String COLUMN_SECOND_NAME = "second_name";
    public static final String COLUMN_SECOND_NAME_INITIAL_LETTER = "second_name_initial_letter";
    public static final String COLUMN_PATRONYMIC = "patronymic";
    public static final String COLUMN_PATRONYMIC_INITIAL_LETTER = "patronymic_initial_letter";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_LAST_NAME_INITIAL_LETTER = "last_name_initial_letter";
    public static final String COLUMN_MOBILE_PHONE = "mobile_phone";
    public static final String COLUMN_HOME_PHONE = "home_phone";
    public static final String COLUMN_PERSONAL_WEBSITE = "personal_website";
    public static final String COLUMN_E_MAIL = "e_mail";
    public static final String COLUMN_FLAT = "flat_number";
    public static final String COLUMN_HOUSE = "house_number";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_POST_CODE = "post_code";

    private static final int INITIAL_NUMBER_OF_CONTACTS = 300;

    public ContactsBaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // создание таблицы
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS + "(" + columnsNameAndType() + ");");
        // добавление начальных данных и заполние базы
        ContentValues contentValues = testContentValues();
        db.insert(TABLE_CONTACTS, null, contentValues);
        for (int i = 0; i < INITIAL_NUMBER_OF_CONTACTS; i++) {
            contentValues.clear();
            contentValues = randomContentValues();
            db.insert(TABLE_CONTACTS, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    private String columnsNameAndType() {
        return COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FIRST_NAME + " TEXT, "
                + COLUMN_FIRST_NAME_INITIAL_LETTER + " TEXT, "
                + COLUMN_SECOND_NAME + " TEXT, "
                + COLUMN_SECOND_NAME_INITIAL_LETTER + " TEXT, "
                + COLUMN_PATRONYMIC + " TEXT, "
                + COLUMN_PATRONYMIC_INITIAL_LETTER + " TEXT, "
                + COLUMN_LAST_NAME + " TEXT, "
                + COLUMN_LAST_NAME_INITIAL_LETTER + " TEXT, "
                + COLUMN_MOBILE_PHONE + " TEXT, "
                + COLUMN_HOME_PHONE + " TEXT, "
                + COLUMN_PERSONAL_WEBSITE + " TEXT, "
                + COLUMN_E_MAIL + " TEXT, "
                + COLUMN_FLAT + " TEXT, "
                + COLUMN_HOUSE + " TEXT, "
                + COLUMN_STREET + " TEXT, "
                + COLUMN_CITY + " TEXT, "
                + COLUMN_STATE + " TEXT, "
                + COLUMN_COUNTRY + " TEXT, "
                + COLUMN_POST_CODE + " TEXT";
    }

    private ContentValues randomContentValues() {
        ContentValues cv = new ContentValues();
        RandomContact contact = new RandomContact();
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_FIRST_NAME_INITIAL_LETTER, contact.getFirstNameInitialLetter());
        cv.put(COLUMN_SECOND_NAME, contact.getSecondName());
        cv.put(COLUMN_SECOND_NAME_INITIAL_LETTER, contact.getSecondNameInitialLetter());
        cv.put(COLUMN_PATRONYMIC, contact.getPatronymic());
        cv.put(COLUMN_PATRONYMIC_INITIAL_LETTER, contact.getPatronymicInitialLetter());
        cv.put(COLUMN_LAST_NAME, contact.getLastName());
        cv.put(COLUMN_LAST_NAME_INITIAL_LETTER, contact.getLastNameInitialLetter());
        cv.put(COLUMN_MOBILE_PHONE, contact.getMobilePhone());
        cv.put(COLUMN_HOME_PHONE, contact.getHomePhone());
        cv.put(COLUMN_PERSONAL_WEBSITE, contact.getPersonalWebsite());
        cv.put(COLUMN_E_MAIL, contact.getEMail());
        cv.put(COLUMN_FLAT, contact.getFlat());
        cv.put(COLUMN_HOUSE, contact.getHouse());
        cv.put(COLUMN_STREET, contact.getStreet());
        cv.put(COLUMN_CITY, contact.getCity());
        cv.put(COLUMN_STATE, contact.getState());
        cv.put(COLUMN_COUNTRY, contact.getCountry());
        cv.put(COLUMN_POST_CODE, contact.getPostCode());
        return cv;
    }

    private ContentValues testContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, "FirstName");
        cv.put(COLUMN_FIRST_NAME_INITIAL_LETTER, "F.");
        cv.put(COLUMN_SECOND_NAME, "SecondName");
        cv.put(COLUMN_SECOND_NAME_INITIAL_LETTER, "S.");
        cv.put(COLUMN_PATRONYMIC, "Patronymic");
        cv.put(COLUMN_PATRONYMIC_INITIAL_LETTER, "P.");
        cv.put(COLUMN_LAST_NAME, "LastName");
        cv.put(COLUMN_LAST_NAME_INITIAL_LETTER, "L.");
        cv.put(COLUMN_MOBILE_PHONE, "+79139533750");
        cv.put(COLUMN_HOME_PHONE, " home 555-333");
        cv.put(COLUMN_PERSONAL_WEBSITE, "www.test.ru");
        cv.put(COLUMN_E_MAIL, "test@test.ru");
        cv.put(COLUMN_FLAT, "Flat 7");
        cv.put(COLUMN_HOUSE, "House 15");
        cv.put(COLUMN_STREET, "New Street");
        cv.put(COLUMN_CITY, "New City");
        cv.put(COLUMN_STATE, "New State");
        cv.put(COLUMN_COUNTRY, "New Country");
        cv.put(COLUMN_POST_CODE, "111222");
        return cv;
    }
}
