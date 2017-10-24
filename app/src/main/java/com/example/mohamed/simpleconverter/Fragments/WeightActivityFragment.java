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

import com.example.mohamed.simpleconverter.Converter.Weight;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeightActivityFragment extends Fragment {

    public RadioGroup rgFrom, rgTo;
    RadioButton rbGram1, rbKilogram1, rbPound1, rbTon1, rbGram2, rbKilogram2, rbPound2, rbTon2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public WeightActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_weight, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbGram1 = (RadioButton) rootView.findViewById(R.id.rbGram1);
        rbGram2 = (RadioButton) rootView.findViewById(R.id.rbGram2);
        rbKilogram1 = (RadioButton) rootView.findViewById(R.id.rbKilogram1);
        rbKilogram2 = (RadioButton) rootView.findViewById(R.id.rbKilogram2);
        rbPound1 = (RadioButton) rootView.findViewById(R.id.rbPound1);
        rbPound2 = (RadioButton) rootView.findViewById(R.id.rbPound2);
        rbTon1 = (RadioButton) rootView.findViewById(R.id.rbTon1);
        rbTon2 = (RadioButton) rootView.findViewById(R.id.rbTon2);

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
                    value = Weight.gramToKilogram(value);
                else if (from == 2)
                    value = Weight.kilogramToPound(value);
                else if (from == 3)
                    value = Weight.poundToTonne(value);
                from++;
            } else {
                if (from == 4)
                    value = Weight.tonneToPound(value);
                else if (from == 3)
                    value = Weight.poundTokilogram(value);
                else if (from == 2)
                    value = Weight.kilogramToGram(value);
                from--;
            }
        }
        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbGram1.isChecked())
            return 1;
        if (rbKilogram1.isChecked())
            return 2;
        if (rbPound1.isChecked())
            return 3;
        if (rbTon1.isChecked())
            return 4;
        return 0;
    }

    private int to() {
        if (rbGram2.isChecked())
            return 1;
        if (rbKilogram2.isChecked())
            return 2;
        if (rbPound2.isChecked())
            return 3;
        if (rbTon2.isChecked())
            return 4;
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
