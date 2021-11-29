package com.example.movielibrary.ui.settingInfo

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.example.movielibrary.R

class FragmentSetting : Fragment() {
    private val options1 = "op1"
    private val options2 = "op2"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.setting, container, false)
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val switch = view.findViewById<Switch>(R.id.switchOptions1)
        val switch2 = view.findViewById<Switch>(R.id.switchOptions2)

        val boolOp1 = loadDataOptionsSwitch(options1)
        val boolOp2 = loadDataOptionsSwitch(options2)

        if (boolOp1 != null) {
            switch.isChecked = boolOp1
        }
        if (boolOp2 != null) {
            switch2.isChecked = boolOp2
        }

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                saveOptionsSwitch(options1, true)
            } else {
                saveOptionsSwitch(options1, false)
            }
        }

        switch2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                saveOptionsSwitch(options2, true)
            } else {
                saveOptionsSwitch(options2, false)
            }
        }
    }

    private fun saveOptionsSwitch(keyOptions: String, bool: Boolean) {
        val editor = activity?.getPreferences(Context.MODE_PRIVATE)?.edit()
        editor?.putBoolean(keyOptions, bool)?.apply()
    }

    private fun loadDataOptionsSwitch(keyOptions: String) : Boolean? {
        return activity?.getPreferences(Context.MODE_PRIVATE)?.getBoolean(keyOptions, false)
    }
}