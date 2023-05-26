package com.example.wagecalc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.wagecalc.R;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton;
    private EditText hoursEditText;
    private EditText rateEditText;
    private TextView paymentTextView;
    private TextView taxTextView;
    private TextView totalPaymentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView (R.layout.activity_main);

        calculateButton = findViewById(R.id.calculateButton);
        hoursEditText = findViewById(R.id.hoursEditText);
        rateEditText = findViewById(R.id.rateEditText);
        paymentTextView = findViewById(R.id.paymentTextView);
        taxTextView = findViewById(R.id.taxTextView);
        totalPaymentTextView = findViewById(R.id.totalPaymentTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePayment();
            }
        });
    }

    @SuppressLint("StringFormatMatches")
    private void calculatePayment() {
        double hours = Double.parseDouble(hoursEditText.getText().toString());
        double rate = Double.parseDouble(rateEditText.getText().toString());
        double payment = hours * rate;
        double tax = calculateTaxAmount(payment);
        double totalPayment = payment - tax;

        paymentTextView.setText(getString(R.string.payment_result, payment));
        taxTextView.setText(getString(R.string.tax_result, tax));
        totalPaymentTextView.setText(getString(R.string.total_payment_result, totalPayment));
    }

    private double calculateTaxAmount(double income) {
        if (income <= 250000) {
            return 0.0;
        } else if (income <= 400000) {
            return 0.15 * (income - 250000);
        } else if (income <= 800000) {
            return 0.20 * (income - 400000) + 22500;
        } else if (income <= 2000000) {
            return 0.25 * (income - 800000) + 102500;
        } else if (income <= 8000000) {
            return 0.30 * (income - 2000000) + 402500;
        } else {
            return 0.35 * (income - 8000000) + 2420500;
        }
    }
}