package com.izzan.eyediseasesdiagnosismobile.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Aizen on 3 Jul 2017.
 */

@Table(name = "symptoms", id = "_id")
public class Symptom extends Model{

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    private int symptomArise;

    public Symptom(String code, String name) {
        super();
        this.code = code;
        this.name = name;
        symptomArise = 0;
    }

    public Symptom() {
        super();
        symptomArise = 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSymptomArise() {
        return symptomArise;
    }

    public void setSymptomArise(int symptomArise) {
        this.symptomArise = symptomArise;
    }

    public static List<Symptom> getAll() {
        // This is how you execute a query
        return new Select()
                .from(Symptom.class)
                .orderBy("_id asc")
                .execute();
    }

    public static Symptom getRandom() {
        return new Select()
                .from(Symptom.class)
                .orderBy("RANDOM()")
                .executeSingle();
    }
}
