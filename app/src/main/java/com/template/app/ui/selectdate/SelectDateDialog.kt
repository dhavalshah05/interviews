package com.template.app.ui.selectdate

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.template.app.R
import com.template.app.util.bundle.getStringValueOrError
import com.template.app.util.date.DateUtils
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import java.util.*

class SelectDateDialog : DialogFragment() {

    companion object {
        const val TAG = "date_picker"
        private const val BUNDLE_REQUEST_KEY_SELECT_DATE = "REQUEST_KEY_SELECT_DATE"

        fun newInstance(requestKeySelectDate: String): SelectDateDialog {
            val bundle = Bundle()
            bundle.putString(BUNDLE_REQUEST_KEY_SELECT_DATE, requestKeySelectDate)

            val dialog = SelectDateDialog()
            dialog.arguments = bundle
            return dialog
        }
    }

    private val requestKeySelectDate: String by lazy {
        arguments.getStringValueOrError(BUNDLE_REQUEST_KEY_SELECT_DATE)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return SpinnerDatePickerDialogBuilder()
            .context(requireContext())
            .callback { _, selectedYear, selectedMonthOfYear, selectedDayOfMonth ->
                notifyParent(selectedYear, selectedMonthOfYear, selectedDayOfMonth)
            }
            .spinnerTheme(R.style.DatePickerSpinner)
            .dialogTheme(R.style.AlertDialog)
            .showTitle(true)
            .showDaySpinner(true)
            .minDate(year, month, day)
            .build()
    }

    private fun notifyParent(selectedYear: Int, selectedMonthOfYear: Int, selectedDayOfMonth: Int) {
        val date = DateUtils.getDate(selectedYear, selectedMonthOfYear, selectedDayOfMonth)
        val bundle = Bundle()
        bundle.putSerializable("date", date)
        parentFragmentManager.setFragmentResult(requestKeySelectDate, bundle)
    }

}