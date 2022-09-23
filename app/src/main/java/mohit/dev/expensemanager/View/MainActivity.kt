package mohit.dev.expensemanager.View

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
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
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.Model.Notes_ModelClass
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

        //date
        var Todaydate = Calendar.getInstance()
        var year = Todaydate.get(Calendar.YEAR)
        var month = Todaydate.get(Calendar.MONTH)
        var day = Todaydate.get(Calendar.DAY_OF_MONTH)

        var set_month = 0
        tv_date.setOnClickListener {
            var datepickerobx =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, d ->
                    set_month = month + 1
                    tv_date.text = "$d/$set_month/$year "

                }, year, month, day)
                    .show()
        }

        var dbhelper = note_database(this)

        btn_done.setOnClickListener {

            var amount = Integer.valueOf(ed_amount.text.toString())
            var category = ed_categoryname.text.toString()
            var note = ed_note.text.toString()
            var date = tv_date.text.toString()

            var passamount = amount.toInt()
            if (amount == 0) {
                Toast.makeText(this, "Enter Amount in integer", Toast.LENGTH_SHORT).show()
            } else {

                var id = dbhelper.note_insertdata(
                    Notes_ModelClass(
                        it.id,
                        amount.toInt(), "$category", "$note", "$date", set_month
                    )
                )


                var i = Intent(this, User_Notes::class.java)
                // Log.d("notesimpdata", "$id \"$amount\" and final amount is")
                i.putExtra("passed", Integer.valueOf(amount))
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                overridePendingTransition(0,0)
                // Toast.makeText(this, "saved at $id ${ed_amount.toString()},${ed_categoryname.toString()} ${ed_note.toString()}", Toast.LENGTH_SHORT).show()
                startActivity(i)

                Log.d("dates", date)

            }

        }

        //recview
        load_category(rec_cat,ed_categoryname)

        //add category
        tv_addcategory.setOnClickListener {

            var d = Dialog(this)
            d.setContentView(R.layout.dialog_category)
            d.window?.setBackgroundDrawable(ColorDrawable(0))
            d.setCancelable(true)

            var dialog_categoryname = d.findViewById<EditText>(R.id.ed_categoryName)
            var btn_savecategory = d.findViewById<Button>(R.id.btn_add)
            var dbhelper = Category_DatabaseHelper(this)

            btn_savecategory.setOnClickListener {
                var id = dbhelper.insertData(
                    Category_ModelClass(
                        it.id,
                        dialog_categoryname.text.toString()
                    )
                )
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

    }
    private fun load_category(recCat: RecyclerView, ed_categoryname: EditText) {

        recCat.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)

        var db_helper = Category_DatabaseHelper(this)

        var userlist: MutableList<Category_ModelClass>
        userlist = db_helper.getAllCategory_Data()

        var connect_Adapter = Mycategory_Adapter(this, userlist,object :Mycategory_Adapter.onclickcartegory{
            override fun onclickcardcatergory(categoryname: String) {
                ed_categoryname.setText(categoryname.toString())
            }
        })
        recCat.adapter = connect_Adapter

    }

    override fun onBackPressed() {
        var i =Intent(this,User_Notes::class.java)

        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i)
        overridePendingTransition(0,0)
        finish()
    }

}