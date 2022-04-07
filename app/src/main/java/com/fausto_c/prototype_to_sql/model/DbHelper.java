package com.fausto_c.prototype_to_sql.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    //constantes para la BD
    private static final String DATABASE_NAME = "personassql.db";
    private static final int DATABASE_VERSION = 3;/*permite crear la BD con los cambios*/
    public static final String TABLA_NAME = "personas";



    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE "+TABLA_NAME+" (" +
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT NOT NULL," +
                "telefono TEXT NOT NULL," +
                "correo TEXT," +
                "edad INTEGER)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sqldrop = "DROP TABLE " +TABLA_NAME;
        sqLiteDatabase.execSQL(sqldrop);

        /*El borrado de las tablas se lo realiza en el desarrollo,
        * cuando esta se defina se puede utilizar ALTER para
        * modificar la tabla*/
        onCreate(sqLiteDatabase);

    }

    /*Método para realizar cambios dentro de la data de la BD*/
    public void noQuery(String nsql){
        this.getWritableDatabase().execSQL(nsql);
    }

    /*Método para consultar información a la BD*/
    public Cursor query (String sql){
        return this.getReadableDatabase().rawQuery(sql,null);
    }
}
