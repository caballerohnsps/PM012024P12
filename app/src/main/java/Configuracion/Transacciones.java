package Configuracion;

public class Transacciones {
    //Nombre de la base de datos

    public static final String DBName = "PM012024";

    //creacion de las tablas de base de datos
    public static final String TablePersonas = "personas";

    //creacion  de los campos de base de datos
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String direccion = "direccion";

    //DDL Create
    public static final String  CreateTablePersonas = "Create table "+ TablePersonas +"("+
            "id INTEGER PRIMARY KEY AUTOINCREMENT, nombres TEXT, apellidos TEXT, edad INTEGER, "+
            "correo TEXT, direccion TEXT )";

    //DDl Drop
    public static final String DropTablePersonas = "DROP TABLE IF EXISTS "+ TablePersonas;

    //DML
    public static final String SelectALLPersonas = "SELECT * FROM " + TablePersonas;

    //Actualizar Registro
    public static final String UpdatePersona = "UPDATE " + TablePersonas + " SET nombres = ?, apellidos = ?, edad = ?, correo = ?, direccion = ? WHERE id = ?";

    //Eliminar Registro
    public static final String DeletePersona = "DELETE FROM " + TablePersonas + " WHERE id = ?";
}
