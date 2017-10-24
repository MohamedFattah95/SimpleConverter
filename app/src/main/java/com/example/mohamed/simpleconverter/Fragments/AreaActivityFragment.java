package com.example.mohamed.simpleconverter.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import com.example.mohamed.simpleconverter.Converter.Area;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class AreaActivityFragment extends Fragment {
    public RadioGroup rgFrom, rgTo;
    RadioButton rbSqCM1, rbSqM1, rbSqKM1, rbSqI1, rbSqF1, rbSqY1, rbSqMil1, rbAcre1;
    RadioButton rbSqCM2, rbSqM2, rbSqKM2, rbSqI2, rbSqF2, rbSqY2, rbSqMil2, rbAcre2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public AreaActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_area, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbSqCM1 = (RadioButton) rootView.findViewById(R.id.rbSqCent1);
        rbSqCM2 = (RadioButton) rootView.findViewById(R.id.rbSqCent2);
        rbSqM1 = (RadioButton) rootView.findViewById(R.id.rbSqMetre1);
        rbSqM2 = (RadioButton) rootView.findViewById(R.id.rbSqMetre2);
        rbSqKM1 = (RadioButton) rootView.findViewById(R.id.rbSqKilo1);
        rbSqKM2 = (RadioButton) rootView.findViewById(R.id.rbSqKilo2);
        rbSqI1 = (RadioButton) rootView.findViewById(R.id.rbSqInch1);
        rbSqI2 = (RadioButton) rootView.findViewById(R.id.rbSqInch2);
        rbSqF1 = (RadioButton) rootView.findViewById(R.id.rbSqFoot1);
        rbSqF2 = (RadioButton) rootView.findViewById(R.id.rbSqFoot2);
        rbSqY1 = (RadioButton) rootView.findViewById(R.id.rbSqYard1);
        rbSqY2 = (RadioButton) rootView.findViewById(R.id.rbSqYard2);
        rbSqMil1 = (RadioButton) rootView.findViewById(R.id.rbSqMile1);
        rbSqMil2 = (RadioButton) rootView.findViewById(R.id.rbSqMile2);
        rbAcre1 = (RadioButton) rootView.findViewById(R.id.rbAcre1);
        rbAcre2 = (RadioButton) rootView.findViewById(R.id.rbAcre2);

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
                    value = Area.sqCMToSqM(value);
                else if (from == 2)
                    value = Area.sqMToSqKM(value);
                else if (from == 3)
                    value = Area.sqKMToSqI(value);
                else if (from == 4)
                    value = Area.sqIToSqF(value);
                else if (from == 5)
                    value = Area.sqFToSqY(value);
                else if (from == 6)
                    value = Area.sqYToSqMi(value);
                else if (from == 7)
                    value = Area.sqMiToAcre(value);
                from++;
            } else {
                if (from == 8)
                    value = Area.acreToSqMi(value);
                else if (from == 7)
                    value = Area.sqMiToSqY(value);
                else if (from == 6)
                    value = Area.sqYToSqF(value);
                else if (from == 5)
                    value = Area.sqFToSqI(value);
                else if (from == 4)
                    value = Area.sqIToSqKM(value);
                else if (from == 3)
                    value = Area.sqKMToSqM(value);
                else if (from == 2)
                    value = Area.sqMToSqCM(value);
                from--;
            }
        }
        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbSqCM1.isChecked())
            return 1;
        if (rbSqM1.isChecked())
            return 2;
        if (rbSqKM1.isChecked())
            return 3;
        if (rbSqI1.isChecked())
            return 4;
        if (rbSqF1.isChecked())
            return 5;
        if (rbSqY1.isChecked())
            return 6;
        if (rbSqMil1.isChecked())
            return 7;
        if (rbAcre1.isChecked())
            return 8;
        return 0;
    }

    private int to() {
        if (rbSqCM2.isChecked())
            return 1;
        if (rbSqM2.isChecked())
            return 2;
        if (rbSqKM2.isChecked())
            return 3;
        if (rbSqI2.isChecked())
            return 4;
        if (rbSqF2.isChecked())
            return 5;
        if (rbSqY2.isChecked())
            return 6;
        if (rbSqMil2.isChecked())
            return 7;
        if (rbAcre2.isChecked())
            return 8;
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
