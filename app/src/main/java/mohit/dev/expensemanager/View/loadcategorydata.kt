package mohit.dev.expensemanager.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mohit.de.Category_DatabaseHelper
import mohit.dev.expensemanager.Adpter.Mycategory_Adapter
import mohit.dev.expensemanager.Model.Category_ModelClass
import mohit.dev.expensemanager.R

class loadcategorydata : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v= inflater.inflate(R.layout.fragment_loadcategorydata, container, false)
        var rec_cat=v.findViewById<RecyclerView>(R.id.frag_rec_cat)
        loadfrag_cat(rec_cat,v)
        return v
    }

    private fun loadfrag_cat(recCat: RecyclerView, v: View) {




    }

}