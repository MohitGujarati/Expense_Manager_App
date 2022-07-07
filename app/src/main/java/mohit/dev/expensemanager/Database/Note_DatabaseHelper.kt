package mohit.dev.expensemanager.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mohit.de.Category_DatabaseHelper
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.Model.userModel

class Note_DatabaseHelper(var context: Context) :
    SQLiteOpenHelper(context,Note_DATABASE_NAME,null,Note_DATABASE_VERSION) {

    companion object{

            private  val Note_DATABASE_NAME="mynotedb"
            private  val Note_DATABASE_VERSION=1

            private const val Note_TABLE_NAME = "notes"
            private const val Note_KEY_NID = "nid"
            private const val Note_KEY_AMOUNT="noteamount"
            private const val Note_KEY_CATEGORY="notecategory"
            private const val Note_KEY_NOTE="notenote"
            private const val Note_KEY_DATE="notedate"

        }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            (
                    "CREATE TABLE " + Note_TABLE_NAME + " "
                            + " ( " + Note_KEY_NID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                            + Note_KEY_AMOUNT + " TEXT, " + ""
                            + Note_KEY_CATEGORY + " TEXT,"
                            + Note_KEY_NOTE + " TEXT,"
                            + Note_KEY_DATE + " TEXT)"
                    )

        db?.execSQL(CREATE_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }


    fun note_insertdata(notesModelclass: Notes_ModelClass):Long{

        var db=this.writableDatabase

        var cv=ContentValues()

        cv.put(Note_KEY_AMOUNT,notesModelclass.user_amount)
        cv.put(Note_KEY_AMOUNT,notesModelclass.user_category)
        cv.put(Note_KEY_AMOUNT,notesModelclass.user_note)
        cv.put(Note_KEY_AMOUNT,notesModelclass.user_date)

        var inserdata=db.insert(Note_TABLE_NAME,null,cv)
        return inserdata

    }

    @SuppressLint("Range")
    fun getAllNotes(): MutableList<Notes_ModelClass> {

        var userlist: MutableList<Notes_ModelClass> = ArrayList()
        var sel_note = "select * from $Note_TABLE_NAME ORDER BY $Note_KEY_NID DESC "

        var cursor: Cursor?
        var db = this.readableDatabase

        try {
            cursor = db.rawQuery(sel_note, null)
        } catch (Exception: SQLException) {
            db.execSQL(sel_note)
            return ArrayList()
        }

        var noteid: Int
        var amount: String
        var date: String
        var category_note: String
        var note: String

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    noteid = cursor.getInt(cursor.getColumnIndex(Note_KEY_NID))
                    amount = cursor.getString(cursor.getColumnIndex(Note_KEY_AMOUNT))
                    date = cursor.getString(cursor.getColumnIndex(Note_KEY_DATE))
                    category_note = cursor.getString(cursor.getColumnIndex(Note_KEY_CATEGORY))
                    note = cursor.getString(cursor.getColumnIndex(Note_KEY_NOTE))


                    var userdatas = Notes_ModelClass(noteid,amount,category_note,note,date)
                    userlist.add(userdatas)


                } while (cursor.moveToNext())
            }
        }

        return userlist
    }


}