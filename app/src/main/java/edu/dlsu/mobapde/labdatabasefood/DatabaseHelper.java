package edu.dlsu.mobapde.labdatabasefood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by G301 on 11/7/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String SCHEMA = "asian";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, SCHEMA, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // ROLE: create the tables for the schema
        // It will only be called once by the system
        // -- when the schema with given name doesn't exist yet

        String sql = "CREATE TABLE " + Food.TABLE_NAME + " ("
                + Food.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Food.COLUMN_NAME + " TEXT,"
                + Food.COLUMN_CALORIES + " INTEGER,"
                + Food.COLUMN_CUSINE + " TEXT"
                + ");";
        sqLiteDatabase.execSQL(sql);

        addFood(new Food("Adobong Manok", 200, "Filipino"), sqLiteDatabase);
        addFood(new Food("Palabok", 150, "Filipino"), sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                          int i, int i1) {
        // ROLE: update the current schema
        // Will be called when version number is newer/higher

        // migration
        // drop current tables
        String sql = "DROP TABLE IF EXISTS " + Food.TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        // call onCreate
        onCreate(sqLiteDatabase);
    }

    // addFood
    public long addFood(Food food){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_NAME, food.getName());
        contentValues.put(Food.COLUMN_CALORIES, food.getCalories());
        contentValues.put(Food.COLUMN_CUSINE, food.getCuisine());

        long id = db.insert(Food.TABLE_NAME,
                null,
                contentValues);
        db.close();
        return id;
    }

    // addFood
    public long addFood(Food food, SQLiteDatabase db){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_NAME, food.getName());
        contentValues.put(Food.COLUMN_CALORIES, food.getCalories());
        contentValues.put(Food.COLUMN_CUSINE, food.getCuisine());

        long id = db.insert(Food.TABLE_NAME,
                null,
                contentValues);
        db.close();
        return id;
    }

    // editFood
    public boolean editFood(Food newFoodDetails, int currentId){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Food.COLUMN_NAME, newFoodDetails.getName());
        contentValues.put(Food.COLUMN_CALORIES, newFoodDetails.getCalories());
        contentValues.put(Food.COLUMN_CUSINE, newFoodDetails.getCuisine());

        int rowsAffected = db.update(Food.TABLE_NAME,
                contentValues,
                Food.COLUMN_ID + "=?",
                new String[]{newFoodDetails.getId()+""});
        db.close();

        return rowsAffected >0;
    }

    // deleteFood
    public boolean deleteFood(long id){
        SQLiteDatabase db = getWritableDatabase();
        int rowsAffected = db.delete(Food.TABLE_NAME,
                Food.COLUMN_ID + "=?",
                new String[]{id+""} );
        db.close();
        return rowsAffected >0;
    }

    // getFood
    public Food getFood(long id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Food.TABLE_NAME,
                null,
                Food.COLUMN_ID + "=?",
                new String[]{ id+"" },
                null,
                null,
                null);
        Food f = null;
        if(c.moveToFirst()){
            f = new Food();
            f.setName(c.getString(c.getColumnIndex(Food.COLUMN_NAME)));
            f.setCalories(c.getInt(c.getColumnIndex(Food.COLUMN_CALORIES)));
            f.setCuisine(c.getString(c.getColumnIndex(Food.COLUMN_CUSINE)));
            f.setId(c.getLong(c.getColumnIndex(Food.COLUMN_ID)));
        }

        c.close();
        db.close();

        return f;
    }

    // getAllFoods
    public Cursor getAllFoodsCursor(){
        return getReadableDatabase().query(Food.TABLE_NAME, null,null,null,null,null,null);
    }
}






