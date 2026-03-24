
package utilidades;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Está Classe troca o usuário que vai se conectar com a base de dados
 * @author guise
 */

public class ConectarBD {
    
     private static EntityManagerFactory emf;
    
    /**
     * Vai trocar o usuário que irá se conectar
     * @param user
     * @param pass 
     */
    public static void criarConexao(String user, String pass) {

        Map<String, Object> props = new HashMap<>();

        props.put("jakarta.persistence.jdbc.user", user);
        props.put("jakarta.persistence.jdbc.password", pass);

        // Mantém o restante do persistence.xml igual
        emf = Persistence.createEntityManagerFactory("Locadora-PU", props);
    }

    /**
     * Conexão padrão caso algo de errado
    */ 
    public static EntityManager getEntityManager() {
        if (emf == null) {
            
            criarConexao("root", "gui2004");
        }
        return emf.createEntityManager();
    }
}
