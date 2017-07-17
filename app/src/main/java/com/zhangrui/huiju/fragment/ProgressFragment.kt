package com.kcode.gankotlin.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.zhangrui.huiju.R
import kotlinx.android.synthetic.main.loading.*
import kotlinx.android.synthetic.main.loading.view.*

/**
 *
 * Created by caik on 2017/6/6.
 */
class ProgressFragment : DialogFragment() {

    companion object {
        fun newInstance(msg: String): ProgressFragment {
            val fragment = ProgressFragment();
            if (fragment.arguments == null) {
                fragment.arguments = Bundle()
            }
            fragment.arguments.putString("msg", msg);
            return ProgressFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        val view = inflater!!.inflate(R.layout.loading, container, false);
        if (arguments!=null){
            msg.text = arguments.getString("msg");
        }

        return view
    }
}