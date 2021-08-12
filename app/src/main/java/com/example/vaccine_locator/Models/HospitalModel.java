package com.example.vaccine_locator.Models;

import java.io.Serializable;
import java.util.ArrayList;

public class HospitalModel implements Serializable {
    String id,hName,hAddress,feeType,vaccineName,dose1,dose2,fee;
    ArrayList<String> timings;

    public HospitalModel(String id, String hName, String hAddress, String feeType, String vaccineName, String dose1, String dose2, String fee, ArrayList<String> timings) {
        this.id = id;
        this.hName = hName;
        this.hAddress = hAddress;
        this.feeType = feeType;
        this.vaccineName = vaccineName;
        this.dose1 = dose1;
        this.dose2 = dose2;
        this.fee = fee;
        this.timings = timings;
    }

    public String getId() {
        return id;
    }

    public String gethName() {
        return hName;
    }

    public String gethAddress() {
        return hAddress;
    }

    public String getFeeType() {
        return feeType;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getDose1() {
        return dose1;
    }

    public String getDose2() {
        return dose2;
    }

    public String getFee() {
        return fee;
    }

    public ArrayList<String> getTimings() {
        return timings;
    }
}
