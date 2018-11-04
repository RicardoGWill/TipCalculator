package com.ricardogwill.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText billEditText;
    private TextView percentTextView, seekbarPercentTextView, tipResultTextView, totalBillTextView;
    private SeekBar percentSeekBar;
    private Button calculateButton;

    int percentTipInt = 10;
    float enteredBillFloat = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billEditText = findViewById(R.id.bill_editText);
        percentTextView = findViewById(R.id.percent_textView);
        seekbarPercentTextView = findViewById(R.id.seekbarPercentTextView);
        tipResultTextView = findViewById(R.id.tip_result_TextView);
        totalBillTextView = findViewById(R.id.total_bill_textView);
        percentSeekBar = findViewById(R.id.percentSeekBar);
        calculateButton = findViewById(R.id.calculate_button);

        seekbarPercentTextView.setText("15%");
        percentSeekBar.setProgress(5);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                percentTipInt = 10 + ((seekBar.getProgress()));
                seekbarPercentTextView.setText(String.valueOf(percentTipInt + "%"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void calculate() {
        float resultFloat = 0.0f;

        if (!billEditText.getText().toString().equals("")) {
            enteredBillFloat = Float.parseFloat(billEditText.getText().toString());
            resultFloat = enteredBillFloat * percentTipInt;

            DecimalFormat tipDecimalFormat = new DecimalFormat("###,###,###.##");
            DecimalFormat totalDecimalFormat = new DecimalFormat("###,###,###.##");

            tipResultTextView.setText("Tip Amount: $" + (tipDecimalFormat.format(resultFloat/100)));
            totalBillTextView.setText("Total Amount: $" + (totalDecimalFormat.format(enteredBillFloat + resultFloat/100)));
        } else {
            Toast.makeText(this, "Please enter a bill amount.", Toast.LENGTH_SHORT).show();
        }


    }

}
