package com.example.android.newsfeed

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.preference.ListPreference
import android.preference.Preference
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.Preference.OnPreferenceClickListener
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.view.MenuItem
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    class NewsPreferenceFragment : PreferenceFragment(), OnPreferenceChangeListener,
        OnDateSetListener {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.settings_main)

            val numOfItems = findPreference(getString(R.string.settings_number_of_items_key))
            bindPreferenceSummaryToValue(numOfItems)

            val orderBy = findPreference(getString(R.string.settings_order_by_key))
            bindPreferenceSummaryToValue(orderBy)

            val orderDate = findPreference(getString(R.string.settings_order_date_key))
            bindPreferenceSummaryToValue(orderDate)

            val colorTheme = findPreference(getString(R.string.settings_color_key))
            bindPreferenceSummaryToValue(colorTheme)

            val textSize = findPreference(getString(R.string.settings_text_size_key))
            bindPreferenceSummaryToValue(textSize)

            val fromDate = findPreference(getString(R.string.settings_from_date_key))
            setOnPreferenceClick(fromDate)
            bindPreferenceSummaryToValue(fromDate)
        }

        private fun setOnPreferenceClick(preference: Preference) {
            preference.onPreferenceClickListener = OnPreferenceClickListener { preference ->
                val key = preference.key
                if (key.equals(getString(R.string.settings_from_date_key), ignoreCase = true)) {
                    showDatePicker()
                }
                false
            }
        }

        private fun showDatePicker() {
            val calendar = Calendar.getInstance()
            val year = calendar[Calendar.YEAR]
            val month = calendar[Calendar.MONTH]
            val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
            DatePickerDialog(
                activity,
                this, year, month, dayOfMonth
            ).show()
        }

        override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
            var month = month
            month = month + 1
            val selectedDate = "$year-$month-$dayOfMonth"
            val formattedDate = formatDate(selectedDate)

            val prefs = PreferenceManager.getDefaultSharedPreferences(activity)
            val editor = prefs.edit()
            editor.putString(getString(R.string.settings_from_date_key), formattedDate).apply()

            val fromDatePreference = findPreference(getString(R.string.settings_from_date_key))
            bindPreferenceSummaryToValue(fromDatePreference)
        }

        override fun onPreferenceChange(preference: Preference, value: Any): Boolean {
            val stringValue = value.toString()
            if (preference is ListPreference) {
                val listPreference = preference
                val prefIndex = listPreference.findIndexOfValue(stringValue)
                if (prefIndex >= 0) {
                    val labels = listPreference.entries
                    preference.setSummary(labels[prefIndex])
                }
            } else {
                preference.summary = stringValue
            }
            return true
        }

        private fun bindPreferenceSummaryToValue(preference: Preference) {
            preference.onPreferenceChangeListener = this
            val preferences =
                PreferenceManager.getDefaultSharedPreferences(preference.context)
            val preferenceString = preferences.getString(preference.key, "")
            onPreferenceChange(preference, preferenceString!!)
        }

        private fun formatDate(dateString: String): String {
            val simpleDateFormat = SimpleDateFormat("yyyy-M-d")
            var dateObject: Date? = null
            try {
                dateObject = simpleDateFormat.parse(dateString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val df = SimpleDateFormat("yyyy-MM-dd")
            return df.format(dateObject)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
