package com.fausto_c.prototype_to_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fausto_c.prototype_to_sql.model.DbHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //variables
    private Button buttonCreate,buttonOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCreate = findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(this);
        buttonOptions = findViewById(R.id.bt_options);
        buttonOptions.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.buttonCreate:
                DbHelper dbHelper = new DbHelper(getApplicationContext());
                SQLiteDatabase db = dbHelper.getWritableDatabase();//tabla de datos de escritura

                Toast.makeText(this, db!=null?"BASE CREADA":"ERROR AL CREAR LA BASE", Toast.LENGTH_LONG).show();
                break;
            case R.id.bt_options:
                Intent intent = new Intent(this,CrudActivity.class);
                startActivity(intent);
                break;
        }

    }


}