package com.example.firebaseapplication

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase

class Page_comment_bts : Fragment() {

    private var btn_back : Button?= null
    private var btn_comment_submit : Button?= null
    lateinit var comment_bts : EditText

    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page_comment_bts, container, false)

        btn_back = view.findViewById<Button>(R.id.btn_back)

        btn_back!!.setOnClickListener{
            val fragmentPageBTS = Page_recycler_view_bts()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageBTS,"fragment_page_recycler_view_bts")
            transaction.addToBackStack("fragment_page_recycler_view_bts")
            transaction.commit()
        }

        comment_bts = view.findViewById<EditText>(R.id.comment_bts)
        btn_comment_submit = view.findViewById<Button>(R.id.btn_comment_submit)

        btn_comment_submit!!.setOnClickListener{
            val  builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("Do you want to confirm to save?")
            builder.setPositiveButton("Save",
                DialogInterface.OnClickListener{ dialog, id ->
                    saveCommentBTS()
                })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener{ dialog, which ->
                    dialog.dismiss()
                })
            builder.show()
        }

        return view
    }

    private fun saveCommentBTS(){
        val comment_bts_detail = comment_bts!!.text.toString().trim()

        if(comment_bts_detail.isEmpty()){
            comment_bts!!.error = "Please fill reason."
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("BTS")
        val comment_bts_detail_id = ref.push().key

        val bts = comment_bts_detail_id?.let { BTS(it, comment_bts_detail) }

        comment_bts_detail_id?.let {
            ref.child(it).setValue(bts).addOnCompleteListener {
                Toast.makeText(context,"Reason Comment BTS Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
