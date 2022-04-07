package com.fausto_c.prototype_to_sql;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fausto_c.prototype_to_sql.model.Persona;
import com.fausto_c.prototype_to_sql.model.PersonaDao;

import java.util.List;

public class Data_Fragment extends Fragment implements View.OnClickListener {

    private TableLayout tabla;
    private TextView tvId, tvNombres,tvTlf,tvCorreo,tvEdad;

    private EditText etId,etNombre,etTelefono,etCorreo,etEdad;
    private ImageButton btsearch, btdelete ,btupdate;

    //Dao
    PersonaDao personaDao;

    public Data_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_, container, false);

        etId = view.findViewById(R.id.et_Id);
        etNombre = view.findViewById(R.id.et_Nombre);
        etTelefono = view.findViewById(R.id.et_Phone);
        etCorreo = view.findViewById(R.id.et_Correo);
        etEdad = view.findViewById(R.id.et_Edad);

        //Botones
        btsearch = view.findViewById(R.id.btSearch);
        btsearch.setOnClickListener(this);
        btdelete = view.findViewById(R.id.btDelete);
        btdelete.setOnClickListener(this);
        btupdate = view.findViewById(R.id.btUpdate);
        btupdate.setOnClickListener(this);

        tabla = view.findViewById(R.id.tl_Persona);
        fillTable();

        return view;
    }

    public void fillTable(){

        tabla.removeAllViews();/*limpia la tabla para cargar nuevos datos*/
        personaDao = new PersonaDao();

        List<Persona> listaPersonas = personaDao.consultarDatos(this.getContext());

        for (Persona persona:
             listaPersonas) {
           View registry = LayoutInflater.from(this.getContext()).inflate(R.layout.item_table_layout,null,false);
           tvId = registry.findViewById(R.id.tv_id);
           tvNombres = registry.findViewById(R.id.tv_nombres);
           tvTlf = registry.findViewById(R.id.tv_tlf);
           tvCorreo = registry.findViewById(R.id.tv_correo);
           tvEdad = registry.findViewById(R.id.tv_edad);

           tvId.setText(String.valueOf(persona.getId()));
           tvNombres.setText(persona.getNombres());
           tvTlf.setText(persona.getTelefono());
           tvCorreo.setText(persona.getCorreo());
           tvEdad.setText(String.valueOf(persona.getEdad()));

           tabla.addView(registry);
            System.out.println(persona.getId() + persona.getNombres());
        }


    }

    public void search(){

        String id = etId.getText().toString();

        if (!id.isEmpty()){
            personaDao = new PersonaDao();
            Persona personaId = personaDao.findById(this.getContext(),Integer.parseInt(id));
            if (personaId.getNombres()!=null){
                etNombre.setText(personaId.getNombres());
                etTelefono.setText(personaId.getTelefono());
                etCorreo.setText(personaId.getCorreo());
                etEdad.setText(String.valueOf(personaId.getEdad()).equals("0")? "":String.valueOf(personaId.getEdad()));
            }else{
                cleanFields();
                Toast.makeText(this.getContext(),"No existe registro con el ID:" + id,Toast.LENGTH_LONG).show();
            }


        }else{
            cleanFields();
            Toast.makeText(this.getContext(), "Ingrese el Id a buscar", Toast.LENGTH_LONG).show();
        }


    }

    public void delete(){

        String id = etId.getText().toString();

        if (!id.isEmpty()){

            int idNumber = Integer.parseInt(id);
            if (checkId(idNumber)){
                personaDao = new PersonaDao();
                personaDao.eliminar(this.getContext(),idNumber);
                Toast.makeText(this.getContext(),"Persona Eliminada",Toast.LENGTH_LONG).show();
                cleanFields();
                fillTable();
            }
        }else{
            cleanFields();
            Toast.makeText(this.getContext(), "Ingrese el Id de la persona a eliminar", Toast.LENGTH_LONG).show();
        }

    }

    public void update(){

        String id = etId.getText().toString();
        if (!id.isEmpty()){

            int idNumber = Integer.parseInt(id);
            if (checkId(idNumber)){

                String nombre = etNombre.getText().toString();
                String telefono = etTelefono.getText().toString();
                String correo = etCorreo.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());

                personaDao = new PersonaDao(nombre,telefono,correo,edad);
                personaDao.actualizar(this.getContext(),idNumber);
                Toast.makeText(this.getContext(),"Persona Modificada",Toast.LENGTH_LONG).show();
                cleanFields();
                fillTable();
            }


        }else{
            cleanFields();
            Toast.makeText(this.getContext(), "Ingrese el Id de la persona a modificar", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkId(int id){

        Persona persona = personaDao.findById(this.getContext(),id);
        if (persona.getNombres()!=null){
            return true;
        }else{
            cleanFields();
            Toast.makeText(this.getContext(),"No existe registro con el ID:" + id,Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public void cleanFields(){

        etId.setText("");
        etNombre.setText("");
        etTelefono.setText("");
        etCorreo.setText("");
        etEdad.setText("");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btSearch:
                search();
                break;
            case R.id.btDelete:
                delete();
                break;
            case R.id.btUpdate:
                update();
                break;
        }
    }
}