package mohit.dev.expensemanager.Database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import mohit.de.Category_DatabaseHelper
import mohit.de.Category_DatabaseHelper.Companion.category_KEY_categoryname
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.Model.Notes_ModelClass

class note_database(var note_context: Context) :
    SQLiteOpenHelper(note_context, note_DATABASE_NAME, null, 1) {

    companion object {
        private val note_DATABASE_NAME = "notedatabase"
        private val note_DATABASE_VERSION = 1

        private const val note_TABLE_NAME = "notedatabase"
        private const val note_KEY_ID = "note_id"
        private const val note_KEY_AMOUNT = "note_amount"
        private const val note_KEY_CATEGORY = "note_category"
        private const val note_KEY_NOTES = "user_note"
        private const val note_KEY_DATE = "user_date"
        private const val note_KEY_MONTH = "user_month"

    }


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            (
                    "CREATE TABLE " + note_TABLE_NAME.toString() + " "
                            + " ( " + note_KEY_ID.toString() + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                            + note_KEY_AMOUNT.toString() + " INTEGER, " + ""
                            + note_KEY_CATEGORY.toString() + " TEXT,"
                            + note_KEY_NOTES.toString() + " TEXT,"
                            + note_KEY_MONTH.toString() + " INTEGER,"
                            + note_KEY_DATE.toString() + " TEXT)"
                    )

        db?.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    lateinit var model:Notes_ModelClass

    fun note_insertdata(notesModelclass: Notes_ModelClass):Long{

        var note_db=this.writableDatabase

        var note_cv=ContentValues()

        note_cv.put(note_KEY_AMOUNT,notesModelclass.user_amount)
        note_cv.put(note_KEY_CATEGORY,notesModelclass.user_category)
        note_cv.put(note_KEY_DATE,notesModelclass.user_date)
        note_cv.put(note_KEY_NOTES,notesModelclass.user_note)
        note_cv.put(note_KEY_MONTH,notesModelclass.user_month)

        var note_insert=note_db.insert(note_TABLE_NAME,null,note_cv)
        return note_insert

    }


    @SuppressLint("Range")
    fun getamount():MutableList<Int> {

        var amount_list:MutableList<Int> =ArrayList()

        var getamountquery="select * from $note_TABLE_NAME order by $note_KEY_ID desc"

        var cursor:Cursor?
        var amount_db=this.readableDatabase


        try {
            cursor=amount_db.rawQuery(getamountquery,null)
        }catch (Exception: SQLException) {
            amount_db.execSQL(getamountquery)
            return ArrayList()
        }

        var amount:String

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {

                    amount = cursor.getString(cursor.getColumnIndex(note_KEY_AMOUNT))

                    var userdatas =
                    amount_list.add(Integer.valueOf(amount))


                } while (cursor.moveToNext())
            }
        }

        return amount_list

    } @SuppressLint("Range")


    fun getfilteramount(month: Int):MutableList<Int> {

        var amount_list:MutableList<Int> =ArrayList()

        var getamountquery="select * from $note_TABLE_NAME where $note_KEY_MONTH = $month order by $note_KEY_ID desc"

        var cursor:Cursor?
        var amount_db=this.readableDatabase


        try {
            cursor=amount_db.rawQuery(getamountquery,null)
        }catch (Exception: SQLException) {
            amount_db.execSQL(getamountquery)
            return ArrayList()
        }

        var amount:String

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {

                    amount = cursor.getString(cursor.getColumnIndex(note_KEY_AMOUNT))

                    var userdatas =
                    amount_list.add(Integer.valueOf(amount))


                } while (cursor.moveToNext())
            }
        }

        return amount_list

    }

    @SuppressLint("Range")
    fun getfilter_catamount(category: String):MutableList<Int> {

        var amount_list:MutableList<Int> =ArrayList()

        var query="select * from $note_TABLE_NAME where  $note_KEY_CATEGORY = '$category' order by $note_KEY_ID desc"

        var cursor:Cursor?
        var amount_db=this.readableDatabase


        try {
            cursor=amount_db.rawQuery(query,null)
        }catch (Exception: SQLException) {
            amount_db.execSQL(query)
            return ArrayList()
        }

        var amount:String

        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {

                    amount = cursor.getString(cursor.getColumnIndex(note_KEY_AMOUNT))

                    var userdatas =
                    amount_list.add(Integer.valueOf(amount))


                } while (cursor.moveToNext())
            }
        }

        return amount_list

    }


    @SuppressLint("Range")
    fun getall_Note():MutableList<Notes_ModelClass>{

        var note_userlist:MutableList<Notes_ModelClass> =ArrayList()
        var query="select * from $note_TABLE_NAME order by $note_KEY_ID desc"

        var cursor:Cursor?
        var note_db=this.readableDatabase

        try {
            cursor=note_db.rawQuery(query,null)
        }catch (Exception: SQLException) {
            note_db.execSQL(query)
            return ArrayList()
    }

        var noteid: Int
        var amount: Int
        var date: String
        var category: String
        var note: String
        var month:Int



        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    noteid = cursor.getInt(cursor.getColumnIndex(note_KEY_ID))
                    amount = cursor.getInt(cursor.getColumnIndex(note_KEY_AMOUNT))
                    category = cursor.getString(cursor.getColumnIndex(note_KEY_CATEGORY))
                    note = cursor.getString(cursor.getColumnIndex(note_KEY_NOTES))
                    date = cursor.getString(cursor.getColumnIndex(note_KEY_DATE))
                    month = cursor.getInt(cursor.getColumnIndex(note_KEY_MONTH))


                    var userdatas = Notes_ModelClass(noteid,amount,category,note,date,month)
                    note_userlist.add(userdatas)


                } while (cursor.moveToNext())
            }
        }

        return note_userlist
    }

    fun note_delete(notesModelclass: Notes_ModelClass):Int{

        var note_db=this.writableDatabase
        var note_del=note_db.delete(note_TABLE_NAME, note_KEY_ID+"="+ notesModelclass.noteid,null)
        return note_del

    }

    @SuppressLint("Range")
    fun getmonth(month:Int):MutableList<Notes_ModelClass>{

        var note_userlist:MutableList<Notes_ModelClass> =ArrayList()
        var query="select * from $note_TABLE_NAME where $note_KEY_MONTH = $month order by $note_KEY_ID desc"

        var cursor:Cursor?
        var note_db=this.readableDatabase

        try {
            cursor=note_db.rawQuery(query,null)
        }catch (Exception: SQLException) {
            note_db.execSQL(query)
            return ArrayList()
        }

        var noteid: Int
        var amount: Int
        var date: String
        var category: String
        var note: String
        var month:Int



        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    noteid = cursor.getInt(cursor.getColumnIndex(note_KEY_ID))
                    amount = cursor.getInt(cursor.getColumnIndex(note_KEY_AMOUNT))
                    category = cursor.getString(cursor.getColumnIndex(note_KEY_CATEGORY))
                    note = cursor.getString(cursor.getColumnIndex(note_KEY_NOTES))
                    date = cursor.getString(cursor.getColumnIndex(note_KEY_DATE))
                    month = cursor.getInt(cursor.getColumnIndex(note_KEY_MONTH))


                    var userdatas = Notes_ModelClass(noteid,amount,category,note,date,month)
                    note_userlist.add(userdatas)


                } while (cursor.moveToNext())
            }
        }

        return note_userlist
    }


    @SuppressLint("Range")
    fun getvalidcategory(category:String):MutableList<Notes_ModelClass>{
        var note_userlist:MutableList<Notes_ModelClass> =ArrayList()
        var query="select * from $note_TABLE_NAME where  $note_KEY_CATEGORY = '$category' order by $note_KEY_ID desc"

        var cursor:Cursor?
        var note_db=this.readableDatabase

        try {
            cursor=note_db.rawQuery(query,null)
        }catch (Exception: SQLException) {
            note_db.execSQL(query)
            return ArrayList()
        }

        var noteid: Int
        var amount: Int
        var date: String
        var category: String
        var note: String
        var month:Int



        if (cursor.count > 0) {
            if (cursor.moveToFirst()) {

                do {
                    noteid = cursor.getInt(cursor.getColumnIndex(note_KEY_ID))
                    amount = cursor.getInt(cursor.getColumnIndex(note_KEY_AMOUNT))
                    category = cursor.getString(cursor.getColumnIndex(note_KEY_CATEGORY))
                    note = cursor.getString(cursor.getColumnIndex(note_KEY_NOTES))
                    date = cursor.getString(cursor.getColumnIndex(note_KEY_DATE))
                    month = cursor.getInt(cursor.getColumnIndex(note_KEY_MONTH))


                    var userdatas = Notes_ModelClass(noteid,amount,category,note,date,month)
                    note_userlist.add(userdatas)


                } while (cursor.moveToNext())
            }
        }

        return note_userlist
    }

}