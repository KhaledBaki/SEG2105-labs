package com.example.lab3simplecalculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private enum Operator {none, add, minus, multiply, divide, eq}

    private double data1 = 0, data2 = 0;

    private Operator optr = Operator.none;

    private boolean requiresCleaning = false;

    private boolean hasDot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumericalButton(View view) {

        // Gets the Id of the button that we pressed
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);

        // If the previous press was =, start a fresh expression
        if (optr == Operator.eq) {
            optr = Operator.none;
            curText.setText("");
        }

        if (requiresCleaning) {
            requiresCleaning = false;
            curText.setText("");
        }

        if (pressID == R.id.button00){
            curText.setText(curText.getText() + "0");
        }

        else if (pressID == R.id.button01){
            curText.setText(curText.getText() + "1");
        }

        else if (pressID == R.id.button02){
            curText.setText(curText.getText() + "2");
        }

        else if (pressID == R.id.button03){
            curText.setText(curText.getText() + "3");
        }

        else if (pressID == R.id.button04){
            curText.setText(curText.getText() + "4");
        }
        else if (pressID == R.id.button05){
            curText.setText(curText.getText() + "5");
        }

        else if (pressID == R.id.button06){
            curText.setText(curText.getText() + "6");
        }

        else if (pressID == R.id.button07){
            curText.setText(curText.getText() + "7");
        }

        else if (pressID == R.id.button08){
            curText.setText(curText.getText() + "8");
        }

        else if (pressID == R.id.button09){
            curText.setText(curText.getText() + "9");
        }

        else if (pressID == R.id.buttonDot){
            if (!hasDot){
                curText.setText(curText.getText() + ".");
                hasDot = true;
            }
        }

        else {
            curText.setText("ERROR");
        }
    }

    public void onClickFunctionButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);

        // CE always clears everything
        if (pressID == R.id.buttonCE) {
            optr = Operator.none; curText.setText("");
            data1 = 0; data2 = 0; requiresCleaning = false;
            return;
        }

        String dataText = curText.getText().toString();
        double numberVal = dataText.length() > 0 ? Double.parseDouble(dataText) : 0;

        if (optr == Operator.none) {
            data1 = numberVal; requiresCleaning = true;

            if (pressID == R.id.buttonAdd){
                optr = Operator.add;
            }

            else if (pressID == R.id.buttonSub){
                optr = Operator.minus;
            }

            else if (pressID == R.id.buttonMult){
                optr = Operator.multiply;
            }

            else if (pressID == R.id.buttonDiv){
                optr = Operator.divide;
            }
        }

        else {
            data2 = numberVal;
            double result = 0;
            switch (optr) {
                case add: result = data1 + data2; break;
                case minus: result = data1 - data2; break;
                case multiply: result = data1 * data2; break;
                case divide: result = data1 / data2; break;
            }
            data1 = result; optr = Operator.none;
            curText.setText(String.valueOf(result));
        }
    }
}