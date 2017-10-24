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

import com.example.mohamed.simpleconverter.Converter.DigitalStorage;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DigitalStorageActivityFragment extends Fragment {

    public RadioGroup rgFrom, rgTo;
    RadioButton rbBit1, rbByte1,rbKilo1, rbMega1, rbGiga1, rbTera1, rbPeta1, rbExa1, rbZeta1;
    RadioButton rbBit2, rbByte2,rbKilo2, rbMega2, rbGiga2, rbTera2, rbPeta2, rbExa2, rbZeta2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public DigitalStorageActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_digital_storage, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbBit1 = (RadioButton) rootView.findViewById(R.id.rbBit1);
        rbByte1 = (RadioButton) rootView.findViewById(R.id.rbByte1);
        rbKilo1 = (RadioButton) rootView.findViewById(R.id.rbKilo1);
        rbMega1 = (RadioButton) rootView.findViewById(R.id.rbMega1);
        rbGiga1 = (RadioButton) rootView.findViewById(R.id.rbGiga1);
        rbTera1 = (RadioButton) rootView.findViewById(R.id.rbTera1);
        rbPeta1 = (RadioButton) rootView.findViewById(R.id.rbPeta1);
        rbExa1 = (RadioButton) rootView.findViewById(R.id.rbExa1);
        rbZeta1 = (RadioButton) rootView.findViewById(R.id.rbZeta1);

        rbBit2 = (RadioButton) rootView.findViewById(R.id.rbBit2);
        rbByte2 = (RadioButton) rootView.findViewById(R.id.rbByte2);
        rbKilo2 = (RadioButton) rootView.findViewById(R.id.rbKilo2);
        rbMega2 = (RadioButton) rootView.findViewById(R.id.rbMega2);
        rbGiga2 = (RadioButton) rootView.findViewById(R.id.rbGiga2);
        rbTera2 = (RadioButton) rootView.findViewById(R.id.rbTera2);
        rbPeta2 = (RadioButton) rootView.findViewById(R.id.rbPeta2);
        rbExa2 = (RadioButton) rootView.findViewById(R.id.rbExa2);
        rbZeta2 = (RadioButton) rootView.findViewById(R.id.rbZeta2);

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
        if (from == to) {
            etResult.setText(etValue.getText());
            return;
        }
        if (from == 0)
            value = DigitalStorage.bitToByte(DigitalStorage.division(value, to - from - 1));
        else if (to == 0)
            value = DigitalStorage.byteToBit(DigitalStorage.multiply(value, from - to - 1));
        else if (from > to)
            value = DigitalStorage.multiply(value, from - to);
        else if (to > from)
            value = DigitalStorage.division(value, to - from);

        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbBit1.isChecked())
            return 0;
        if (rbByte1.isChecked())
            return 1;
        if (rbKilo1.isChecked())
            return 2;
        if (rbMega1.isChecked())
            return 3;
        if (rbGiga1.isChecked())
            return 4;
        if (rbTera1.isChecked())
            return 5;
        if (rbPeta1.isChecked())
            return 6;
        if (rbExa1.isChecked())
            return 7;
        if (rbZeta1.isChecked())
            return 8;
        return 0;
    }

    private int to() {
        if (rbBit2.isChecked())
            return 0;
        if (rbByte2.isChecked())
            return 1;
        if (rbKilo2.isChecked())
            return 2;
        if (rbMega2.isChecked())
            return 3;
        if (rbGiga2.isChecked())
            return 4;
        if (rbTera2.isChecked())
            return 5;
        if (rbPeta2.isChecked())
            return 6;
        if (rbExa2.isChecked())
            return 7;
        if (rbZeta2.isChecked())
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
