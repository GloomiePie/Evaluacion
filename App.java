package org.example;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception{

        Connection conn =
                DriverManager.getConnection("jdbc:h2:~/examenParcial1", "sa", "");


        Thread hilo1 = new Thread(new AsambleistaRunnable());

        hilo1.start();

        System.out.println("Inserción de datos completa.");

        getDataVoto(conn);
        getDataRegion(conn);


    }

    private static void getDataVoto(Connection con) throws SQLException {
        String select = """ 
                SELECT
                    (SELECT COUNT(*) FROM VOTO WHERE TIPO = 'N') AS count_n,
                    (SELECT COUNT(*) FROM VOTO WHERE TIPO = 'S') AS count_s,
                    (SELECT COUNT(*) FROM VOTO WHERE TIPO = 'A') AS count_a;
                         """;
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(select)
        ) {
            while(rs.next()){
                System.out.printf("Número de 'SI': %s\n" +
                                "Número de 'NO': %s\n" +
                                "Número de 'ABSTENCION': %s\n\n",
                        rs.getString("COUNT_N"),
                        rs.getString("COUNT_S"),
                        rs.getString("COUNT_A"));
            }

        }
    }

    private static void getDataRegion(Connection con) throws SQLException{
        String selectN = """ 
                SELECT
                    (SELECT COUNT(*) FROM ASAMBLEISTA WHERE region = 'N') AS count_n,
                    (SELECT COUNT(*) FROM ASAMBLEISTA WHERE region = 'E') AS count_e,
                    (SELECT COUNT(*) FROM ASAMBLEISTA WHERE region = 'P') AS count_p;
                         """;
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(selectN)
        ) {
            while(rs.next()){
                System.out.printf("Asambleista/s nacionales: %s\n" +
                                "Asambleista/s extranjeros: %s\n" +
                                "Asambleista/s provinciales: %s\n",
                        rs.getString("COUNT_N"),
                        rs.getString("COUNT_E"),
                        rs.getString("COUNT_P"));
            }

        }
    }

}