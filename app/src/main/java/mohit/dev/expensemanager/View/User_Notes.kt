package mohit.dev.expensemanager.View

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import mohit.dev.expensemanager.Adpter.Mynotes_Adapter
import mohit.dev.expensemanager.Database.note_database
import mohit.dev.expensemanager.Model.Notes_ModelClass
import mohit.dev.expensemanager.R
import java.util.*

class User_Notes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_notes)

        var Todaydate = Calendar.getInstance()
        var year = Todaydate.get(Calendar.YEAR)
        var month = Todaydate.get(Calendar.MONTH)
        var day = Todaydate.get(Calendar.DAY_OF_MONTH)


        var tv_curmonth = findViewById<TextView>(R.id.tv_curmonth)
        var btn_addnotes = findViewById<ExtendedFloatingActionButton>(R.id.btn_addnotes)
        var rec_notes = findViewById<RecyclerView>(R.id.rec_savednotes)


        //Todo:Make recycler view like we did in other example and set the data as we did with
        //TODO:Follow this method



            rec_notes.layoutManager = LinearLayoutManager(this)

            var db_helper = note_database(this)

            var userlist: MutableList<Notes_ModelClass>
            userlist = db_helper.getall_Note()

            var connect_Adapter = Mynotes_Adapter(this, userlist)
            rec_notes.adapter = connect_Adapter


        btn_addnotes.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

//       var curmonth=tv_curmonth.text
//
//        when(month){
//
//            6->curmonth="July"
//        }


    }


}