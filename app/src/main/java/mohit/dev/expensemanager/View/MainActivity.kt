package mohit.dev.expensemanager.View

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mohit.de.Category_DatabaseHelper
import mohit.dev.expensemanager.Adpter.Mycategory_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.R
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ed_amount = findViewById<EditText>(R.id.ed_userAmount)
        var ed_categoryname = findViewById<EditText>(R.id.ed_categoryname)
        var rec_cat = findViewById<RecyclerView>(R.id.rec_category)
        var tv_date = findViewById<TextView>(R.id.tv_seletedate)
        var ed_note = findViewById<EditText>(R.id.ed_note)
        var tv_addcategory = findViewById<TextView>(R.id.tv_addcategory)
        var btn_done = findViewById<ExtendedFloatingActionButton>(R.id.btn_done)

        var dbhelper = note_database(this)


        btn_done.setOnClickListener {

            var amount = ed_amount.text.toString()
            var category = ed_categoryname.text.toString()
            var note = ed_note.text.toString()
            var date = tv_date.text.toString()


            var passamount = ed_amount.text


            var id = dbhelper.note_insertdata(
                Notes_ModelClass(
                    it.id,
                    "$amount", "$category", "$note", "$date"
                )
            )

            var i = Intent(this, User_Notes::class.java)
            Log.d("notesimpdata", "$id \"$amount\" and final amount is")
            i.putExtra("passed",Integer.valueOf(amount))
            Toast.makeText(
                this,
                "saved at $id ${ed_amount.toString()},${ed_categoryname.toString()} ${ed_note.toString()}",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(i)

            Log.d("dates", date)

        }


        //date
        var Todaydate = Calendar.getInstance()
        var year = Todaydate.get(Calendar.YEAR)
        var month = Todaydate.get(Calendar.MONTH)
        var day = Todaydate.get(Calendar.DAY_OF_MONTH)


        //setting onclick recview data

        var categoryname = getIntent().getStringExtra("categoryname")
        if (categoryname == null) {
            ed_categoryname.setText("Enter the category")
        } else
            ed_categoryname.setText(categoryname.toString())

        //recview
        load_category(rec_cat)

        //add category
        tv_addcategory.setOnClickListener {

            var d = Dialog(this)
            d.setContentView(R.layout.mydialog)
            d.setCancelable(true)

            var dialog_categoryname = d.findViewById<EditText>(R.id.ed_categoryName)
            var btn_savecategory = d.findViewById<Button>(R.id.btn_Add)
            var dbhelper = Category_DatabaseHelper(this)

            btn_savecategory.setOnClickListener {
                var id = dbhelper.insertData(Category_ModelClass(it.id, dialog_categoryname.text.toString()))
                if (id > 0) {
                    var intent = Intent(this, MainActivity::class.java)
                    //   Toast.makeText(this, "saved at $id", Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                }

            }

            d.show()

        }

        //take date from recview
        tv_date.setOnClickListener {
            var datepickerbox =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, d ->
                    var m = month + 1
                    tv_date.text = "$d/$m/$year "

                }, year, month, day)
                    .show()
        }


    }


    private fun load_category(recCat: RecyclerView) {

        recCat.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

        var db_helper = Category_DatabaseHelper(this)

        var userlist: MutableList<Category_ModelClass>
        userlist = db_helper.getAllData()

        var connect_Adapter = Mycategory_Adapter(this, userlist)
        recCat.adapter = connect_Adapter

    }

}