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

import com.example.mohamed.simpleconverter.Converter.Temperature;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TemperatureActivityFragment extends Fragment {

    public RadioGroup rgFrom, rgTo;
    RadioButton rbCelsius1, rbKelvin1, rbFahrenheit1, rbCelsius2, rbKelvin2, rbFahrenheit2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public TemperatureActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_temperature, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbCelsius1 = (RadioButton) rootView.findViewById(R.id.rbCelsius1);
        rbCelsius2 = (RadioButton) rootView.findViewById(R.id.rbCelsius2);
        rbFahrenheit1 = (RadioButton) rootView.findViewById(R.id.rbFahrenheit1);
        rbFahrenheit2 = (RadioButton) rootView.findViewById(R.id.rbFahrenheit2);
        rbKelvin1 = (RadioButton) rootView.findViewById(R.id.rbKelvin1);
        rbKelvin2 = (RadioButton) rootView.findViewById(R.id.rbKelvin2);

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
                    value = Temperature.celsiusToKelvin(value);
                else if (from == 2)
                    value = Temperature.kelvinToFahrenheit(value);
                from++;
            } else {
                if (from == 3)
                    value = Temperature.fahrenheitToKelvin(value);
                else if (from == 2)
                    value = Temperature.kelvinToCelsius(value);
                from--;
            }
        }
        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbCelsius1.isChecked())
            return 1;
        if (rbKelvin1.isChecked())
            return 2;
        if (rbFahrenheit1.isChecked())
            return 3;
        return 0;
    }

    private int to() {
        if (rbCelsius2.isChecked())
            return 1;
        if (rbKelvin2.isChecked())
            return 2;
        if (rbFahrenheit2.isChecked())
            return 3;
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
