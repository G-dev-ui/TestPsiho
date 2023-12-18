package com.example.testpsiho;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormaRegistr extends AppCompatActivity {
    String selectedTopic;

   public  static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forma_registr);
        final Button otpravitBtn = findViewById(R.id.otpravitBtn);
        final EditText editText1 = findViewById(R.id.editText1);
        final EditText editText2 = findViewById(R.id.editText2);
        final EditText editText3 = findViewById(R.id.editText3);
        final EditText editText4 = findViewById(R.id.editText4);
        final EditText editText5 = findViewById(R.id.editText5);
        editText1.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        editText2.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        editText3.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(20)});
        editText4.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)}); // Дата рождения в формате ДД.ММ.ГГГГ
        editText5.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)}); // Класс, например, 10

        Intent intent = getIntent();
        selectedTopic = intent.getStringExtra("selectedTopic");

        otpravitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText1.getText().toString().isEmpty() || editText2.getText().toString().isEmpty()|| editText3.getText().toString().isEmpty() || editText4.getText().toString().isEmpty() || editText5.getText().toString().isEmpty()){
                    Toast.makeText(FormaRegistr.this, "Введите данные", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(FormaRegistr.this, QuizActivity.class);
                    intent.putExtra("editText1", editText1.getText().toString());
                    intent.putExtra("editText2", editText2.getText().toString());
                    intent.putExtra("editText3", editText3.getText().toString());
                    intent.putExtra("editText4", editText4.getText().toString());
                    intent.putExtra("editText5", editText5.getText().toString());
                    intent.putExtra("selectedTopic", selectedTopic);
                    startActivity(intent);
                    finish();
                }

            }
        });


    }



    @Override
    public void onBackPressed (){
        startActivities(new Intent[]{new Intent(FormaRegistr.this, MainActivity.class)});
        finish();
    }

}