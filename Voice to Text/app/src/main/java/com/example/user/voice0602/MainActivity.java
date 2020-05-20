package com.example.user.voice0602;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech tts;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    public String s1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tts = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener(){
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.TRADITIONAL_CHINESE);
                    // 如果該語言資料不見了或沒有支援則無法使用
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                }
            }
        });
        button1 = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button1.setOnClickListener(new Button.OnClickListener()
        {
               public void onClick(View v)
               {
                    //使用RecognizerIntent.ACTION_RECOGNIZE_SPEECH
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    //設定辨識語言(這邊設定的是繁體中文)
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-TW");
                     //設定語音辨識視窗的內容
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening...");
                    startActivityForResult(intent, 1);
                }
        }
        );
        button2.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {// 語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                tts.setPitch((float) 0.5);
                // 速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
                tts.setSpeechRate((float) 0.5);
                 // 設定要說的內容文字
                tts.speak(s1, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
        );
        button3.setOnClickListener(new Button.OnClickListener()
                                   {
                                       public void onClick(View v)
                                       {// 語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                                           tts.setPitch((float) 0.1);
                                           // 速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
                                           tts.setSpeechRate((float) 0.5);
                                           // 設定要說的內容文字
                                           tts.speak(s1, TextToSpeech.QUEUE_FLUSH, null);
                                       }
                                   }
        );
        button4.setOnClickListener(new Button.OnClickListener()
                                   {
                                       public void onClick(View v)
                                       {// 語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                                           tts.setPitch((float) 1.5);
                                           // 速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
                                           tts.setSpeechRate((float) 0.5);
                                           // 設定要說的內容文字
                                           tts.speak(s1, TextToSpeech.QUEUE_FLUSH, null);
                                       }
                                   }
        );
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        //用來儲存最後的辨識結果
        String firstMatch;
        if (requestCode == 1 && resultCode == RESULT_OK) {
            //取出多個辨識結果並儲存在String的ArrayList中
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            firstMatch = (String) result.get(0);
            s1 = firstMatch;
        } else {
            firstMatch = "無法辨識";
        }
        //開啟對話方塊
        //set.title:設定標題
        //setMessage:設定顯示訊息 這裡會顯示辨識的結果
        new AlertDialog.Builder(MainActivity.this).setTitle("語音辨識結果")
                .setIcon(android.R.drawable.ic_menu_search)
                .setMessage(firstMatch.toString())
                .setPositiveButton("OK", null).show();
    }

/*
    private TextToSpeech.OnInitListener ttsInitListener = new TextToSpeech.OnInitListener() {
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = tts.setLanguage(Locale.US);
                // 如果該語言資料不見了或沒有支援則無法使用
                if (result == TextToSpeech.LANG_MISSING_DATA
                        || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "This Language is not supported");
                } else {
                // 語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
                    tts.setPitch((float) 0.5);
                // 速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
                    tts.setSpeechRate((float) 0.5);
                // 設定要說的內容文字
                    tts.speak("hello world! ", TextToSpeech.QUEUE_FLUSH, null);
                }
            } else {
                Toast.makeText(MainActivity.this, "Initialization Failed!",
                        Toast.LENGTH_LONG).show();
            }
        }
    };*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

    }
}
