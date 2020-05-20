package com.example.user.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button BT_calculate;
    EditText TF_height;
    EditText TF_weight;
    TextView TV_result;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BT_calculate = (Button)findViewById(R.id.BT_calculate);
        TF_height = (EditText)findViewById(R.id.TF_height);
        TF_weight = (EditText)findViewById(R.id.TF_weight);
        TV_result = (TextView)findViewById(R.id.TV_result);
        BT_calculate.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        double weight = Integer.parseInt(TF_weight.getText().toString());
                        double height = Integer.parseInt(TF_height.getText().toString());
                        TV_result.setText(String.valueOf((weight / ((height / 100.0) * (height / 100.0)))));
                    }
                }
        );
    }
}
