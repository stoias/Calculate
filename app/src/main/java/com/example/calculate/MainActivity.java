package com.example.calculate;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView resultText;
    Button button;

    View.OnClickListener buttonLister = new View.OnClickListener(){
        public void onClick(View view){
            resultText.setText(resultText.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textview);
        resultText = (TextView)findViewById(R.id.resulttext);


        findViewById(R.id.button).setOnClickListener(buttonListener);
        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);
    }

    View.OnClickListener buttonNumberListener = new View.OnClickListener(){
        public void onClick(View view){
            Button button = (Button) view;

            textView.append(button.getText());
            if (isOperatorKeyPushed == true) {
                resultText.setText(button.getText());
            } else {
                resultText.append(button.getText());
            }
            isOperatorKeyPushed = false;

        }
    };

    int recentOperator = R.id.button_equal;
    BigDecimal result;                    // 計算結果
    boolean isOperatorKeyPushed;    // 演算ボタンの記憶

    View.OnClickListener buttonOperatorListener = new View.OnClickListener(){
        public void onClick(View view){
            Button operatorButton = (Button) view;

            BigDecimal value = new BigDecimal(resultText.getText().toString());
            textView.append(operatorButton.getText());

            if (recentOperator == R.id.button_equal) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);
                resultText.setText(String.valueOf(result));
            }

            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            resultText.setText(String.valueOf(result));
            isOperatorKeyPushed = true;

        }

        BigDecimal calc(int operator,BigDecimal value1, BigDecimal value2){
            switch(operator){
                case R.id.button_add:
                    return value1.add(value2);
                case  R.id.button_subtract:
                    return value1.subtract(value2);
                case  R.id.button_multiply:
                    return value1.multiply(value2);
                case R.id.button_divide:
                    return value1.divide(value2,3, BigDecimal.ROUND_HALF_UP);
                default:
                    return value1;
            }
        }
    };

    View.OnClickListener buttonListener = new View.OnClickListener(){
        public void onClick(View view){
            recentOperator = R.id.button_equal;
            result = new BigDecimal(0);
            isOperatorKeyPushed = false;

            textView.setText("");
            resultText.setText("");
        }
    };

}
