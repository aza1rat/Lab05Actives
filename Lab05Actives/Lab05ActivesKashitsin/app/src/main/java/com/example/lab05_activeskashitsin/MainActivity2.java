package com.example.lab05_activeskashitsin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity2 extends AppCompatActivity {
    EditText ValueTxt; Switch SW1; Switch SW2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ValueTxt = findViewById(R.id.input_value2);
        SW1 = findViewById(R.id.switcher2_f);
        SW2 = findViewById(R.id.switcher2_s);
        Intent intent = getIntent();
        ValueTxt.setText(intent.getStringExtra("checktxt"));
        SW1.setChecked(intent.getBooleanExtra("checkcb1", false));
        SW2.setChecked(intent.getBooleanExtra("checkcb2", false));
    }

    public void Cancel(View v)//Кашицын,393
    {
        setResult(RESULT_CANCELED);
        finish();
    }

    public  void onSave(View v)
    {
        Intent intent = new Intent();
        intent.putExtra("check2txt", ValueTxt.getText().toString());
        intent.putExtra("check2cb1", SW1.isChecked());
        intent.putExtra("check2cb2", SW2.isChecked());
        setResult(RESULT_OK, intent);
        finish();
    }

}