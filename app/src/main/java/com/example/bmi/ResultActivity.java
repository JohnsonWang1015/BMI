package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    private TextView tvHeight, tvWeight, tvBmi, tvResult;
    private double height, weight, bmi;
    private int photoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvHeight = findViewById(R.id.tvHeight);
        tvWeight = findViewById(R.id.tvWeight);
        tvBmi = findViewById(R.id.tvBmi);
        tvResult = findViewById(R.id.tvResult);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        height = bundle.getDouble("height");
        weight = bundle.getDouble("weight");
        getResult();
    }

    private void getResult() {
        Log.i("#### height = ", height + "");
        Log.i("#### weight = ", weight + "");
        tvHeight.setText(String.format("身高: %.2f cm", height));
        tvWeight.setText(String.format("體重: %.2f kg", weight));
        bmi = weight / ((height/100) * (height/100));
        tvBmi.setText(String.format("BMI: %.2f", bmi));

        String result;
        if(bmi >= 35){
            result = "重度肥胖";
            photoId = 0;
        }else if(bmi >= 30){
            result = "中度肥胖";
            photoId = 1;
        }else if(bmi >= 27){
            result = "輕度肥胖";
            photoId = 2;
        }else if(bmi >= 24){
            result = "過重";
            photoId = 3;
        }else if(bmi >= 18.5){
            result = "標準體重";
            photoId = 4;
        }else{
            result = "體重過輕";
            photoId = 5;
        }

        tvResult.setText(result);

    }

    public void onClick(View view){
        Intent intent = new Intent();
        intent.putExtra("photoId", photoId);
        setResult(RESULT_OK, intent);

        finish();
    }
}