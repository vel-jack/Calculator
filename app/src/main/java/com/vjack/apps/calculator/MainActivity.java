package com.vjack.apps.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private String buf="",n1="",n2="",total="",sign,content="";
    private TextView contentText;
    private TextView resultText;
    private Boolean isNumClicked=false,isSignClicked=false,isEqualClicked=false;
    private Button dot;
    private int lC = 0;
    private static DecimalFormat df = new DecimalFormat(".#############");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentText = (TextView) findViewById(R.id.contentText);
        resultText  = (TextView) findViewById(R.id.resultText);
        dot = (Button) findViewById(R.id.iamdot);
    }
    public void numberClick(View view){
        Button button = (Button) view;
        buf = button.getText().toString();
        if(lC<8) {
            if (buf.equals(".")) {
                dot.setEnabled(false);
            }
            n1 += buf;
            resultText.setText(n1);
            isNumClicked = true;
            isEqualClicked = false;
            lC++;
        }
    }
    public void signClick(View view){
        Button button = (Button) view;
        if(isEqualClicked == true){
            n2 = total;
            isSignClicked = false;
        }
        else if(isSignClicked == false && isNumClicked == false){
            n2 = "0";
        }
        else if(isSignClicked == false && isNumClicked == true){
            n2 = n1;
            isNumClicked = false;
        }
        else if(isSignClicked == true && isNumClicked == true){
            content += sign;
            calculate(n2,n1,sign);
            n2 = total;
            isNumClicked = false;
        }
        content += n1;
        isSignClicked = true;
        sign = button.getText().toString();
        contentText.setText(content+" "+sign);
        n1 = "";
        dot.setEnabled(true);
        lC = 0 ;
        if(content.length() > 30){
            content = "Result";
        }
    }

    private void calculate(String x, String y, String mySign) {
        Double a,b,result;
        a = Double.parseDouble(x);
        b = Double.parseDouble(y);
        switch (mySign){
            case "+":
                result = a + b;
                if(result - Math.floor(result) == 0){
                    total = String.valueOf(result.intValue());
                }else {
                    total = String.valueOf(df.format(result));
                }
                if(total.length() > 8){
                    resultText.setTextSize(40);
                }else{
                    resultText.setTextSize(70);
                }
                resultText.setText(total);
                break;

            case "-":
                result = a - b;
                if(result - Math.floor(result) == 0){
                    total = String.valueOf(result.intValue());
                }else {
                    total = String.valueOf(df.format(result));
                }
                if(total.length() > 8){
                    resultText.setTextSize(40);
                }else{
                    resultText.setTextSize(70);
                }
                resultText.setText(total);
                break;

            case "/":
                result = a / b;
                if(result - Math.floor(result) == 0){
                    total = String.valueOf(result.intValue());
                }else {
                    total = String.valueOf(df.format(result));
                }
                if(total.length() > 8){
                    resultText.setTextSize(40);
                }else{
                    resultText.setTextSize(70);
                }
                resultText.setText(total);
                if(b == 0){
                    emptyAll();
                }
                break;

            case "x":
                result = a * b;
                if(result - Math.floor(result) == 0){
                    total = String.valueOf(result.intValue());
                }else {
                    total = String.valueOf(df.format(result));
                }
                if(total.length() > 8){
                    resultText.setTextSize(40);
                }else{
                    resultText.setTextSize(70);
                }
                resultText.setText(total);
                break;
        }
    }

    private void emptyAll() {
        n1 = "";
        n2 = "";
        contentText.setText("");
        sign = "";
        content = "";
        total = "";
        isNumClicked = false;
        isSignClicked = false;
        lC = 0 ;
    }

    public void resultClick(View view){
        if(!n1.equals("") && !n2.equals("") && !sign.equals("")) {
            Toast.makeText(getApplicationContext(), n2 + " " + sign + " " + n1, Toast.LENGTH_LONG).show();
            calculate(n2, n1, sign);
            isEqualClicked = true;
            String temp = total;
            emptyAll();
            total = temp;
            dot.setEnabled(true);
            lC = 0 ;
        }
    }

    public void clearClick(View view) {
        emptyAll();
        resultText.setText("0");
    }
}
