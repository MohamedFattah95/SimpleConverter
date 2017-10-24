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

import com.example.mohamed.simpleconverter.Converter.Volume;
import com.example.mohamed.simpleconverter.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class VolumeActivityFragment extends Fragment {
    public RadioGroup rgFrom, rgTo;
    RadioButton rbLitre1, rbCubicCM1, rbCubicM1, rbCubicI1, rbCubicF1, rbCubicY1;
    RadioButton rbLitre2, rbCubicCM2, rbCubicM2, rbCubicI2, rbCubicF2, rbCubicY2;
    EditText etValue, etResult;
    TextView tvFromVal, tvToRes;

    public VolumeActivityFragment() {
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
        View rootView = inflater.inflate(R.layout.fragment_volume, container, false);
        rgFrom = (RadioGroup) rootView.findViewById(R.id.rgfrom);
        rgTo = (RadioGroup) rootView.findViewById(R.id.rgTo);

        rbLitre1 = (RadioButton) rootView.findViewById(R.id.rbLitre1);
        rbLitre2 = (RadioButton) rootView.findViewById(R.id.rbLitre2);
        rbCubicCM1 = (RadioButton) rootView.findViewById(R.id.rbCCm1);
        rbCubicCM2 = (RadioButton) rootView.findViewById(R.id.rbCCm2);
        rbCubicM1 = (RadioButton) rootView.findViewById(R.id.rbCM1);
        rbCubicM2 = (RadioButton) rootView.findViewById(R.id.rbCM2);
        rbCubicI1 = (RadioButton) rootView.findViewById(R.id.rbCI1);
        rbCubicI2 = (RadioButton) rootView.findViewById(R.id.rbCI2);
        rbCubicF1 = (RadioButton) rootView.findViewById(R.id.rbCF1);
        rbCubicF2 = (RadioButton) rootView.findViewById(R.id.rbCF2);
        rbCubicY1 = (RadioButton) rootView.findViewById(R.id.rbCY1);
        rbCubicY2 = (RadioButton) rootView.findViewById(R.id.rbCY2);

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
                    value = Volume.litreToCubicCM(value);
                else if (from == 2)
                    value = Volume.cubicCMToCubicM(value);
                else if (from == 3)
                    value = Volume.cubicMToCubicI(value);
                else if (from == 4)
                    value = Volume.cubicIToCubicF(value);
                else if (from == 5)
                    value = Volume.cubicFToCubicY(value);
                from++;
            } else {
                if (from == 6)
                    value = Volume.cubicYToCubicF(value);
                else if (from == 5)
                    value = Volume.cubicFToCubicI(value);
                else if (from == 4)
                    value = Volume.cubicIToCubicM(value);
                else if (from == 3)
                    value = Volume.cubicMToCubicCM(value);
                else if (from == 2)
                    value = Volume.cubicCMToLitre(value);
                from--;
            }
        }
        etResult.setText(String.valueOf(value));
    }

    private int from() {
        if (rbLitre1.isChecked())
            return 1;
        if (rbCubicCM1.isChecked())
            return 2;
        if (rbCubicM1.isChecked())
            return 3;
        if (rbCubicI1.isChecked())
            return 4;
        if (rbCubicF1.isChecked())
            return 5;
        if (rbCubicY1.isChecked())
            return 6;
        return 0;
    }

    private int to() {
        if (rbLitre2.isChecked())
            return 1;
        if (rbCubicCM2.isChecked())
            return 2;
        if (rbCubicM2.isChecked())
            return 3;
        if (rbCubicI2.isChecked())
            return 4;
        if (rbCubicF2.isChecked())
            return 5;
        if (rbCubicY2.isChecked())
            return 6;
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
