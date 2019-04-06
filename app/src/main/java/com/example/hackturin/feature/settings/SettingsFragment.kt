package com.example.hackturin.feature.settings

import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.SeekBar
import android.widget.Switch

import android.widget.SeekBar.OnSeekBarChangeListener
import com.example.hackturin.R


class SettingsFragment : Fragment() {

    private lateinit var viewModel: SettingsViewModel
    private lateinit var switchRecorded: Switch
    private lateinit var switchTrack: Switch
    private lateinit var seekRange: SeekBar
    private lateinit var contextActivity: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.settings_fragment, container, false)

        switchRecorded = view.findViewById(R.id.switchHistoryRecorded)
        switchTrack = view.findViewById(R.id.switchTripMode)
        seekRange = view.findViewById(R.id.seekBarRange)



        return view
    }

    override fun onAttach(context: Context?) {
        this.contextActivity = context!!
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        reloadValue()
        setupListeners()
    }


    private fun reloadValue(){
        var preferences = PreferenceManager.getDefaultSharedPreferences(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            seekRange.setProgress(preferences.getInt("range",40),true)
        }

        switchTrack.setChecked(preferences.getBoolean("tracking", true))
        switchRecorded.setChecked(preferences.getBoolean("recording", true))
    }

    private fun setupListeners() {
        switchRecorded.setOnCheckedChangeListener{ button,checked -> saveValuePreference("recording", checked ) }
        switchTrack.setOnCheckedChangeListener{ button,checked -> saveValuePreference("tracking", checked ) }
        seekRange.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                saveValuePreference("range",progress)
            }
        })
    }

    private fun saveValuePreference(name: String,value: Any){
        var preferences = PreferenceManager.getDefaultSharedPreferences(context).edit()

        if ( value is Boolean ) {
            preferences.putBoolean(name,value)
        }

        if ( value is Int ) {
            preferences.putInt(name,value)
        }

        preferences.apply()

    }

}
