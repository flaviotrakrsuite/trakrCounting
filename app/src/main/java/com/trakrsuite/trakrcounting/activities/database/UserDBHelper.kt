package com.trakrsuite.trakrcounting.activities.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_ENTRIES = "CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${UserContract.UserEntry.COLUMN_USERNAME} TEXT," +
                "${UserContract.UserEntry.COLUMN_PASSWORD} TEXT," +
                "${UserContract.UserEntry.COLUMN_LOCATION} TEXT)"

        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aquí puedes implementar la lógica de actualización de la base de datos si es necesario
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "User.db"
    }
}
