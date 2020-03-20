package com.example.firebaseapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager


class profile : Fragment() {
    //For Facebook Logout
    var PhotoURL : String = ""
    var Name : String = ""

    //For Go To Main Page
    private var btn_enter : Button? = null

    fun newInstance(url: String,name : String): profile {
        val profile = profile()
        val bundle = Bundle()
        bundle.putString("PhotoURL", url)
        bundle.putString("Name", name)
        profile.setArguments(bundle)
        return profile
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //For Facebook Logout Button
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val ivProfilePicture = view.findViewById(R.id.iv_profile) as ImageView
        val tvName = view.findViewById(R.id.tv_name) as TextView
        val btn_facebook_logout = view.findViewById(R.id.btn_facebook_logout) as Button
        Glide.with(activity!!.baseContext)
            .load(PhotoURL)
            .into(ivProfilePicture)
        tvName.setText(Name)


        //For Go To Main Page Button
        btn_enter = view.findViewById<Button>(R.id.btn_enter)
        btn_enter!!.setOnClickListener{
            val page_main = Page_Main()
            val frgMng = fragmentManager
            val transaction : FragmentTransaction = frgMng!!.beginTransaction()
            transaction.replace(R.id.layout, page_main,"page_main")
            transaction.addToBackStack("page_main")
            transaction.commit()
        }

        btn_facebook_logout.setOnClickListener{
            LoginManager.getInstance().logOut()
            activity!!.supportFragmentManager.popBackStack()
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            PhotoURL = bundle.getString("PhotoURL").toString()
            Name = bundle.getString("Name").toString()
        }
    }
}
