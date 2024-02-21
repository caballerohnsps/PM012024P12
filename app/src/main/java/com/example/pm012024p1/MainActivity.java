 package com.example.pm012024p1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Configuracion.SQLiteConexion;
import Configuracion.Transacciones;

 public class MainActivity extends AppCompatActivity {

     EditText nombres, apellidos, edad, correo, direccion;
     Button btnproceso,btneliminar;
     String idp = "0";


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         nombres = (EditText) findViewById(R.id.nombres);
         apellidos = (EditText) findViewById(R.id.apellidos);
         edad = (EditText) findViewById(R.id.edad);
         correo = (EditText) findViewById(R.id.correo);
         direccion = (EditText) findViewById(R.id.direccion);
         btnproceso = (Button) findViewById(R.id.btn_procesar);
         btneliminar = (Button) findViewById(R.id.btn_eliminar2);

         Intent intent = getIntent();
         if (intent.getExtras() != null) {
             btnproceso.setText("Actualizar");
             btneliminar.setVisibility(View.VISIBLE);
             idp = intent.getStringExtra("id");
             String nombrep = intent.getStringExtra("nombres");
             String apellidop = intent.getStringExtra("apellidos");
             int edadp = intent.getIntExtra("edad", 0);
             String correop = intent.getStringExtra("correo");
             String direccionp = intent.getStringExtra("direccion");

             nombres.setText(nombrep);
             apellidos.setText(apellidop);
             edad.setText(String.valueOf(edadp));
             correo.setText(correop);
             direccion.setText(direccionp);

         }

         btnproceso.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(intent.getExtras() != null){
                     Update();
                 }else{
                     AddPerson();
                 }

                 ListPerson();
             }
         });

         btneliminar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Delete();
                 ListPerson();
             }
         });

     }

     private void ListPerson(){
         Intent intent = new Intent(getApplicationContext(), ActivityList.class);
         startActivity(intent);
     }

     private void AddPerson() {
         SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
         SQLiteDatabase db = conexion.getWritableDatabase();

         ContentValues valores = new ContentValues();
         valores.put(Transacciones.nombres, nombres.getText().toString());
         valores.put(Transacciones.apellidos, apellidos.getText().toString());
         valores.put(Transacciones.edad, edad.getText().toString());
         valores.put(Transacciones.correo, correo.getText().toString());
         valores.put(Transacciones.direccion,direccion.getText().toString());

         Long resultado = db.insert(Transacciones.TablePersonas, Transacciones.id, valores);

         Toast.makeText(getApplicationContext(), "Registro Ingresado con exito " + resultado.toString(),
                 Toast.LENGTH_LONG).show();

         db.close();


     }

     private void Update(){
         SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
         SQLiteDatabase db = conexion.getWritableDatabase();

         ContentValues valores = new ContentValues();
         valores.put(Transacciones.nombres, nombres.getText().toString());
         valores.put(Transacciones.apellidos, apellidos.getText().toString());
         valores.put(Transacciones.edad, edad.getText().toString());
         valores.put(Transacciones.correo, correo.getText().toString());
         valores.put(Transacciones.direccion, direccion.getText().toString());

         int rowUpdate = db.update(Transacciones.TablePersonas, valores, Transacciones.id + "=?", new String[]{idp});

         Toast.makeText(getApplicationContext(), "Registro Actualizado con exito ", Toast.LENGTH_LONG).show();
         db.close();
     }

     private void Delete(){
         SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBName, null, 1);
         SQLiteDatabase db = conexion.getWritableDatabase();

         int romDelete = db.delete(Transacciones.TablePersonas, Transacciones.id+ "=?",new String[]{idp});
         Toast.makeText(getApplicationContext(), "Registro eliminado con exito ",
                 Toast.LENGTH_LONG).show();

         db.close();
     }
 }