package mohit.dev.expensemanager.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mohit.dev.expensemanager.Adpter.MyViewPager_Adapter
import mohit.dev.expensemanager.Adpter.Mynotes_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R

class User_Notes : AppCompatActivity() {

    private lateinit var viewpage: ViewPager
    private lateinit var viewPagerAdapter: MyViewPager_Adapter


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {




        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notes)

        //view pager1
        /*
           viewpage = findViewById(R.id.viewpager)
           var viewPagerAdapter = MyViewPager_Adapter(this)
           viewpage.adapter = viewPagerAdapter

         */

        var currentmonth = findViewById<Spinner>(R.id.id_spinner)
        var totalamount = findViewById<TextView>(R.id.tv_totalExpence)
        var btn_addnotes = findViewById<ExtendedFloatingActionButton>(R.id.btn_addnotes)
        var rec_notes = findViewById<RecyclerView>(R.id.rec_savednotes)
        var tv_Leftcash = findViewById<TextView>(R.id.tv_Leftcash)
        var txt_budget = findViewById<TextView>(R.id.txt_budget)
        var viewhistory = findViewById<ImageView>(R.id.viewhistory)
       // var start_app = findViewById<Button>(R.id.start_app)
       //var layout_notesmain = findViewById<RelativeLayout>(R.id.layout_notesmain)
        //View Pager 2
/*
        start_app.setOnClickListener {

            layout_notesmain.visibility=View.VISIBLE
            viewpage.visibility=View.GONE
            start_app.visibility=View.GONE



            viewhistory.setOnClickListener {
                var i = Intent(this, Monthly_History::class.java)
                startActivity(i)

            }
            currentmonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {


                    setmonth(position, txt_budget)
                    amountleft(txt_budget, totalamount, tv_Leftcash)

                    Log.d("spinnerdata","${"$position =" + currentmonth.getItemAtPosition(position)}")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }

            loadrecview(rec_notes)

            loadamount(totalamount)



            if (txt_budget.text != null && totalamount.text != null) {
                amountleft(txt_budget, totalamount, tv_Leftcash)
            } else {
                Toast.makeText(this, "setting values to zero", Toast.LENGTH_SHORT).show()
                txt_budget.text == "0" && totalamount.text == "0" && tv_Leftcash.text == "0"
            }



            btn_addnotes.setOnClickListener {
                var i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }

        }

 */


        var prgarray= ArrayList<Int>()

        viewhistory.setOnClickListener {
            var i = Intent(this, Monthly_History::class.java)
            startActivity(i)

        }
        currentmonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                setmonth(position, txt_budget)
                amountleft(txt_budget, totalamount, tv_Leftcash)

                Log.d("spinnerdata", "${"$position =" + currentmonth.getItemAtPosition(position)}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


        loadrecview(rec_notes,totalamount,prgarray)

        loadamount(totalamount,prgarray)



        if (txt_budget.text != null && totalamount.text != null) {
            amountleft(txt_budget, totalamount, tv_Leftcash)
        } else {
            Toast.makeText(this, "setting values to zero", Toast.LENGTH_SHORT).show()
            txt_budget.text == "0" && totalamount.text == "0" && tv_Leftcash.text == "0"
        }



        btn_addnotes.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }


    private fun loadamount(totalamount: TextView, prgarray: ArrayList<Int>) {
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


        var prg=0
        for (i in 0..amountlist.size-1){
             prg =(amountlist.get(i) * 100/sum).toInt()
            Log.d("progress","$prg")
           // Log.d("progressarray","$prg")
            prgarray.add(prg)
            Log.d("progressarray","$prgarray")
        }


    }


    private fun amountleft(txt_budget: TextView, totalamount: TextView, tv_Leftcash: TextView) {
        var intbug = Integer.valueOf(txt_budget.text.toString())
        var inttotal = Integer.valueOf(totalamount.text.toString())

        var intleft = (intbug - inttotal)
        tv_Leftcash.setTextColor(ContextCompat.getColor(this, R.color.green));
        tv_Leftcash.text = (intbug - inttotal).toString() + "/-left"

        if (intleft <= 0) {
            tv_Leftcash.text = (intbug - inttotal).toString() + "/- debt"
            tv_Leftcash.setTextColor(ContextCompat.getColor(this, R.color.red));
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

        var connect_Adapter = Mynotes_Adapter(this, userlist,prgarray)
        rec_notes.adapter = connect_Adapter

    }

    private fun setmonth(month: Int, txt_budget: TextView) {

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
}