package com.example.pm012024p1;

import static Configuracion.Transacciones.id;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import Configuracion.Transacciones;

public class Activity_Delete extends AppCompatActivity {
    EditText nombres, apellidos, edad, correo, direccion;
    Button btnproceso;

    SearchView busqueda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        nombres = (EditText) findViewById(R.id.txtnombre2);
        apellidos = (EditText) findViewById(R.id.txtapellidos2);
        edad = (EditText) findViewById(R.id.txtedad2);
        correo = (EditText) findViewById(R.id.txtcorreo2);
        direccion = (EditText) findViewById(R.id.txtdireccion2);
        btnproceso = (Button) findViewById(R.id.btneliminar);
        busqueda = (SearchView) findViewById(R.id.busquedaid);

        busqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String Id) {
                buscarRegistros(Integer.parseInt(Id));
                return false;
            }
        });

        btnproceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleted();
            }
                    });
    }
    private void buscarRegistros(int idABuscar) {
        SQLiteDatabase db = getApplicationContext().openOrCreateDatabase("PM012024", Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + Transacciones.TablePersonas + " WHERE " + id + " = ?", new String[]{String.valueOf(idABuscar)});

        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex(Transacciones.nombres));
            @SuppressLint("Range") String apellido = cursor.getString(cursor.getColumnIndex(Transacciones.apellidos));
            @SuppressLint("Range") String edad2 = cursor.getString(cursor.getColumnIndex(Transacciones.edad));
            @SuppressLint("Range") String correo2 = cursor.getString(cursor.getColumnIndex(Transacciones.correo));
            @SuppressLint("Range") String direccion2 = cursor.getString(cursor.getColumnIndex(Transacciones.direccion));

            nombres.setText(nombre);
            apellidos.setText(apellido);
            edad.setText(edad2);
            correo.setText(correo2);
            direccion.setText(direccion2);
        }

        cursor.close();
        db.close();
    }
    private void deleted() {
    }
}