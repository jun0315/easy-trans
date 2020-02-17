package com.example.ancienttranslation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.baidu.translate.demo.TranThread;

public class MainActivity extends AppCompatActivity {

    private TextView output;
    private EditText input;
    private Spinner fromSpan;
    private Spinner toSpan;

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BlidView();
        CheckPermission();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void CheckPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    public void trans(View view) throws InterruptedException {
        String inputContent = input.getText().toString();
        String fromLanguage = fromSpan.getSelectedItem().toString();
        String toLanguage = toSpan.getSelectedItem().toString();
        TranThread tranThread = new TranThread(inputContent, fromLanguage, toLanguage);
        tranThread.start();
        tranThread.join();
        String result = tranThread.getOutput();
        output.setText(result);
    }

    private void BlidView() {
        output = findViewById(R.id.output);
        input = findViewById(R.id.input);
        fromSpan = findViewById(R.id.fromLanguage);
        toSpan = findViewById(R.id.toLanguage);
    }


}


