
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class AsambleistaController {

    private static EntityManager em = null;

    private void getEm(String puName) {
        if(em == null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(puName);
            em = emf.createEntityManager();
        }
    }

    public boolean addAsambleista(AsambleistaDTO asambleista) {
        Asambleista newAsambleista = new Asambleista(asambleista.voto(),
                asambleista.region());
        em.getTransaction().begin();
        em.persist(newAsambleista);
        em.flush();
        em.getTransaction().commit();

        return true;
    }

    public void close() {
        em.close();
    }
}
