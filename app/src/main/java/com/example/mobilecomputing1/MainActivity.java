package com.example.mobilecomputing1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button0;
    private Button buttonMinus;
    private Button buttonPlus;
    private Button buttonMultiply;
    private Button buttonDivide;
    private Button buttonEquals;
    private Button buttonAC;

    private TextView equationText;

    private Context context = null;

    private TableLayout tableLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }

    /* Initialise layout and button components. */
    private void initControls() {

        tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
        buttonMinus = (Button) findViewById(R.id.buttonMinus);
        buttonPlus = (Button) findViewById(R.id.buttonPlus);
        buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        buttonDivide = (Button) findViewById(R.id.buttonDivide);
        buttonEquals = (Button) findViewById(R.id.buttonEquals);
        buttonAC = (Button) findViewById(R.id.buttonAC);

        equationText = (TextView) findViewById(R.id.equationText);

        if (context == null) {
            context = getApplicationContext();
        }

        /* Must set the on click listener to this activity,
           otherwise the override onClick method will bot be invoked.*/
        tableLayout.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMultiply.setOnClickListener(this);
        buttonDivide.setOnClickListener(this);
        buttonEquals.setOnClickListener(this);
        buttonAC.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            // Get view component id.
            int id = view.getId();
            switch (id) {
                case R.id.button1:
                    equationText.append("1");
                    break;
                case R.id.button2:
                    equationText.append("2");
                    break;
                case R.id.button3:
                    equationText.append("3");
                    break;
                case R.id.button4:
                    equationText.append("4");
                    break;
                case R.id.button5:
                    equationText.append("5");
                    break;
                case R.id.button6:
                    equationText.append("6");
                    break;
                case R.id.button7:
                    equationText.append("7");
                    break;
                case R.id.button8:
                    equationText.append("8");
                    break;
                case R.id.button9:
                    equationText.append("9");
                    break;
                case R.id.button0:
                    equationText.append("0");
                    break;
                case R.id.buttonMinus:
                    if (!canInsertSign()) {
                        equationText.setText(equationText.getText().subSequence(0, equationText.getText().length() - 1));
                    }
                    equationText.append("-");
                    break;
                case R.id.buttonPlus:
                    if (!canInsertSign()) {
                        equationText.setText(equationText.getText().subSequence(0, equationText.getText().length() - 1));
                    }
                    equationText.append("+");
                    break;
                case R.id.buttonMultiply:
                    if (!canInsertSign()) {
                        equationText.setText(equationText.getText().subSequence(0, equationText.getText().length() - 1));
                    }
                    equationText.append("*");
                    break;
                case R.id.buttonDivide:
                    if (!canInsertSign()) {
                        equationText.setText(equationText.getText().subSequence(0, equationText.getText().length() - 1));
                    }
                    equationText.append("/");
                    break;
                case R.id.buttonAC:
                    equationText.setText("");
                    break;
                case R.id.buttonEquals:
                    calculate();
                    break;
            }

        }
    }

    private void calculate() {
        if (!canInsertSign()) {
            equationText.setText(equationText.getText().subSequence(0, equationText.getText().length() - 1));
        }
        Expression e = new Expression(equationText.getText().toString());
        equationText.setText(Double.toString(e.calculate()));
    }

    private boolean canInsertSign() {
        return Character.isDigit(equationText.getText().charAt(equationText.getText().length() - 1));
    }

}
