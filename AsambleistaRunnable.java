package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class AsambleistaRunnable implements Runnable {
    private static final int NUM_INSERTS = 5; // Número de filas a insertar en cada hilo

    public void run() {
        insertAsambleistas();
        insertVotos();
    }

    private void insertAsambleistas() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/examenParcial1", "sa", "")) {
            String insertQuery = "INSERT INTO ASAMBLEISTA (REGION) VALUES (?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                Random random = new Random();
                for (int i = 0; i < NUM_INSERTS; i++) {
                    preparedStatement.setString(1, getRandomRegion(random));
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertVotos() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu-pa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Asambleista> asambleistas = entityManager.createQuery("SELECT a FROM Asambleista a", Asambleista.class)
                .getResultList();

        Random random = new Random();

        int startIndex = 0;

        for (int i = 0; i < NUM_INSERTS; i++) {

            Asambleista asambleista = asambleistas.get(startIndex);

            String tipoVoto;
            int randomNumber = random.nextInt(100) + 1;
            if (randomNumber >= 1 && randomNumber <= 24) {
                tipoVoto = "S";
            } else if (randomNumber >= 25 && randomNumber <= 49) {
                tipoVoto = "N";
            } else {
                tipoVoto = "A";
            }

            Voto voto = new Voto();
            voto.setAsambleista(asambleista);
            voto.setTipoVoto(tipoVoto);
            entityManager.persist(voto);

            startIndex++;
        }

        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    private String getRandomRegion(Random random) {
        String[] regions = { "N", "E", "P" };
        return regions[random.nextInt(regions.length)];
    }



}