package com.fausto_c.prototype_to_sql;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fausto_c.prototype_to_sql.model.PersonaDao;

public class SaveMethod_Fragment extends Fragment {

    private Button btsave;
    private EditText etNombre,etTelefono,etCorreo,etEdad;

    public SaveMethod_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_save_method_, container, false);

        //enlazar elementos visuales
        btsave = view.findViewById(R.id.bt_Save);
        etNombre = view.findViewById(R.id.et_Nombre);
        etTelefono = view.findViewById(R.id.et_Phone);
        etCorreo = view.findViewById(R.id.et_Correo);
        etEdad = view.findViewById(R.id.et_Edad);

        btsave.setOnClickListener(l -> onClickSave());

        return view;
    }

    private void onClickSave(){

        String nombre = etNombre.getText().toString();
        String telefono = etTelefono.getText().toString();
        String correo = etCorreo.getText().toString();
        int edad = Integer.parseInt(etEdad.getText().toString());

        PersonaDao modelo_persona = new PersonaDao(nombre,telefono,correo,edad);

        modelo_persona.guardar(this.getContext());
        Toast.makeText(this.getContext(), "PERSONA CREADA", Toast.LENGTH_SHORT).show();
        cleanFields();

    }

    public void cleanFields(){

        etNombre.setText("");
        etTelefono.setText("");
        etCorreo.setText("");
        etEdad.setText("");
    }
}