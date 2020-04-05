package com.github.thibaultbee.srtwrapper.examples

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class MainServerSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_server, rootKey)
    }
}