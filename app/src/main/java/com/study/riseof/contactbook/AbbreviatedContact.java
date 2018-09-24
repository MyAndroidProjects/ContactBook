package com.study.riseof.contactbook;

public class AbbreviatedContact {
    private int itemId;
    private String contactName;

    public AbbreviatedContact(int id, String name){
        itemId = id;
        contactName = name;
    }

    public void setItemId(int databaseId) {
        this.itemId = databaseId;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public int getItemId() {
        return itemId;
    }

    public String getContactName() {
        return contactName;
    }
}
