 package com.example.pm012024p1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;
import Models.Personas;

 public class ActivityList extends AppCompatActivity {

     SQLiteConexion conexion;
     ListView listpersonas;

     ArrayList<Personas> lista;
     ArrayList<String> arreglo;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_list);

         conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
         listpersonas = (ListView) findViewById(R.id.listpersonas);

         ObtenerInfo();

         listpersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Personas personase = lista.get(position);
                 Intent intent = new Intent(ActivityList.this, MainActivity.class);

                 intent.putExtra("id", personase.getId().toString());
                 intent.putExtra("nombres", personase.getNombres());
                 intent.putExtra("apellidos", personase.getApellidos());
                 intent.putExtra("edad", personase.getEdad());
                 intent.putExtra("correo", personase.getCorreo());
                 intent.putExtra("direccion", personase.getDireccion());
                 startActivity(intent);

             }

         });

         ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arreglo);
         listpersonas.setAdapter(adp);
     }

     private void ObtenerInfo()
     {
         SQLiteDatabase db = conexion.getReadableDatabase();
         Personas person = null;
         lista = new ArrayList<Personas>();

         //Cursor de base de datos para recorrer los datos
         Cursor cursor = db.rawQuery(Transacciones.SelectALLPersonas, null);

         while(cursor.moveToNext())
         {
             person = new Personas();
             person.setId(cursor.getInt(0));
             person.setNombres(cursor.getString(1));
             person.setApellidos(cursor.getString(2));
             person.setEdad(cursor.getInt(3));
             person.setCorreo(cursor.getString(4));
             person.setDireccion(cursor.getString(5));

             lista.add(person);
         }

         cursor.close();

         FillData();
     }


     private void FillData()
     {
         arreglo = new ArrayList<String>();
         for(int i = 0; i < lista.size(); i++)
         {
             arreglo.add(lista.get(i).getId() + " - "+
                     lista.get(i).getNombres() + " - "+
                     lista.get(i).getApellidos()+ " - "+
                     lista.get(i).getEdad()+ " - "+
                     lista.get(i).getDireccion());
         }
     }
 }