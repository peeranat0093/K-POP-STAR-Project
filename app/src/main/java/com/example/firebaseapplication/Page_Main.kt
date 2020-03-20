package com.example.firebaseapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class Page_Main : Fragment() {

    private var btn_bts : Button? = null
    private var btn_txt : Button? = null
    private var btn_gfriend : Button? = null
    private var btn_back : Button?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_page_main, container, false)
        // Inflate the layout for this fragment

        btn_bts = view.findViewById<Button>(R.id.btn_bts)
        btn_txt = view.findViewById<Button>(R.id.btn_txt)
        btn_gfriend = view.findViewById<Button>(R.id.btn_gfriend)
        btn_back = view.findViewById<Button>(R.id.btn_back)

        btn_bts!!.setOnClickListener{
            Toast.makeText(context,"BTS Member Details", Toast.LENGTH_SHORT).show()
            val fragmentPageBTS = Page_recycler_view_bts()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageBTS,"fragment_page_recycler_view_bts")
            transaction.addToBackStack("fragment_page_recycler_view_bts")
            transaction.commit()
        }

        btn_txt!!.setOnClickListener{
            Toast.makeText(context,"TXT Member Details", Toast.LENGTH_SHORT).show()
            val fragmentPageTXT = Page_recycler_view_txt()

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageTXT,"fragment_page_recycler_view_txt")
            transaction.addToBackStack("fragment_page_recycler_view_txt")
            transaction.commit()
        }

        btn_gfriend!!.setOnClickListener{
            Toast.makeText(context,"GFRIEND Member Details", Toast.LENGTH_SHORT).show()
            val fragmentPageGFRIEND = Page_recycler_view_gfriend()

            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageGFRIEND,"fragment_page_recycler_view_gfriend")
            transaction.addToBackStack("fragment_page_recycler_view_gfriend")
            transaction.commit()
        }

        btn_back!!.setOnClickListener{
            val fragmentPageLogin = authen()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageLogin,"fragment_authen")
            transaction.addToBackStack("fragment_authen")
            transaction.commit()
        }
        return view;
    }

}
