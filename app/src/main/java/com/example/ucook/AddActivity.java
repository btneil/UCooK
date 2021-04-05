package com.example.ucook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText titre_rct, diff_rct, ing_rct, tmps_rct, instructions_rct;
    Button add_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        titre_rct = findViewById(R.id.titre_rct);
        diff_rct = findViewById(R.id.diff_rct);
        ing_rct = findViewById(R.id.ing_rct);
        tmps_rct = findViewById(R.id.tmps_rct);
        instructions_rct = findViewById(R.id.instructions_rct);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDataBase myDB = new MyDataBase(AddActivity.this);
                myDB.ajouter_rct(titre_rct.getText().toString().trim(),
                        diff_rct.getText().toString().trim(),
                        ing_rct.getText().toString().trim(),
                        Integer.valueOf(tmps_rct.getText().toString().trim()),
                        instructions_rct.getText().toString().trim()); //on converti chaque champs pour qu'ils correspondent à ceux attendus
            }
        });
    }
}