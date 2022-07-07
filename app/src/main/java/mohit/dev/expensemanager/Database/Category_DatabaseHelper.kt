package mohit.de

import android.annotation.SuppressLint

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mohit.dev.expensemanager.Model.userModel

class Category_DatabaseHelper(var context: Context) :
    SQLiteOpenHelper(context, category_DATABASE_NAME, null, category_DATABASE_VERSION) {

    companion object {
        private val category_DATABASE_NAME = "mydb"
        private val category_DATABASE_VERSION = 1


        private const val category_TABLE_NAME = "notes"
        private const val category_KEY_ID = "id"
        private const val category_KEY_categoryname = "username"
    }


    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_TABLE =
            (
                    "CREATE TABLE " + category_TABLE_NAME + " "
                            + " ( " + category_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                            + category_KEY_categoryname + " TEXT)"
                    )

        db?.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(userModel: userModel): Long {
        var db = this.writableDatabase
        var cv = ContentValues()

        cv.put(category_KEY_categoryname, userModel.userCategory)

        var inserData = db.insert(category_TABLE_NAME, null, cv)
        return inserData
    }

    @SuppressLint("Range")
    fun getAllData(): MutableList<userModel> {

        var userlist: MutableList<userModel> = ArrayList()
        var sel_que = "select * from $category_TABLE_NAME ORDER BY $category_KEY_ID DESC "

        var cursor: Cursor?
        var db = this.readableDatabase

        try {
            cursor = db.rawQuery(sel_que, null)
        } catch (Exception: SQLException) {
            db.execSQL(sel_que)
            return ArrayList()
        }

        var userid: Int
        var category: String

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    userid = cursor.getInt(cursor.getColumnIndex(category_KEY_ID))
                    category = cursor.getString(cursor.getColumnIndex(category_KEY_categoryname))


                    var userdata = userModel(userid, category)
                    userlist.add(userdata)


                } while (cursor.moveToNext())
            }
        }

        return userlist
    }

    fun delete(userModel: userModel):Int{

        var db =this.writableDatabase
        var id_del=db.delete(category_TABLE_NAME,category_KEY_ID+"="+userModel.userid,null)
        db.close()

        return id_del

    }
}



