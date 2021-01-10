package com.project.sehatq_test_android.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.project.sehatq_test_android.ui.productdetail.PurchaseHistoryModel
import java.util.*

/**
 * Created by Ridwan Juanda on 1/10/21.
 */
class DatabaseHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val query: String
        //creating table
        query = "CREATE TABLE $TABLE_NAME(ID INTEGER PRIMARY KEY, Title TEXT, Price TEXT, Image TEXT)"
        db.execSQL(query)
    }

    //upgrading database
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    //add the new ph
    fun addPurchaseHistory(title: String?, price: String?, imgProduct: String?) {
        val sqLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put("Title", title)
        values.put("Price", price)
        values.put("Image", imgProduct)

        //inserting new row
        sqLiteDatabase.insert(TABLE_NAME, null, values)
        //close database connection
        sqLiteDatabase.close()
    }// select all query

    // looping through all rows and adding to list
    //get the all notes
    val purchaseHistory: ArrayList<PurchaseHistoryModel>
        get() {
            val arrayList = ArrayList<PurchaseHistoryModel>()

            // select all query
            val select_query = "SELECT *FROM $TABLE_NAME"
            val db = this.writableDatabase
            val cursor = db.rawQuery(select_query, null)

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    val noteModel = PurchaseHistoryModel()
                    noteModel.id = cursor.getString(0)
                    noteModel.title = cursor.getString(1)
                    noteModel.des = cursor.getString(2)
                    noteModel.imageUrl = cursor.getString(3)
                    arrayList.add(noteModel)
                } while (cursor.moveToNext())
            }
            return arrayList
        }

    //delete the ph
    fun delete(ID: String) {
        val sqLiteDatabase = this.writableDatabase
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID=$ID", null)
        sqLiteDatabase.close()
    }

    //update the note
    fun updateNote(title: String?, price: String?,imgProduct: String?, ID: String) {
        val sqLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put("Title", title)
        values.put("Price", price)
        values.put("Image", imgProduct)
        //updating row
        sqLiteDatabase.update(TABLE_NAME, values, "ID=$ID", null)
        sqLiteDatabase.close()
    }

    companion object {
        //database name
        const val DATABASE_NAME = "sehatQ_Android"

        //database version
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "tbl_purchase"
    }
}