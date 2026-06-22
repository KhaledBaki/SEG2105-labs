package com.example.lab5;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_SKU = "SKU";

    // Constructor
    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PRODUCTNAME + " TEXT, "
                + COLUMN_SKU + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    /**
     * Adding product to the database
     * @param product Product being added
     */
    public void addProduct(Product product){
        // Gets the data repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getProductID());
        values.put(COLUMN_SKU, product.getSKU());

        // Inserting values into the database
        db.insert(TABLE_PRODUCTS, null, values);

        // Close
        db.close();
    }

    /**
     * Searching product from the database
     * @param productName Product name being searched for
     */
    public Product findProduct(String productName) {

        //Getting reference to readable database
        SQLiteDatabase db = this.getReadableDatabase();

        // Running query
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + " = \"" + productName + "\"";
        Cursor cursor = db.rawQuery(query, null);

        Product product = new Product();
        if (cursor.moveToFirst()) {
            product.setProductID(Integer.parseInt(cursor.getString(0)));
            product.setName(cursor.getString(1));
            product.setSKU(Integer.parseInt(cursor.getString(2)));
        } else {
            product = null;
        }
        db.close();
        return product;
    }

    /**
     * Deleting product from the database
     * @param productname Product name being deleted
     */
    public boolean deleteProduct(String productname) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + " = \"" + productname + "\"";

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }



}
