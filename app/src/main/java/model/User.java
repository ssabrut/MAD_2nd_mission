package model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String full_name, age, address;

    public User() {

    }

    public User(String full_name, String age, String address) {
        this.full_name = full_name;
        this.age = age;
        this.address = address;
    }

    protected User(Parcel in) {
        full_name = in.readString();
        age = in.readString();
        address = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(full_name);
        dest.writeString(age);
        dest.writeString(address);
    }
}
