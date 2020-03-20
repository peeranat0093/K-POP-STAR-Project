package com.example.firebaseapplication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

class Page_recycler_view_gfriend : Fragment() {

    private var btn_back : Button?= null
    private var btn_comment_gfriend : Button?= null

    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page_recycler_view_gfriend, container, false)
        // Inflate the layout for this fragment

        val jsonString : String = loadJsonFromAsset("GFRIEND.json", activity!!.baseContext).toString()
        val json = JSONObject(jsonString)
        val jsonArray = json.getJSONArray("GFRIEND")

        val recyclerView: RecyclerView = view.findViewById(R.id.recyLayout)

        //ตั้งค่า Layout
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)
        recyclerView.layoutManager = layoutManager

        //ตั้งค่า Adapter
        val adapter = MyRecyclerAdapter(activity!!,jsonArray)
        recyclerView.adapter = adapter

        btn_back = view.findViewById<Button>(R.id.btn_back)

        btn_back!!.setOnClickListener{
            val fragmentPageMain = Page_Main()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageMain,"fragment_page_main")
            transaction.addToBackStack("fragment_page_main")
            transaction.commit()
        }

        btn_comment_gfriend = view.findViewById<Button>(R.id.btn_comment_gfriend)

        btn_comment_gfriend!!.setOnClickListener{
            val fragmentPageCommentGFRIEND = Page_comment_gfriend()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageCommentGFRIEND,"fragment_page_comment_gfriend")
            transaction.addToBackStack("fragment_page_comment_gfriend")
            transaction.commit()
        }

        return view
    }


    private fun loadJsonFromAsset(filename: String, context: Context): String? {
        val json: String?

        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

}
