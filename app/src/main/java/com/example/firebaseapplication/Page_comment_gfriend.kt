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
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.database.FirebaseDatabase

class Page_comment_gfriend : Fragment() {

    private var btn_back : Button?= null
    private var btn_comment_submit : Button?= null
    lateinit var comment_gfriend : EditText

    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page_comment_gfriend, container, false)

        btn_back = view.findViewById<Button>(R.id.btn_back)

        btn_back!!.setOnClickListener{
            val fragmentPageGFRIEND = Page_recycler_view_gfriend()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageGFRIEND,"fragment_page_recycler_view_gfriend")
            transaction.addToBackStack("fragment_page_recycler_view_gfriend")
            transaction.commit()
        }

        comment_gfriend = view.findViewById<EditText>(R.id.comment_gfriend)
        btn_comment_submit = view.findViewById<Button>(R.id.btn_comment_submit)

        btn_comment_submit!!.setOnClickListener{
            val  builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("Do you want to confirm to save?")
            builder.setPositiveButton("Save",
                DialogInterface.OnClickListener{ dialog, id ->
                    saveCommentGFRIEND()
                })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener{ dialog, which ->
                    dialog.dismiss()
                })
            builder.show()
        }

        return view
    }

    private fun saveCommentGFRIEND(){
        val comment_gfriend_detail = comment_gfriend!!.text.toString().trim()

        if(comment_gfriend_detail.isEmpty()){
            comment_gfriend!!.error = "Please fill reason."
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("GFRIEND")
        val comment_gfriend_detail_id = ref.push().key

        val bts = comment_gfriend_detail_id?.let { GFRIEND(it, comment_gfriend_detail) }

        comment_gfriend_detail_id?.let {
            ref.child(it).setValue(bts).addOnCompleteListener {
                Toast.makeText(context,"Reason Comment GFRIEND Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
