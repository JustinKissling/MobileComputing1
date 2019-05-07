package com.example.mobilecomputing1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

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

    private Button buttonComma;
    private Button buttonAC;

    private TextView equationText;
    private Context context = null;
    private TableLayout tableLayout;

    private int commaCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();
    }

    /**
     * This method initialises layout and button components.
     */
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

        buttonComma = (Button) findViewById(R.id.buttonComma);
        buttonAC = (Button) findViewById(R.id.buttonAC);

        equationText = (TextView) findViewById(R.id.equationText);

        if (context == null) {
            context = getApplicationContext();
        }

        // Add onClick listener to all buttons
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
        buttonComma.setOnClickListener(this);
        buttonAC.setOnClickListener(this);

        // Init equation text with '0'
        equationText.setText("0");

    }

    @Override
    public void onClick(View view) {
        if (view != null) {
            // Get view component id.
            int id = view.getId();
            switch (id) {
                case R.id.button1:
                    updateEquationText('1');
                    break;
                case R.id.button2:
                    updateEquationText('2');
                    break;
                case R.id.button3:
                    updateEquationText('3');
                    break;
                case R.id.button4:
                    updateEquationText('4');
                    break;
                case R.id.button5:
                    updateEquationText('5');
                    break;
                case R.id.button6:
                    updateEquationText('6');
                    break;
                case R.id.button7:
                    updateEquationText('7');
                    break;
                case R.id.button8:
                    updateEquationText('8');
                    break;
                case R.id.button9:
                    updateEquationText('9');
                    break;
                case R.id.button0:
                    updateEquationText('0');
                    break;
                case R.id.buttonMinus:
                    updateEquationText('-');
                    break;
                case R.id.buttonPlus:
                    updateEquationText('+');
                    break;
                case R.id.buttonMultiply:
                    updateEquationText('*');
                    break;
                case R.id.buttonDivide:
                    updateEquationText('/');
                    break;
                case R.id.buttonComma:
                    updateEquationText('.');
                    break;
                case R.id.buttonAC:
                    resetCalculator();
                    break;
                case R.id.buttonEquals:
                    calculate();
                    break;
            }

        }
    }

    /**
     * This method update the equation text of the calculator when the user clicks on a button.
     *
     * @param input
     */
    private void updateEquationText(char input) {
        // If input is number
        if (isDigit(input)) {
            // Don't append numbers on '0' and 'NaN'
            if (equationText.getText().toString().equals("0") || equationText.getText().toString().equals("NaN")) {
                equationText.setText(String.valueOf(input));
            } else {
                equationText.append(String.valueOf(input));
            }
        }

        // If input is mathematical symbol
        if (isSymbol(input)) {
            // Can't place two symbols after one other or after 'NaN'
            if (!isSymbol(getLastElementOfEquationText()) && !equationText.getText().toString().equals("NaN")) {
                equationText.append(String.valueOf(input));
                commaCounter = 0;
            } else {
                removeSymbolsAtEquationEnd();
                equationText.append(String.valueOf(input));
            }
        }

        // If input is a comma
        if (isComma(input)) {
            if (isDigit(getLastElementOfEquationText())) {
                if (commaCounter == 0) {
                    equationText.append(String.valueOf(input));
                    commaCounter = 1;
                }
            }
        }

    }

    /**
     * This method calculates the result of the input and displays it.
     */
    private void calculate() {
        removeSymbolsAtEquationEnd();
        Expression e = new Expression(equationText.getText().toString());
        String result = Double.toString(e.calculate());
        // If result is an integer
        if (result.contains(".0")) {
            result = result.substring(0, result.length() - 2);
            commaCounter = 0;
        }

        // Check afterwards if comma exists
        if (result.contains(".")) {
            commaCounter = 1;
        }

        equationText.setText(result);
    }


    /**
     * This method removes mathematical symbols at the end of a equation text.
     */
    private void removeSymbolsAtEquationEnd() {
        if (isSymbol(getLastElementOfEquationText()) ||isComma(getLastElementOfEquationText())) {
            equationText.setText(equationText.getText().subSequence(0, equationText.getText().length() - 1));
        }
    }

    /**
     * This method checks if the users input is a mathematical symbol.
     *
     * @param input
     * @return isSymbol
     */
    private boolean isSymbol(char input) {
        if (input == '+' || input == '-' || input == '*' || input == '/') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method checks if the users input is a digit.
     *
     * @param input
     * @return isDigit
     */
    private boolean isDigit(char input) {
        return Character.isDigit(input);
    }

    /**
     * This method returns the current last element of the equation text field.
     * @return lastElementAsChar
     */
    private char getLastElementOfEquationText() {
        return equationText.getText().charAt(equationText.getText().length() - 1);
    }

    /**
     * This method checks if the users input is a comma.
     *
     * @param input
     * @return isComma
     */
    private boolean isComma(char input) {
        if (input == '.') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method resets the calculator.
     */
    private void resetCalculator() {
        equationText.setText("0");
        commaCounter = 0;
    }
}
