package edu.dlsu.mobapde.labdatabasefood;

/**
 * Created by courtneyngo on 10/11/2017.
 */

public class Food {

    public static final String TABLE_NAME = "Food";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME ="name";
    public static final String COLUMN_CUSINE = "cuisine";
    public static final String COLUMN_CALORIES = "calories";

    private long id;
    private String name;
    private int calories;
    private String cuisine;

    public Food() {
    }

    public Food(String name, int calories, String cuisine) {
        this.name = name;
        this.calories = calories;
        this.cuisine = cuisine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
}
