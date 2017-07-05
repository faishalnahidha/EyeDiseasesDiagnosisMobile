package com.izzan.eyediseasesdiagnosismobile.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Aizen on 4 Jul 2017.
 */

@Table(name = "seriousness", id = "_id")
public class Seriousness extends Model {

    @Column(name = "code")
    private String code;

    @Column(name = "level")
    private String level;

    private int score;

    public Seriousness(String code, String level) {
        super();
        this.code = code;
        this.level = level;
    }

    public Seriousness() {
        super();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static List<Seriousness> getAll() {
        // This is how you execute a query
        return new Select()
                .from(Seriousness.class)
                .orderBy("_id asc")
                .execute();
    }

    public static Seriousness getRandom() {
        return new Select()
                .from(Seriousness.class)
                .orderBy("RANDOM()")
                .executeSingle();
    }

    public static Seriousness getById(long id){
        return new Select()
                .from(Seriousness.class)
                .where("_id = ?", id)
                .executeSingle();
    }
}
