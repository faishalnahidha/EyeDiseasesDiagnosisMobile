package com.izzan.eyediseasesdiagnosismobile.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Aizen on 3 Jul 2017.
 */

@Table(name = "diseases", id = "_id")
public class Disease extends Model{

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    private int score;

    public Disease(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public Disease() {
        super();
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static List<Disease> getAll(){
        return new Select()
                .from(Disease.class)
                .orderBy("_id asc")
                .execute();
    }

    public static Disease getRandom(){
        return new Select()
                .from(Disease.class)
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static Disease getById(long id){
        return new Select()
                .from(Disease.class)
                .where("_id = ?", id)
                .executeSingle();
    }
}
