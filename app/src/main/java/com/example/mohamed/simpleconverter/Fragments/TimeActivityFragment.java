package com.example.mohamed.simpleconverter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.simpleconverter.Converter.Time;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TimeActivityFragment extends Fragment {
    public RadioGroup rgFrom, rgTo;
    RadioButton rbNanosec1, rbMicrosec1, rbMillisec1, rbSec1, rbMin1, rbHour1, rbDay1, rbWeek1, rbMonth1, rbYear1, rbCentury1;
    RadioButton rbNanosec2, rbMicrosec2, rbMillisec2, rbSec2, rbMin2, rbHour2, rbDay2, rbWeek2, rbMonth2, rbYear2, rbCentury2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public TimeActivityFragment() {
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (etValue.getText().toString().length() == 15)
                Toast.makeText(getContext(), R.string.msg, Toast.LENGTH_SHORT).show();
            if (s.toString().equals(".") && start == 0) {
                etValue.setText("0.");
                etValue.setSelection(etValue.getText().length());
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            convert();
        }
    };

    private RadioGroup.OnCheckedChangeListener rglistener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            setUnit(group,checkedId);
            convert();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_time, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbNanosec1 = (RadioButton) rootView.findViewById(R.id.rbNanoSec1);
        rbNanosec2 = (RadioButton) rootView.findViewById(R.id.rbNanoSec2);
        rbMicrosec1 = (RadioButton) rootView.findViewById(R.id.rbMicroSec1);
        rbMicrosec2 = (RadioButton) rootView.findViewById(R.id.rbMicroSec2);
        rbMillisec1 = (RadioButton) rootView.findViewById(R.id.rbMilliSec1);
        rbMillisec2 = (RadioButton) rootView.findViewById(R.id.rbMilliSec2);
        rbSec1 = (RadioButton) rootView.findViewById(R.id.rbSecond1);
        rbSec2 = (RadioButton) rootView.findViewById(R.id.rbSecond2);
        rbMin1 = (RadioButton) rootView.findViewById(R.id.rbMinute1);
        rbMin2 = (RadioButton) rootView.findViewById(R.id.rbMinute2);
        rbHour1 = (RadioButton) rootView.findViewById(R.id.rbHour1);
        rbHour2 = (RadioButton) rootView.findViewById(R.id.rbHour2);
        rbDay1 = (RadioButton) rootView.findViewById(R.id.rbDay1);
        rbDay2 = (RadioButton) rootView.findViewById(R.id.rbDay2);
        rbWeek1 = (RadioButton) rootView.findViewById(R.id.rbWeek1);
        rbWeek2 = (RadioButton) rootView.findViewById(R.id.rbWeek2);
        rbMonth1 = (RadioButton) rootView.findViewById(R.id.rbMonth1);
        rbMonth2 = (RadioButton) rootView.findViewById(R.id.rbMonth2);
        rbYear1 = (RadioButton) rootView.findViewById(R.id.rbYear1);
        rbYear2 = (RadioButton) rootView.findViewById(R.id.rbYear2);
        rbCentury1 = (RadioButton) rootView.findViewById(R.id.rbCentury1);
        rbCentury2 = (RadioButton) rootView.findViewById(R.id.rbCentury2);

        etValue = (EditText) rootView.findViewById(R.id.etValue);
        etResult = (EditText) rootView.findViewById(R.id.etResult);

        tvFromVal = (TextView) rootView.findViewById(R.id.tvFromVal);
        tvToRes = (TextView) rootView.findViewById(R.id.tvToRes);

        etValue.addTextChangedListener(mTextWatcher);
        rgFrom.setOnCheckedChangeListener(rglistener);
        rgTo.setOnCheckedChangeListener(rglistener);

        return rootView;
    }

    private void convert() {
        if (etValue.getText().toString().equals("")) {
            etResult.setText(R.string.result);
            return;
        }
        int from = from();
        int to = to();
        double value = Double.parseDouble(etValue.getText().toString());
        while (from != to) {
            if (from < to) {
                if (from == 1)
                    value = Time.nanosecToMicrosec(value);
                else if (from == 2)
                    value = Time.microsecToMillisec(value);
                else if (from == 3)
                    value = Time.millisecToSeconds(value);
                else if (from == 4)
                    value = Time.secondsToMinutes(value);
                else if (from == 5)
                    value = Time.minutesToHour(value);
                else if (from == 6)
                    value = Time.hourToDay(value);
                else if (from == 7)
                    value = Time.dayToWeek(value);
                else if (from == 8)
                    value = Time.weekToMonth(value);
                else if (from == 9)
                    value = Time.monthToYear(value);
                else if (from == 10)
                    value = Time.yearToCentury(value);
                from++;
            } else {
                if (from == 11)
                    value = Time.centuryToYear(value);
                else if (from == 10)
                    value = Time.yearToMonth(value);
                else if (from == 9)
                    value = Time.monthToWeek(value);
                else if (from == 8)
                    value = Time.weekToDay(value);
                else if (from == 7)
                    value = Time.dayToHour(value);
                else if (from == 6)
                    value = Time.hourToMinutes(value);
                else if (from == 5)
                    value = Time.minutesToSeconds(value);
                else if (from == 4)
                    value = Time.secondsToMillisec(value);
                else if (from == 3)
                    value = Time.millisecToMicrosec(value);
                else if (from == 2)
                    value = Time.microsecToNanosec(value);
                from--;
            }
        }
        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbNanosec1.isChecked())
            return 1;
        if (rbMicrosec1.isChecked())
            return 2;
        if (rbMillisec1.isChecked())
            return 3;
        if (rbSec1.isChecked())
            return 4;
        if (rbMin1.isChecked())
            return 5;
        if (rbHour1.isChecked())
            return 6;
        if (rbDay1.isChecked())
            return 7;
        if (rbWeek1.isChecked())
            return 8;
        if (rbMonth1.isChecked())
            return 9;
        if (rbYear1.isChecked())
            return 10;
        if (rbCentury1.isChecked())
            return 11;
        return 0;
    }

    private int to() {
        if (rbNanosec2.isChecked())
            return 1;
        if (rbMicrosec2.isChecked())
            return 2;
        if (rbMillisec2.isChecked())
            return 3;
        if (rbSec2.isChecked())
            return 4;
        if (rbMin2.isChecked())
            return 5;
        if (rbHour2.isChecked())
            return 6;
        if (rbDay2.isChecked())
            return 7;
        if (rbWeek2.isChecked())
            return 8;
        if (rbMonth2.isChecked())
            return 9;
        if (rbYear2.isChecked())
            return 10;
        if (rbCentury2.isChecked())
            return 11;
        return 0;
    }
    public void setUnit(RadioGroup group ,int checkedId){
        TextView textView;
        if(group.getId()==rgFrom.getId())
            textView=tvFromVal;
        else
            textView=tvToRes;
        textView.setText(((RadioButton)group.findViewById(checkedId)).getText());
    }
}
