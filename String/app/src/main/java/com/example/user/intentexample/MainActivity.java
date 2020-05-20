package com.example.user.intentexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String s2="";
    String s3="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b2 = (Button)findViewById(R.id.main_bt2);
        Button b3 = (Button)findViewById(R.id.main_bt3);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, Layout2Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("inputs2", s2);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Layout3Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("inputs3", s3);
                intent.putExtras(bundle);
                startActivityForResult(intent,3);
            }
        });
        TextView tv1 = (TextView)findViewById(R.id.main_tv1);
        TextView tv2 = (TextView)findViewById(R.id.main_tv2);
        tv1.setText("S2:"+s2);
        tv2.setText("S3:" + s3);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3 && resultCode==RESULT_OK)
        {
            s3 = data.getExtras().getString("outputs3");
            TextView tv2 = (TextView)findViewById(R.id.main_tv2);
            tv2.setText("S3:"+s3);
        }
        if(requestCode==2 && resultCode==RESULT_OK)
        {
            s2 = data.getExtras().getString("outputs2");
            TextView tv1 = (TextView)findViewById(R.id.main_tv1);
            tv1.setText("S2:"+s2);

            Intent intent = new Intent();
            intent.setClass(MainActivity.this, Layout3Activity.class);
            Bundle bundle = new Bundle();
            bundle.putString("inputs3", s3);
            intent.putExtras(bundle);
            startActivityForResult(intent, 3);
        }
    }


}
