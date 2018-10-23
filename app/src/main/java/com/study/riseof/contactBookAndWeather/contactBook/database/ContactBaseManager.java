package com.study.riseof.contactBookAndWeather.contactBook.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.study.riseof.contactBookAndWeather.contactBook.model.AbbreviatedContact;
import com.study.riseof.contactBookAndWeather.contactBook.model.Contact;

import java.util.ArrayList;

public class ContactBaseManager {

    public enum BaseColumn{
        ID(0),
        FIRST_NAME(1),
        FIRST_NAME_INITIAL_LETTER(2),
        SECOND_NAME(3),
        SECOND_NAME_INITIAL_LETTER(4),
        PATRONYMIC(5),
        PATRONYMIC_INITIAL_LETTER(6),
        LAST_NAME(7),
        LAST_NAME_INITIAL_LETTER(8),
        MOBILE_PHONE(9),
        HOME_PHONE(10),
        PERSONAL_WEBSITE(11),
        E_MAIL(12),
        FLAT(13),
        HOUSE(14),
        STREET(15),
        CITY(16),
        STATE(17),
        COUNTRY(18),
        POST_CODE(19);

        int tableIndex;
        private BaseColumn (int tableIndex){
            this.tableIndex = tableIndex;
        }
    }

    private ContactsBaseSQLiteHelper contactsBaseSQLiteHelper;
    private SQLiteDatabase database;
    private Context context;

    private final String EMPTY_STRING = "";
    private final String BUTTON_ALL = "All";
    private final String BUTTON_SPACE = "__";

    public ContactBaseManager(Context context){
        this.context = context;
        contactsBaseSQLiteHelper = new ContactsBaseSQLiteHelper(context);
    }

    public void deleteContactFromBase(int id){
        database = contactsBaseSQLiteHelper.getWritableDatabase();
        int delCount = database.delete(ContactsBaseSQLiteHelper.TABLE_CONTACTS, ContactsBaseSQLiteHelper.COLUMN_ID + " = " + id, null);
        if(delCount > 0){
            Toast.makeText(context, "Contact is delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Nothing is delete", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }

    public ArrayList<AbbreviatedContact> getAbbrContactListByFirstLetter(String letter){
        database = contactsBaseSQLiteHelper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery(queryByFirstLetterFromLastName(letter), null );
        ArrayList<AbbreviatedContact> selectedContacts = getSelectedContactsList(cursor);
        cursor.close();
        database.close();
        return selectedContacts;
    }

    private String queryByFirstLetterFromLastName(String letter){
        if(letter.equals(BUTTON_ALL)){
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                    " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                    " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
        }else if(letter.equals(BUTTON_SPACE)){
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                    " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                    " WHERE " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME +
                    " LIKE " + "'" + EMPTY_STRING + "'" +
                    " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
        }  else {
            return " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                    " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                    " WHERE " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME +
                    " LIKE " + "'" + letter + "%'" +
                    " ORDER BY " + ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                    ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER;
        }
    }

    private ArrayList<AbbreviatedContact> getSelectedContactsList(Cursor cursor){
        ArrayList<AbbreviatedContact> contactsList = new ArrayList();
        AbbreviatedContact abbrContact;
        if(cursor.moveToFirst()){
            do{
                abbrContact = getAbbrContactFromCursor(cursor);
                contactsList.add(abbrContact);
            }
            while(cursor.moveToNext());
        }
        return contactsList;
    }

    // ??? как тут обойтись без цифр? Под каждый запрос делать ENUM?
    // ??? или делать запрос на все столбцы и потом уже нужные колонки дёргать из курсора, пользуя один enum
    private AbbreviatedContact getAbbrContactFromCursor(Cursor cursor){
        AbbreviatedContact abbreviatedContact = new AbbreviatedContact(
                cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4)
        );
        return abbreviatedContact;
    }

    public Contact getContactFromCursor(Cursor cursor) {
        cursor.moveToFirst();
        Contact contact = new Contact(
                cursor.getInt(BaseColumn.ID.tableIndex),
                cursor.getString(BaseColumn.FIRST_NAME.tableIndex),
                cursor.getString(BaseColumn.FIRST_NAME_INITIAL_LETTER.tableIndex),
                cursor.getString(BaseColumn.SECOND_NAME.tableIndex),
                cursor.getString(BaseColumn.SECOND_NAME_INITIAL_LETTER.tableIndex),
                cursor.getString(BaseColumn.PATRONYMIC.tableIndex),
                cursor.getString(BaseColumn.PATRONYMIC_INITIAL_LETTER.tableIndex),
                cursor.getString(BaseColumn.LAST_NAME.tableIndex),
                cursor.getString(BaseColumn.LAST_NAME_INITIAL_LETTER.tableIndex),
                cursor.getString(BaseColumn.MOBILE_PHONE.tableIndex),
                cursor.getString(BaseColumn.HOME_PHONE.tableIndex),
                cursor.getString(BaseColumn.PERSONAL_WEBSITE.tableIndex),
                cursor.getString(BaseColumn.E_MAIL.tableIndex),
                cursor.getString(BaseColumn.FLAT.tableIndex),
                cursor.getString(BaseColumn.HOUSE.tableIndex),
                cursor.getString(BaseColumn.STREET.tableIndex),
                cursor.getString(BaseColumn.CITY.tableIndex),
                cursor.getString(BaseColumn.STATE.tableIndex),
                cursor.getString(BaseColumn.COUNTRY.tableIndex),
                cursor.getString(BaseColumn.POST_CODE.tableIndex)
        );
        return contact;
    }

    public Contact getContactById(int id) {
        database = contactsBaseSQLiteHelper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery(queryInformById(id), null );
        Contact contact = getContactFromCursor(cursor);
        cursor.close();
        database.close();
        return contact;
    }

    private String queryInformById(int id){
        //??? как лучше делать запросы: передавать аргументы прямо в строку запроса, а в selectionArg ставить null
        // или ставить вопросительные знаки, а аргументы передавать в массив? (первый и второй вариант)
             /* cursorQuery = database.rawQuery("SELECT * FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE "+ ContactsBaseSQLiteHelper.COLUMN_ID + "= ?",new String(Contact.Column..tableIndex)); */
        return " SELECT * FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE " + ContactsBaseSQLiteHelper.COLUMN_ID + " = '" + id + "'";
    }

    public String getMobileNumberById(int id) {
        database = contactsBaseSQLiteHelper.getReadableDatabase();
        Cursor cursor;
        cursor = database.rawQuery(" SELECT " + ContactsBaseSQLiteHelper.COLUMN_MOBILE_PHONE +
                " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE " + ContactsBaseSQLiteHelper.COLUMN_ID + " = '" + id + "'", null );
        cursor.moveToFirst();
        String phone  = cursor.getString(0);
        cursor.close();
        database.close();
        return phone;
    }

    public void updateContactInBase(int selectedId, Contact contact){
        database = contactsBaseSQLiteHelper.getWritableDatabase();
        database.update(ContactsBaseSQLiteHelper.TABLE_CONTACTS,
                getContentValuesFromNewContact(contact), ContactsBaseSQLiteHelper.COLUMN_ID+" = " + selectedId, null);
        Toast.makeText(context, "Contact is saved", Toast.LENGTH_SHORT).show();
        database.close();
    }

    public int addNewContactToBase(Contact contact){
        database = contactsBaseSQLiteHelper.getWritableDatabase();
        long rowID = database.insert(ContactsBaseSQLiteHelper.TABLE_CONTACTS, null, getContentValuesFromNewContact(contact));
        int id = (int)rowID;
        Toast.makeText(context, "Contact is added", Toast.LENGTH_SHORT).show();
        database.close();
        return id;
    }

    private ContentValues getContentValuesFromNewContact(Contact contact){
        ContentValues cv = new ContentValues();
        cv.clear();
        cv.put( ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME_INITIAL_LETTER, contact.getFirstNameInitialLetter());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME, contact.getSecondName());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER, contact.getSecondNameInitialLetter());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC, contact.getPatronymic());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER, contact.getPatronymicInitialLetter());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_LAST_NAME, contact.getLastName());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_LAST_NAME_INITIAL_LETTER, contact.getLastNameInitialLetter());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_MOBILE_PHONE, contact.getMobilePhone());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_HOME_PHONE, contact.getHomePhone());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_PERSONAL_WEBSITE, contact.getPersonalWebsite());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_E_MAIL, contact.getEMail());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_FLAT, contact.getFlat());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_HOUSE, contact.getHouse());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_STREET, contact.getStreet());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_CITY, contact.getCity());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_STATE, contact.getState());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_COUNTRY, contact.getCountry());
        cv.put( ContactsBaseSQLiteHelper.COLUMN_POST_CODE, contact.getPostCode());
        return cv;
    }

    public AbbreviatedContact getAbbrContactById(int id){
        database = contactsBaseSQLiteHelper.getReadableDatabase();
        Cursor cursor;
        String query = " SELECT " + ContactsBaseSQLiteHelper.COLUMN_ID + ", " +
                ContactsBaseSQLiteHelper.COLUMN_LAST_NAME + ", " +
                ContactsBaseSQLiteHelper.COLUMN_FIRST_NAME + ", " +
                ContactsBaseSQLiteHelper.COLUMN_SECOND_NAME_INITIAL_LETTER + ", " +
                ContactsBaseSQLiteHelper.COLUMN_PATRONYMIC_INITIAL_LETTER +
                " FROM " + ContactsBaseSQLiteHelper.TABLE_CONTACTS +
                " WHERE " + ContactsBaseSQLiteHelper.COLUMN_ID + " = '" + id + "'";
        cursor = database.rawQuery(query, null );
        cursor.moveToFirst();
        AbbreviatedContact abbreviatedContact = new AbbreviatedContact(
                cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3),
                cursor.getString(4)
        );
        cursor.close();
        database.close();
        return abbreviatedContact;
    }
}
