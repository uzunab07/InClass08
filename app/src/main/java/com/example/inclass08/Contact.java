package com.example.inclass08;

import java.io.Serializable;

public class Contact implements Serializable {
    String Cid,Name,Email,Phone,PhoneType;

    public Contact(String cid, String name, String email, String phone, String phoneType) {
        Cid = cid;
        Name = name;
        Email = email;
        Phone = phone;
        PhoneType = phoneType;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "Cid='" + Cid + '\'' +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                ", PhoneType='" + PhoneType + '\'' +
                '}';
    }
}
