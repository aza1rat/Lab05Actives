package com.example.lab05_activeskashitsin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //Кашицын,393
    EditText ValueTxt; CheckBox CB1; CheckBox CB2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ValueTxt = findViewById(R.id.input_value);
        CB1 = findViewById(R.id.switcher1_f);
        CB2 = findViewById(R.id.switcher1_s);
    }
    @Override
    protected void onActivityResult(int requestC, int resultC, @Nullable Intent data) {
        if (requestC == 555)
        {
            if (data != null)
            {
                String s = data.getStringExtra("check2txt");
                CB1.setChecked(data.getBooleanExtra("check2cb1", false));
                CB2.setChecked(data.getBooleanExtra("check2cb2", false));
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
                ValueTxt.setText(s);
            }
        }
        super.onActivityResult(requestC, resultC, data);
    }

    public void Exit(View v)//Кашицын,393
    {
        AlertDialog dlg = makeDialog("Вы действительно хотите выйти?");
        dlg.setView(dialogButExit(dlg));
        dlg.show();
    }

    public void ToNextWindow (View v)
    {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("checktxt", ValueTxt.getText().toString());
        intent.putExtra("checkcb1", CB1.isChecked());
        intent.putExtra("checkcb2", CB2.isChecked());
        startActivityForResult(intent, 555);
    }

    AlertDialog makeDialog(String str)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dlg = builder.create();
        dlg.setIcon(R.drawable.exiticon);
        dlg.setTitle(str);
        return  dlg;
    }

    public void OpenDialog(View v)//Кашицын,393
    {
        EditText editText; Button button1; Button button2;
        Switch sw1; Switch sw2;
        AlertDialog dlg = makeDialog("Переход");

        LinearLayout containerRoot = createLL();
        LinearLayout containerText = createLL();
        LinearLayout containerSwitches = createLL();
        LinearLayout containerButton = createLL();

        editText = addText();
        containerText.addView(editText);

        sw1 = new Switch(this);
        sw2 = new Switch(this);
        containerSwitches.addView(sw1);
        containerSwitches.addView(sw2);

        button1 = addButton("Ок");
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ValueTxt.setText(editText.getText().toString());
                CB1.setChecked(sw1.isChecked());
                CB2.setChecked(sw2.isChecked());
                dlg.cancel();
            }
        });
        containerButton.addView(button1);
        button2 = addButton("Отмена");
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlg.cancel();
            }
        });
        containerButton.addView(button2);
        containerRoot.addView(containerText);
        containerRoot.addView(containerButton);
        containerRoot.addView(containerSwitches);

        dlg.setView(containerRoot);
        dlg.show();

    }

    LinearLayout dialogButExit(AlertDialog dlg)//Кашицын,393
    {
        LinearLayout linearLayout = createLL();
        Button button = addButton("Да");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button button2 = addButton("Отмена");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg.cancel();
            }
        });
        linearLayout.addView(button);
        linearLayout.addView(button2);
        return linearLayout;
    }

    Button addButton(String str)//Кашицын,393
    {
        Button button = new Button(this);
        button.setText(str);
        //button.setWidth(470);
        return button;
    }

    LinearLayout createLL()
    {
        LinearLayout linearLayout = new LinearLayout(getBaseContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        return linearLayout;
    }

    EditText addText()
    {
        EditText editText= new EditText(this);
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        return editText;
    }
}