package com.fausto_c.prototype_to_sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class CrudActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentTransaction transaction;
    Fragment fragmentInicio, fragmentData;
    FragmentManager fragmentManager;
    ImageButton bt_save, bt_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        fragmentInicio = new SaveMethod_Fragment();
        fragmentData = new Data_Fragment();

        bt_save = findViewById(R.id.imgButtonSave);
        bt_data = findViewById(R.id.imageButtonData);


        bt_save.setOnClickListener(this);
        bt_data.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contenedorFragments,fragmentInicio);
        transaction.commit();
    }


    @Override
    public void onClick(View view) {

        transaction = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){

            case R.id.imgButtonSave:
                transaction.replace(R.id.contenedorFragments,fragmentInicio);
                transaction.addToBackStack(null);/*No cierra la app*/
                break;
            case R.id.imageButtonData:
                transaction.replace(R.id.contenedorFragments,fragmentData);
                transaction.addToBackStack(null);/*No cierra la app*/
        }

        transaction.commit();
    }
}