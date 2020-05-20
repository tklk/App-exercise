package com.example.user.intentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Layout2Activity extends AppCompatActivity {
    private Intent intent;
    private Bundle bundle;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);

        intent = this.getIntent();
        bundle = intent.getExtras();
        et = (EditText)findViewById(R.id.p2_et);
        et.setText(bundle.getString("inputs2"));
        Button bt = (Button)findViewById(R.id.p2_bt);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                bundle.putString("outputs2", et.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                Layout2Activity.this.finish();
            }
        });
    }
}
