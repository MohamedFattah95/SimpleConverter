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

import com.example.mohamed.simpleconverter.Converter.Length;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class LengthActivityFragment extends Fragment {

    public RadioGroup rgFrom, rgTo;
    RadioButton rbNanometre1, rbMicrometre1, rbMillimetre1, rbCentimetre1, rbMetre1, rbKilometre1, rbMile1, rbNauticalMile1, rbInch1, rbFeet1, rbYard1, rbLightYear1;
    RadioButton rbNanometre2, rbMicrometre2, rbMillimetre2, rbCentimetre2, rbMetre2, rbKilometre2, rbMile2, rbNauticalMile2, rbInch2, rbFeet2, rbYard2, rbLightYear2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public LengthActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_length, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbNanometre1 = (RadioButton) rootView.findViewById(R.id.rbNanometre1);
        rbNanometre2 = (RadioButton) rootView.findViewById(R.id.rbNanometre2);
        rbMicrometre1 = (RadioButton) rootView.findViewById(R.id.rbMicrometre1);
        rbMicrometre2 = (RadioButton) rootView.findViewById(R.id.rbMicrometre2);
        rbMillimetre1 = (RadioButton) rootView.findViewById(R.id.rbMillimetre1);
        rbMillimetre2 = (RadioButton) rootView.findViewById(R.id.rbMillimetre2);
        rbCentimetre1 = (RadioButton) rootView.findViewById(R.id.rbCentimetre1);
        rbCentimetre2 = (RadioButton) rootView.findViewById(R.id.rbCentimetre2);
        rbMetre1 = (RadioButton) rootView.findViewById(R.id.rbMetre1);
        rbMetre2 = (RadioButton) rootView.findViewById(R.id.rbMetre2);
        rbKilometre1 = (RadioButton) rootView.findViewById(R.id.rbKilometre1);
        rbKilometre2 = (RadioButton) rootView.findViewById(R.id.rbKilometre2);
        rbMile1 = (RadioButton) rootView.findViewById(R.id.rbMile1);
        rbMile2 = (RadioButton) rootView.findViewById(R.id.rbMile2);
        rbNauticalMile1 = (RadioButton) rootView.findViewById(R.id.rbNMile1);
        rbNauticalMile2 = (RadioButton) rootView.findViewById(R.id.rbNMile2);
        rbInch1 = (RadioButton) rootView.findViewById(R.id.rbInch1);
        rbInch2 = (RadioButton) rootView.findViewById(R.id.rbInch2);
        rbFeet1 = (RadioButton) rootView.findViewById(R.id.rbFeet1);
        rbFeet2 = (RadioButton) rootView.findViewById(R.id.rbFeet2);
        rbYard1 = (RadioButton) rootView.findViewById(R.id.rbYard1);
        rbYard2 = (RadioButton) rootView.findViewById(R.id.rbYard2);
        rbLightYear1 = (RadioButton) rootView.findViewById(R.id.rbLYear1);
        rbLightYear2 = (RadioButton) rootView.findViewById(R.id.rbLYear2);

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
                    value = Length.nanometreToMicrometer(value);
                else if (from == 2)
                    value = Length.micrometerToMillimeter(value);
                else if (from == 3)
                    value = Length.millimeterToCentimeter(value);
                else if (from == 4)
                    value = Length.centimeterToMeter(value);
                else if (from == 5)
                    value = Length.meterToKilometer(value);
                else if (from == 6)
                    value = Length.kilometerToMile(value);
                else if (from == 7)
                    value = Length.mileToNauticalMaile(value);
                else if (from == 8)
                    value = Length.nauticalmileToInch(value);
                else if (from == 9)
                    value = Length.inchToFeet(value);
                else if (from == 10)
                    value = Length.feetToYard(value);
                else if (from == 11)
                    value = Length.yardToLightYear(value);
                from++;
            } else {
                if (from == 12)
                    value = Length.lightYearToYard(value);
                else if (from == 11)
                    value = Length.yardToFeet(value);
                else if (from == 10)
                    value = Length.feetToInch(value);
                else if (from == 9)
                    value = Length.inchToNauticalmile(value);
                else if (from == 8)
                    value = Length.nauticalMaileToMile(value);
                else if (from == 7)
                    value = Length.mileToKilometer(value);
                else if (from == 6)
                    value = Length.kilometerToMeter(value);
                else if (from == 5)
                    value = Length.meterToCentimeter(value);
                else if (from == 4)
                    value = Length.centimeterToMillimeter(value);
                else if (from == 3)
                    value = Length.millimeterToMicrometer(value);
                else if (from == 2)
                    value = Length.micrometerToNanometre(value);
                from--;
            }
        }
        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbNanometre1.isChecked())
            return 1;
        if (rbMicrometre1.isChecked())
            return 2;
        if (rbMillimetre1.isChecked())
            return 3;
        if (rbCentimetre1.isChecked())
            return 4;
        if (rbMetre1.isChecked())
            return 5;
        if (rbKilometre1.isChecked())
            return 6;
        if (rbMile1.isChecked())
            return 7;
        if (rbNauticalMile1.isChecked())
            return 8;
        if (rbInch1.isChecked())
            return 9;
        if (rbFeet1.isChecked())
            return 10;
        if (rbYard1.isChecked())
            return 11;
        if (rbLightYear1.isChecked())
            return 12;
        return 0;
    }

    private int to() {
        if (rbNanometre2.isChecked())
            return 1;
        if (rbMicrometre2.isChecked())
            return 2;
        if (rbMillimetre2.isChecked())
            return 3;
        if (rbCentimetre2.isChecked())
            return 4;
        if (rbMetre2.isChecked())
            return 5;
        if (rbKilometre2.isChecked())
            return 6;
        if (rbMile2.isChecked())
            return 7;
        if (rbNauticalMile2.isChecked())
            return 8;
        if (rbInch2.isChecked())
            return 9;
        if (rbFeet2.isChecked())
            return 10;
        if (rbYard2.isChecked())
            return 11;
        if (rbLightYear2.isChecked())
            return 12;
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
