package com.example.bmi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etHeight, etWeight;
    private static final int BMI_REQUEST_CODE = 100;
    private int[] photoIds = {
            R.drawable.p01, R.drawable.p02, R.drawable.p03,
            R.drawable.p04, R.drawable.p05, R.drawable.p06
    };
    private ImageView ivResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.etHeight);
        etWeight = findViewById(R.id.etWeight);
        ivResult = findViewById(R.id.ivResult);

    }

    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btnResult:
                if(etHeight.length() == 0 || etWeight.length() == 0){
                    Toast.makeText(MainActivity.this, "輸入身高或體重!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double height = Double.parseDouble(etHeight.getText().toString());
                double weight = Double.parseDouble(etWeight.getText().toString());

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("height", height);
                bundle.putDouble("weight", weight);
                intent.putExtras(bundle);

                // 只傳送資料
                //startActivity(intent);

                // 傳送資料並準備接受回傳資料
                startActivityForResult(intent, BMI_REQUEST_CODE);

                break;
            case R.id.btnCls:
                etHeight.setText("");
                etWeight.setText("");
                etHeight.requestFocus();
                ivResult.setVisibility(View.GONE);

                break;
        }
    }

    // 覆寫方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == BMI_REQUEST_CODE){
            int photoId = data.getIntExtra("photoId", 4);
            ivResult.setImageResource(photoIds[photoId]);
            // 讓 ivResult 可以顯示
            ivResult.setVisibility(View.VISIBLE);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}