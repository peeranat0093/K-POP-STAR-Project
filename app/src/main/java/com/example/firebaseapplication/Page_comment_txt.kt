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

class Page_comment_txt : Fragment() {

    private var btn_back : Button?= null
    private var btn_comment_submit : Button?= null
    lateinit var comment_txt : EditText

    override fun onCreateView(inflater : LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_page_comment_txt, container, false)

        btn_back = view.findViewById<Button>(R.id.btn_back)

        btn_back!!.setOnClickListener{
            val fragmentPageTXT = Page_recycler_view_txt()
            val fm = fragmentManager
            val transaction : FragmentTransaction = fm!!.beginTransaction()
            transaction.replace(R.id.layout, fragmentPageTXT,"fragment_page_recycler_view_txt")
            transaction.addToBackStack("fragment_page_recycler_view_txt")
            transaction.commit()
        }

        comment_txt = view.findViewById<EditText>(R.id.comment_txt)
        btn_comment_submit = view.findViewById<Button>(R.id.btn_comment_submit)

        btn_comment_submit!!.setOnClickListener{
            val  builder: AlertDialog.Builder = AlertDialog.Builder(this.context)
            builder.setMessage("Do you want to confirm to save?")
            builder.setPositiveButton("Save",
                DialogInterface.OnClickListener{ dialog, id ->
                    saveCommentTXT()
                })
            builder.setNegativeButton("Cancel",
                DialogInterface.OnClickListener{ dialog, which ->
                    dialog.dismiss()
                })
            builder.show()
        }

        return view
    }

    private fun saveCommentTXT(){
        val comment_txt_detail = comment_txt!!.text.toString().trim()

        if(comment_txt_detail.isEmpty()){
            comment_txt!!.error = "Please fill reason."
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("TXT")
        val comment_txt_detail_id = ref.push().key

        val txt = comment_txt_detail_id?.let { TXT(it, comment_txt_detail) }

        comment_txt_detail_id?.let {
            ref.child(it).setValue(txt).addOnCompleteListener {
                Toast.makeText(context,"Reason Comment TXT Success", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
