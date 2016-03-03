package com.anselmo.appcapacidades.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.anselmo.appcapacidades.utils.Constants;


/**
 * Provides functions CRUD Database
 * 
 * @author Naranya
 * @version 1.1 date Oct 23, 2014
 */
public class Querys {


	public static void addUUID(Context c, String uuid ) {
		SQLiteDatabase sampleDB = c.openOrCreateDatabase(Constants.DB_NAME_DATABASE, c.MODE_PRIVATE, null);
		sampleDB.execSQL("INSERT INTO tbl_config VALUES (" + "'" + uuid + "'" + ")");
	}

	public static String getUUID( Context context ) {
		SQLiteDatabase sampleDB = context.openOrCreateDatabase(Constants.DB_NAME_DATABASE, context.MODE_PRIVATE, null);
		String query = "SELECT DISTINCT uuid FROM tbl_config";

		String uuid = "";

		//Create Cursor object to read versions from the table
		Cursor c = sampleDB.rawQuery(query, null);
		//If Cursor is valid
		if (c != null ) {
			//Move cursor to first row
			if  (c.moveToFirst()) {
				do {
					uuid = c.getString(0);
				}while (c.moveToNext()); //Move to next row
			}
		}
		
		return uuid;
	}
}