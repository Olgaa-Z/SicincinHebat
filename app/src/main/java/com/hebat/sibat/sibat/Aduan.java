package com.hebat.sibat.sibat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.hebat.sibat.sibat.ui.ui.Detailaduan;

public class Aduan extends AppCompatActivity {

    Button buttonaduan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aduan);

        buttonaduan = findViewById(R.id.buttonaduan);

        buttonaduan.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Aduan.this, Detailaduan.class);
                startActivity(intent);
            }
        });
    }


}
