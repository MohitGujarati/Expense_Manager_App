package mohit.dev.expensemanager.View

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mohit.de.Category_DatabaseHelper
import mohit.dev.expensemanager.Adpter.MyFrag_category_Adapter
import mohit.dev.expensemanager.Adpter.Mycategory_notes_Adapter
import mohit.dev.expensemanager.Adpter.Mynotes_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R


class User_Notes : AppCompatActivity() {

    var backPressedTime: Long = 0
    var chip_clicked = true
    var gohome = false

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notes)

        var totalamount = findViewById<TextView>(R.id.tv_totalExpence)
        var tv_txtper = findViewById<TextView>(R.id.tv_txtper)
        var btn_addnotes = findViewById<ExtendedFloatingActionButton>(R.id.btn_addnotes)
        var rec_notes = findViewById<RecyclerView>(R.id.rec_savednotes)

        var viewhistory = findViewById<ImageView>(R.id.viewhistory)
        var btn_budget = findViewById<ImageView>(R.id.btn_budget)
        var btn_GoHome = findViewById<MaterialButton>(R.id.btn_GoHome)
        var rec_chips = findViewById<RecyclerView>(R.id.rec_Categorychips)
        var rec_chip_list = findViewById<RecyclerView>(R.id.rec_showchipdata)
        var show_chip = findViewById<MaterialButton>(R.id.show_chip)
        var new_pgbar = findViewById<ProgressBar>(R.id.new_pgbar)


        btn_GoHome.setOnClickListener {
            var i = Intent(this, User_Notes::class.java)
            startActivity(i)
        }

        rec_chips.visibility = View.GONE
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
            load_category_notes(
                rec_chip_list,
                txtchip,
                prgarray,
                totalamount,
                new_pgbar,
                tv_txtper,
                btn_GoHome,
                btn_addnotes
            )
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
        totalamount: TextView,
        new_pgbar: ProgressBar,
        tv_txtper: TextView,
        btn_GoHome: MaterialButton,
        btn_addnotes: ExtendedFloatingActionButton,

        ) {
        Log.d("prgdata", "$prgarray")

        recCat.layoutManager = LinearLayoutManager(this)
        var db_helper = note_database(this)

        var userlist: MutableList<Notes_ModelClass>
        userlist = db_helper.getvalidcategory("$txtchip")

        var connect_Adapter = Mycategory_notes_Adapter(this, userlist, prgarray)
        recCat.adapter = connect_Adapter

        //category%

        var amountlist: MutableList<Int>
        amountlist = db_helper.getfilter_catamount("$txtchip")


        var sum = 0
        for (i in 0..amountlist.size - 1) {

            var amountlistdata = sum + amountlist[i]
            sum = amountlistdata
        }
        val param = totalamount.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(10, 30, 10, 10)


        var totalamount = Integer.valueOf(totalamount.text.toString())
        var prgper = (sum * 100) / totalamount

        new_pgbar.visibility = View.VISIBLE
        new_pgbar.setProgress(prgper.toInt())

        tv_txtper.visibility = View.VISIBLE
        tv_txtper.text = " $txtchip consists $prgper % of your Total Expense "
        btn_GoHome.visibility = View.VISIBLE
        btn_addnotes.visibility = View.GONE

        gohome = true


        Log.d("userlist_data", "$prgper")

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


    override fun onBackPressed() {


        if (gohome == false) {
            var builder = AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are you sure you want to exit")
                .setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "yes", Toast.LENGTH_SHORT).show()
                    super.onBackPressed()
                    finish()
                    dialog.dismiss()
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                })
                .setNeutralButton(
                    " Goto My Home",
                    DialogInterface.OnClickListener { dialog, which ->
                        var i = Intent(this, User_Notes::class.java)
                        Toast.makeText(this, "My Task", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                        startActivity(i)
                    })
                .setCancelable(false)
            builder.show()
        } else if (gohome == true) {
            var i = Intent(this, User_Notes::class.java)
            startActivity(i)
        }
    }


}

