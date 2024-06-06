package com.example.nbastatsapp;
import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable{
    private String name;
    private String logo;
    private int id;

    public Team(String name, String logo) {
        this.name = name;
        this.logo = logo;
    }
    protected Team(Parcel in) {
        id = in.readInt();
        name = in.readString();
        logo = in.readString();
    }
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(logo);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public int getId(){
        return id;
    }
}
