package com.mobileappsco.training.surprise;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2/28/2016.
 */
public class PersonInfo implements Parcelable {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int sex;
    private Bitmap photo;

    public PersonInfo(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
        phone = in.readString();
        sex = in.readInt();
    }

    public PersonInfo(String firstName, String lastName, String email, String phone, int sex, Bitmap photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.sex = sex;
        this.photo = photo;
    }

    public PersonInfo() {}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return this.sex;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public Bitmap getPhoto() {
        return this.photo;
    }

    public static final Creator<PersonInfo> CREATOR = new Creator<PersonInfo>() {
        @Override
        public PersonInfo createFromParcel(Parcel in) {
            return new PersonInfo(in);
        }

        @Override
        public PersonInfo[] newArray(int size) {
            return new PersonInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeInt(sex);
    }
}
