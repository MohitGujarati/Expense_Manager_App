package mohit.dev.expensemanager.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mohit.de.Category_DatabaseHelper
import mohit.dev.expensemanager.Adpter.MyFrag_category_Adapter
import mohit.dev.expensemanager.Adpter.Mynotes_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R


class User_Notes : AppCompatActivity() {

    var backPressedTime: Long = 0

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notes)

        var totalamount = findViewById<TextView>(R.id.tv_totalExpence)
        var btn_addnotes = findViewById<ExtendedFloatingActionButton>(R.id.btn_addnotes)
        var rec_notes = findViewById<RecyclerView>(R.id.rec_savednotes)

        var viewhistory = findViewById<ImageView>(R.id.viewhistory)
        var btn_budget = findViewById<ImageView>(R.id.btn_budget)
        var rec_chips = findViewById<RecyclerView>(R.id.rec_Categorychips)
        var rec_chip_list = findViewById<RecyclerView>(R.id.rec_showchipdata)
        var show_chip = findViewById<Button>(R.id.show_chip)


        var chip_clicked = true
        var prgarray = ArrayList<Int>()
        loadrecview(rec_notes, totalamount, prgarray)
        loadamount(totalamount, prgarray)

        var msg = intent.getStringExtra("chiptext")
        show_chip.setOnClickListener {
            if (chip_clicked == true) {
                rec_chips.visibility = View.VISIBLE

                load_category_chips(rec_chips)
                chip_clicked = false
            } else if (chip_clicked == false) {
                rec_chips.visibility = View.GONE
                chip_clicked = true
            }
        }

        if (msg != null) {
            rec_chip_list.visibility = View.VISIBLE
            var txtchip = msg.toString()
            load_category_notes(rec_chip_list, txtchip, prgarray)
        } else {
            rec_chip_list.visibility = View.GONE
        }




        viewhistory.setOnClickListener {
            var i = Intent(this, Monthly_History::class.java)
            startActivity(i)

        }
        btn_addnotes.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }


    private fun load_category_notes(
        recCat: RecyclerView,
        txtchip: String,
        prgarray: ArrayList<Int>,

    ) {
        Log.d("prgdata","$prgarray")

        recCat.layoutManager = LinearLayoutManager(this)
        var db_helper = note_database(this)

        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getvalidcategory("$txtchip")

        var connect_Adapter = Mynotes_Adapter(this, userlist,prgarray)
        recCat.adapter = connect_Adapter



    }

    private fun load_category_chips(
        recCat: RecyclerView
    ) {

        recCat.layoutManager = GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false)

        var db_helper = Category_DatabaseHelper(this)

        var userlist: MutableList<Category_ModelClass>
        userlist = db_helper.getAllCategory_Data()

        var connect_Adapter = MyFrag_category_Adapter(this, userlist)
        recCat.adapter = connect_Adapter

    }

    private fun loadamount(
        totalamount: TextView,
        prgarray: ArrayList<Int>
    ) {
        var db_helper = note_database(this)

        var amountlist: MutableList<Int>
        amountlist = db_helper.getamount()

        var sum = 0
        for (i in 0..amountlist.size - 1) {

            var amountlistdata = sum + amountlist[i]
            sum = amountlistdata
        }


        totalamount.text = sum.toString()
        Log.d("amount_list_data", "$amountlist, sum=$sum")


        var prg = 0
        for (i in 0..amountlist.size - 1) {
            prg = (amountlist.get(i) * 100 / sum).toInt()
            Log.d("progress", "$prg")
            // Log.d("progressarray","$prg")
            prgarray.add(prg)
            Log.d("progressarray", "$prgarray")
        }


    }

    private fun loadrecview(
        rec_notes: RecyclerView,
        totalamount: TextView,
        prgarray: ArrayList<Int>
    ) {
        var db_helper = note_database(this)
        //calculation for progress
        rec_notes.layoutManager = LinearLayoutManager(this)

        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getall_Note()

        var connect_Adapter = Mynotes_Adapter(this, userlist, prgarray)
        rec_notes.adapter = connect_Adapter

    }

    private fun setmonth(
        month: Int,
        txt_budget: TextView
    ) {

        var budget_arraylist = arrayListOf<Int>()
        budget_arraylist.add(0, 10000)
        budget_arraylist.add(1, 10000)
        budget_arraylist.add(2, 20000)
        budget_arraylist.add(3, 30000)
        budget_arraylist.add(4, 40000)
        budget_arraylist.add(5, 50000)
        budget_arraylist.add(6, 60000)
        budget_arraylist.add(7, 70000)
        budget_arraylist.add(8, 80000)
        budget_arraylist.add(9, 90000)
        budget_arraylist.add(10, 100000)
        budget_arraylist.add(11, 110000)

        when (month) {
            0 -> {

                txt_budget.text = budget_arraylist.get(0).toString()
            }
            1 -> {

                txt_budget.text = budget_arraylist.get(1).toString()
            }
            2 -> {

                txt_budget.text = budget_arraylist.get(2).toString()
            }
            3 -> {

                txt_budget.text = budget_arraylist.get(3).toString()
            }
            4 -> {

                txt_budget.text = budget_arraylist.get(4).toString()
            }
            5 -> {

                txt_budget.text = budget_arraylist.get(5).toString()
            }
            6 -> {


                txt_budget.text = budget_arraylist.get(6).toString()
            }
            7 -> {

                txt_budget.text = budget_arraylist.get(7).toString()
            }
            8 -> {

                txt_budget.text = budget_arraylist.get(8).toString()
            }
            9 -> {

                txt_budget.text = budget_arraylist.get(9).toString()
            }
            10 -> {

                txt_budget.text = budget_arraylist.get(10).toString()
            }
            11 -> {

                txt_budget.text = budget_arraylist.get(11).toString()
            }
        }

    }
/*
    override fun onBackPressed() {
        //super.onBackPressed()
        var builder = AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("Are you sure you want to exit")
            .setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
                finish()
                //     dialog.dismiss()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            })

            .setNeutralButton(" Goto My Task's", DialogInterface.OnClickListener { dialog, which ->
                var i = Intent(this, User_Notes::class.java)
                Toast.makeText(this, "My Task", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                startActivity(i)
            })
            .setCancelable(false)

        builder.show()
    }

 */


}