/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package compradehamburguesas;


import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhoan
 */
public class conDB {
    
    Connection conn; 

    // datos de conexion 
    String bd = "taller_hamburguesa";
    String login = "root";
    String password = "";
    String host = "127.0.0.1";

    public conDB() {
        conn = null;
    }

    public boolean conectarMySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + bd, login, password);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encuentra la referencia del conector de MySQL.");
            return false;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al tratar de conectar con la base de datos '" + bd + "'\n" + ex);
            return false;
        }
        return true;
    }

    public boolean desconectar() {
        try {
            conn.close();
            return true;
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error al tratar de conectar con la base de datos '" + bd + "'");
            return false;
        }
    }
        
    public boolean insertar(String tabla, ArrayList<String> datos) {
        boolean resp = false;
        if(conectarMySQL()){
            String sql = "INSERT INTO " + tabla + " VALUES('";
            for (String campo : datos) {
                sql += campo + "','";
            }
            sql = sql.substring(0, sql.length() - 2);
            sql += ");";
            //System.out.println(sql);
            try {
                //PreparedStatement pstmt = conn.prepareStatement(sql);
                //ResultSet rs = pstmt.executeQuery();
                Statement stmt = conn.createStatement();
                int resultado = stmt.executeUpdate(sql);
                
                resp = true;
            } catch (SQLException sqle) {                
                JOptionPane.showMessageDialog(null, "Error al ejecutar la actualización.\n" + sqle);
                resp = false;
            }
            desconectar();
        }
        return resp;
    }

    public ArrayList getColumns(String tabla) {
        ArrayList<String> columns = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT COLUMN_NAME FROM Information_Schema.Columns WHERE TABLE_NAME = '" + tabla + "'", 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                columns.add(rs.getString(1));
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return columns;
    }
    
    public boolean actualizar(String tabla, ArrayList<String> datos, String condicion) {
        boolean resp = false;
        if(conectarMySQL()){
            ArrayList<String> nomColumns = new ArrayList<>(getColumns(tabla));        
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ").append(tabla).append(" SET ");        
            for (int i = 0; i < nomColumns.size(); i++) {
                sql.append(nomColumns.get(i)).append(" = '").append(datos.get(i)).append("', ");
            }        
            sql = new StringBuilder(sql.substring(0, sql.length() - 2));        
            sql.append(" WHERE ").append(condicion);
            //System.out.println(sql);
            try {
                //PreparedStatement pstmt = conn.prepareStatement(sql);
                //ResultSet rs = pstmt.executeQuery();
                Statement stmt = conn.createStatement();
                int resultado = stmt.executeUpdate(sql.toString());
                
                resp = true;
            } catch (SQLException sqle) {                
                JOptionPane.showMessageDialog(null, "Error al ejecutar la actualización.\n" + sqle);
                resp = false;
            }
            desconectar();
        }
        return resp;
    }
    
    public boolean actualizar(String sql) {
        boolean resp = false;
        if(conectarMySQL()){
            try {
                //PreparedStatement pstmt = conn.prepareStatement(sql);
                //ResultSet rs = pstmt.executeQuery();
                Statement stmt = conn.createStatement();
                int resultado = stmt.executeUpdate(sql);
                
                resp = true;
            } catch (SQLException sqle) {                
                JOptionPane.showMessageDialog(null, "Error al ejecutar la actualización.\n" + sqle);
                resp = false;
            }
            desconectar();
        }
        return resp;
    }
    
    public boolean borrar(String sql) {        
        boolean resp = false;
        if(conectarMySQL()){
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = pstmt.executeQuery();
                
                resp = true;
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Error al ejecutar el borrado.\n" + sqle);
                resp = false;
            }
            desconectar();
        }
        return resp;
    }
    
    public boolean borrar(String tabla, String condicion) {        
        return borrar("DELETE FROM " + tabla + " WHERE " + condicion);
    }

    public String[] consultaFila(String tabla, String campo, String valor) {
        String fila[] = null;
        if(conectarMySQL()){
            try {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM " + tabla + " WHERE " + campo + " = '" + valor + "'", 
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = pstmt.executeQuery();
                
                int c = getSizeQuery(rs); // obtener cantidad de registros de la consulta                	
                if (c > 0) {//si la cantidad de filas (registros) resultantes de la consulta es mayor a cero, es porque el valor a buscar existe

                    int cantColumnas = rs.getMetaData().getColumnCount();//obtener cantidad de columnas (campos) resultantes de la consulta									
                    fila = new String[cantColumnas];//dar tama�o al array dependiendo de la cantidad de columnas (campos) resultantes de la consulta

                    while (rs.next()) {//recorrer la consulta
                        for (int i = 1; i <= cantColumnas; i++) {
                            fila[i - 1] = rs.getString(i);//y llenar el array String con los campos a retornar
                        }
                        break;
                    }
                } else {
                    fila = null;
                    JOptionPane.showMessageDialog(null, "No hay registros que cumplan la condición.");
                }
            } catch (SQLException sqle) {
                fila = null;
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
            }
            desconectar();
        }
        return fila;
    }

    public String[][] consultaMatriz(String sql) {
        String matrizRegistros[][] = null;
        if(conectarMySQL()){
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = pstmt.executeQuery();
                
                int canFilas = getSizeQuery(rs);
                if (canFilas > 0) {
                    int canColumnas = rs.getMetaData().getColumnCount();
                    matrizRegistros = new String[canFilas][canColumnas];
                    int f = 0;
                    while (rs.next()) {
                        for (int c = 0; c < canColumnas; c++) {
                            matrizRegistros[f][c] = rs.getString(c + 1);
                        }
                        f++;
                    }
                } else {
                    matrizRegistros = null;
                    JOptionPane.showMessageDialog(null, "No hay registros que cumplan la condición.");
                }
            } catch (SQLException sqle) {
                matrizRegistros = null;
                JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
            }
            desconectar();
        }
        return matrizRegistros;
    }

    public int getSizeQuery(ResultSet rs) {
        int cantFilas = -1;
        try {
            rs.last(); //Desplazar el puntero de lectura a la ultima fila (registro)
            cantFilas = rs.getRow(); //Calcular la cantidad de filas (registros) que arroja la consulta
            rs.beforeFirst(); //Desplazar el puntero de lectura a la primera fila (registro)
        } catch (SQLException sqle) { }
        return cantFilas;
    }
    
    public int maximo(String tabla, String campo) { // obtener el maximo valor almacenado en un campo
        return max_min(tabla, campo, "MAX");
    }
    
    public int minimo(String tabla, String campo) { // obtener el minimo valor almacenado en un campo
        return max_min(tabla, campo, "MIN");
    }
    
    private int max_min(String tabla, String campo, String funcion) { // obtener el maximo/minimo valor almacenado en un campo
        int valor = 0;
        try {            
            PreparedStatement pstmt = conn.prepareStatement("SELECT " + funcion + "(" + campo + ") FROM " + tabla,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                valor = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return valor;
    }
    
    public int contar(String tabla) { // contar cuantos registros tiene una tabla
        int cont = 0;
        try {            
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + tabla,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                cont = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return cont;
    }
    
    public int contar(String tabla, String condicion) { // contar cuantos registros cumplen una determinada condicion
        int cont = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM " + tabla + " WHERE " + condicion,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                cont = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return cont;
    }

    public double sumar(String tabla, String campo) {
        double sum = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(" + campo + ") FROM " + tabla,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                sum = Double.parseDouble(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return sum;
    }
    
    public double sumar(String tabla, String campo, String condicion) {
        double sum = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT SUM(" + campo + ") FROM " + tabla + " WHERE " + condicion,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                sum = Double.parseDouble(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return sum;
    }
    
    public double promedio(String tabla, String campo) {
        double prom = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT AVG(" + campo + ") FROM " + tabla,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                prom = Double.parseDouble(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return prom;
    }
    
    public double promedio(String tabla, String campo, String condicion) {
        double prom = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT AVG(" + campo + ") FROM " + tabla + " WHERE " + condicion,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                prom = Double.parseDouble(rs.getString(1));
            }
        } catch (SQLException sqle) { 
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return prom;
    }
    
    public String[] distintos(String tabla, String campo) {
        String matrizRegistros[];
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT " + campo + " FROM " + tabla + " ORDER BY " + campo + " ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            int canFilas = getSizeQuery(rs);
            if (canFilas > 0) {
                matrizRegistros = new String[canFilas];
                int f = 0;
                while (rs.next()) {
                    matrizRegistros[f] = rs.getString(1);
                    f++;
                }
            } else {
                matrizRegistros = null;
                JOptionPane.showMessageDialog(null, "No hay registros que cumplan la condición.");
            }
        } catch (SQLException sqle) {
            matrizRegistros = null;
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return matrizRegistros;
    }
    
    public String[] distintos(String tabla, String campo, String condicion) {
        String matrizRegistros[];
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT " + campo + " FROM " + tabla + " WHERE " + condicion + " ORDER BY " + campo + " ASC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            int canFilas = getSizeQuery(rs);
            if (canFilas > 0) {
                matrizRegistros = new String[canFilas];
                int f = 0;
                while (rs.next()) {
                    matrizRegistros[f] = rs.getString(1);
                    f++;
                }
            } else {
                matrizRegistros = null;
                JOptionPane.showMessageDialog(null, "No hay registros que cumplan la condición.");
            }
        } catch (SQLException sqle) {
            matrizRegistros = null;
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return matrizRegistros;
    }
    
    public String[][] frecuencia(String tabla, String campo){ // calcula la frecuancia solo si el campo tiene valores
        String matrizRegistros[][] = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT " + campo + ", COUNT(*) AS frecuencia FROM "
                    + tabla + " GROUP BY " + campo + " ORDER BY frecuencia DESC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            int canFilas = getSizeQuery(rs);
            if (canFilas > 0) {
                int canColumnas = rs.getMetaData().getColumnCount();
                matrizRegistros = new String[canFilas][canColumnas];
                int f = 0;
                while (rs.next()) {
                    for (int c = 0; c < canColumnas; c++) {
                        matrizRegistros[f][c] = rs.getString(c + 1);
                    }
                    f++;
                }
            } else {
                matrizRegistros = null;
                JOptionPane.showMessageDialog(null, "No hay registros que cumplan la condición.");
            }
        } catch (SQLException sqle) {
            matrizRegistros = null;
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta." + sqle);
        }
        return matrizRegistros;
    }
    
    public HashMap frecuencia(String tabla, String campo, String valores) {
        HashMap<String, Integer> frecs = new HashMap<>();
        String items[] = valores.split(",");
        for (String dato : items) frecs.put(dato.trim(), 0); // llenar el HashMap con frecuencias cero por cada valor a buscar
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT " + campo + ", COUNT(*) AS frecuencia FROM "
                    + tabla + " GROUP BY " + campo,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            int canFilas = getSizeQuery(rs);
            if (canFilas > 0) {
                String matrizRegistros[][] = new String[canFilas][2];
                int f = 0;
                while (rs.next()) {
                    for (int c = 0; c < 2; c++) {
                        matrizRegistros[f][c] = rs.getString(c + 1);
                    }
                    f++;
                }
                for (String[] registro : matrizRegistros) {
                    frecs.replace(registro[0], Integer.valueOf(registro[1])); // modicar el valor (frecuencia) de los items encontrados en la tabla
                }
            }
            rs.close();
            return frecs;
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta.\n" + sqle);
            return null;
        }
    }
    
    public HashMap moda(String tabla, String campo) {
        HashMap<String, Integer> frecs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT " + campo + ", COUNT(*) AS moda FROM "
                    + tabla + " GROUP BY " + campo + " ORDER BY moda DESC",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pstmt.executeQuery();
            
            int canFilas = getSizeQuery(rs);
            if (canFilas > 0) {
                String matrizRegistros[][] = new String[canFilas][2];
                int f = 0;
                while (rs.next()) {
                    for (int c = 0; c < 2; c++) {
                        matrizRegistros[f][c] = rs.getString(c + 1);
                    }
                    f++;
                }
                
                frecs = new HashMap<>();
                frecs.put(matrizRegistros[0][0], Integer.valueOf(matrizRegistros[0][1])); // guardar la primer moda encontrada
                if(canFilas > 1){ // si existen varias modas, guardar las otras que sean iguales a la primera
                    int primer_moda = Integer.parseInt(matrizRegistros[0][1]); // primer moda encontrada
                    for (int i = 1; i < matrizRegistros.length; i++) { // recorrer la matriz de resultados de la consulta para buscar otras modas iguales a la primera
                        String[] registro = matrizRegistros[i];
                        if(Integer.parseInt(registro[1]) == primer_moda) // si moda encontrada == primer_moda, entonces ...
                            frecs.put(matrizRegistros[i][0], Integer.valueOf(matrizRegistros[i][1])); // guardar la otra moda
                    }
                }
            }
            rs.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error al ejecutar la consulta.\n" + sqle);
        }
        return frecs;
    }
    
    
}
