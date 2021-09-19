package com.example.dalelidwaai.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Medication implements Parcelable {

    String name;
    String dosage;
    String contraindications;
    String store;
    String uses;

    public Medication() {
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public String getContraindications() {
        return contraindications;
    }

    public String getStore() {
        return store;
    }

    public String getUses() {
        return uses;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(dosage);
        dest.writeString(contraindications);
        dest.writeString(store);
        dest.writeString(uses);
    }

    protected Medication(Parcel in) {
        name = in.readString();
        dosage = in.readString();
        contraindications = in.readString();
        store = in.readString();
        uses = in.readString();
    }

    public static final Creator<Medication> CREATOR = new Creator<Medication>() {
        @Override
        public Medication createFromParcel(Parcel in) {
            return new Medication(in);
        }

        @Override
        public Medication[] newArray(int size) {
            return new Medication[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
