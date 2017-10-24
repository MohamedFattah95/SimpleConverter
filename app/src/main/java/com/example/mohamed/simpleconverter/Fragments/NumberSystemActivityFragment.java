package com.example.mohamed.simpleconverter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.simpleconverter.Converter.NumberSystem;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class NumberSystemActivityFragment extends Fragment {

    CardView crKeyboard;
    public RadioGroup rgFrom, rgTo;
    RadioButton rbBinary1, rbBinary2, rbDecimal1, rbDecimal2, rbOctal1, rbOctal2, rbHex1, rbHex2;
    EditText etValue, etResult;
    Button btA, btB, btC, btD, btE, btF;
    TextView tvFromVal, tvToRes;

    public NumberSystemActivityFragment() {
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
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
            if (rbHex1.isChecked())
                crKeyboard.setVisibility(CardView.VISIBLE);
            else
                crKeyboard.setVisibility(CardView.GONE);
            setUnit(group, checkedId);
            convert();
        }
    };

    private Button.OnClickListener blistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!(etValue.getText().toString().length() == 15)) {
                int s = etValue.getSelectionStart();

                if (view.getId() == R.id.btA)
                    etValue.setText(appendIndex(etValue.getText().toString(), 'A', s));
                if (view.getId() == R.id.btB)
                    etValue.setText(appendIndex(etValue.getText().toString(), 'B', s));
                if (view.getId() == R.id.btC)
                    etValue.setText(appendIndex(etValue.getText().toString(), 'C', s));
                if (view.getId() == R.id.btD)
                    etValue.setText(appendIndex(etValue.getText().toString(), 'D', s));
                if (view.getId() == R.id.btE)
                    etValue.setText(appendIndex(etValue.getText().toString(), 'E', s));
                if (view.getId() == R.id.btF)
                    etValue.setText(appendIndex(etValue.getText().toString(), 'F', s));

                etValue.setSelection(s + 1);
            }
            else
                Toast.makeText(getContext(), R.string.msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_number_system, container, false);

        crKeyboard = (CardView) rootView.findViewById(R.id.crKeyboard);

        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbBinary1 = (RadioButton) rootView.findViewById(R.id.rbBinary1);
        rbBinary2 = (RadioButton) rootView.findViewById(R.id.rbBinary2);
        rbDecimal1 = (RadioButton) rootView.findViewById(R.id.rbDecimal1);
        rbDecimal2 = (RadioButton) rootView.findViewById(R.id.rbDecimal2);
        rbOctal1 = (RadioButton) rootView.findViewById(R.id.rbOctal1);
        rbOctal2 = (RadioButton) rootView.findViewById(R.id.rbOctal2);
        rbHex1 = (RadioButton) rootView.findViewById(R.id.rbHex1);
        rbHex2 = (RadioButton) rootView.findViewById(R.id.rbHex2);

        etValue = (EditText) rootView.findViewById(R.id.etValue);
        etResult = (EditText) rootView.findViewById(R.id.etResult);

        tvFromVal = (TextView) rootView.findViewById(R.id.tvFromVal);
        tvToRes = (TextView) rootView.findViewById(R.id.tvToRes);

        btA = (Button) rootView.findViewById(R.id.btA);
        btB = (Button) rootView.findViewById(R.id.btB);
        btC = (Button) rootView.findViewById(R.id.btC);
        btD = (Button) rootView.findViewById(R.id.btD);
        btE = (Button) rootView.findViewById(R.id.btE);
        btF = (Button) rootView.findViewById(R.id.btF);

        etValue.addTextChangedListener(mTextWatcher);
        rgFrom.setOnCheckedChangeListener(rglistener);
        rgTo.setOnCheckedChangeListener(rglistener);
        btA.setOnClickListener(blistener);
        btB.setOnClickListener(blistener);
        btC.setOnClickListener(blistener);
        btD.setOnClickListener(blistener);
        btE.setOnClickListener(blistener);
        btF.setOnClickListener(blistener);

        return rootView;
    }

    private void convert() {

        NumberSystem numberSystem = new NumberSystem(getContext());

        if (etValue.getText().toString().equals("")) {
            etResult.setText(R.string.result);
            return;
        }
        int from = from();
        int to = to();
        if (from == 10) {
            etResult.setText(numberSystem.decimalTo(etValue.getText().toString(), to));
            return;
        }
        if (to == 10) {
            etResult.setText(numberSystem.toDecimal(etValue.getText().toString(), from));
            return;
        }
        etResult.setText(numberSystem.fromTo(etValue.getText().toString(), from, to));
    }

    private int from() {
        if (rbBinary1.isChecked())
            return 2;
        if (rbDecimal1.isChecked())
            return 10;
        if (rbOctal1.isChecked())
            return 8;
        if (rbHex1.isChecked())
            return 16;
        return 0;
    }

    private int to() {
        if (rbBinary2.isChecked())
            return 2;
        if (rbDecimal2.isChecked())
            return 10;
        if (rbOctal2.isChecked())
            return 8;
        if (rbHex2.isChecked())
            return 16;
        return 0;
    }

    public void setUnit(RadioGroup group, int checkedId) {
        TextView textView;
        if (group.getId() == rgFrom.getId())
            textView = tvFromVal;
        else
            textView = tvToRes;
        textView.setText(((RadioButton) group.findViewById(checkedId)).getText());
    }

    public String appendIndex(String string, char character, int index) {
        if (index == string.length())
            return string + character;
        char template = string.charAt(index);
        char chars[] = string.toCharArray();
        chars[index] = character;
        return appendIndex(String.valueOf(chars), template, index + 1);
    }
}
