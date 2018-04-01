package lucian.example.com.projetcircuits.Data;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

import lucian.example.com.projetcircuits.R;

/**
 * Created by lucian on 2018-03-30.
 */

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        month++;
        String dateChoisie = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

        EditText editDepart = ( EditText) getActivity().findViewById(R.id.arrivee);
        editDepart.setText(dateChoisie);
    }

/*
    static final int START_DATE = 1;
    static final int END_DATE = 2;

    private int mChosenDate;
    String dateChoisie;

    int cur = 0;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mChosenDate = bundle.getInt("DATE", 1);
        }


        switch (mChosenDate) {

            case START_DATE:
                cur = START_DATE;
                return new DatePickerDialog(getActivity(), this, year, month, day);

            case END_DATE:
                cur = END_DATE;
                return new DatePickerDialog(getActivity(), this, year, month, day);

        }
        return null;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        if (cur == START_DATE) {
            // set selected date into textview
            Log.v("Date Début", "Date1 : " + new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            dateChoisie = String.valueOf(year) + "-" + String.valueOf(month+1) + "-" + String.valueOf(day);

            EditText editDepart = ( EditText) getActivity().findViewById(R.id.depart);
            editDepart.setText(dateChoisie);

        } else if(cur == END_DATE) {
            Log.v("Date fin", "Date2 : " + new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
                    .append(" "));

            dateChoisie = String.valueOf(year) + "-" + String.valueOf(month+1) + "-" + String.valueOf(day);

            EditText editArrivee = ( EditText) getActivity().findViewById(R.id.arrivee);
            editArrivee.setText(dateChoisie);
        }
    }


*/

}
