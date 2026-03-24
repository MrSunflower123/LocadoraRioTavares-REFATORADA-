
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Classe que faz a criação de EntityManager e seu encerramento.
 * 
 */


public class JPAUtil {
    
    /**
     * Criar a EntityManager e EntityManagerFactory
     */
    private static final String PERSISTENCE_UNIT = "Locadora-PU";
    
    private static EntityManager em;
    private static EntityManagerFactory fabrica;
    
    //Cria a a fabrica se estiver nula e a retorna
    public static EntityManager getEntityManager(){
        if(fabrica == null || !fabrica.isOpen())
            fabrica = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        
        //Se EntityManager for nula ou não estiver aberta, crie um nova instância
        if(em == null || !em.isOpen()) 
            em = fabrica.createEntityManager();

        return em;
    }
    
     /**
     * Fecha o EntityManager e fabrica
     */
    public static void closeEntityManager(){
        if(em.isOpen() && em != null){
            em.close();
            fabrica.close();
        }
    
}
    
}