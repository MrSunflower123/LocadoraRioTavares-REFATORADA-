
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Cliente;

/**
 * Classe que vai acessar e manipular a tabela "clientes'
 * @author guise
 */

public class ClienteDAO {
    
       /**
       * Insere um novo cliente na tabela
       * @param cliente
       */   
        public void inserir(Cliente cliente){
            EntityManager em = JPAUtil.getEntityManager();

            try{
                em.getTransaction().begin();
                em.persist(cliente);
                em.getTransaction().commit();           
            } catch(Exception e) {
                em.getTransaction().rollback();
                throw e;
            }

            finally {
                JPAUtil.closeEntityManager();
            }

        }
    
    
    /**
     * Faz uma consulta geral dos clientes e guarda em uma lista
     * @return 
     */
      public List<Cliente> listar(){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              TypedQuery<Cliente> consulta = em.createQuery("SELECT c FROM EntidadeCliente c", Cliente.class);
              List<Cliente> listaCliente = consulta.getResultList();
              return listaCliente;
          }
          
          finally {
              JPAUtil.closeEntityManager();
          }
          
      }
      
      
      /**
      * Faz uma consulta na tabela através do nome ou cpf de um cliente e guarda em uma lista
      * @param filtro
      * @param texto
      * @return
      */
      public List<Cliente> listaComFiltro(int filtro, String texto){
          EntityManager em = JPAUtil.getEntityManager();
          
          try {
          String jpql;
          
          switch (filtro){
              
              case 1:
                  jpql = "SELECT c FROM EntidadeCliente c WHERE c.nome LIKE :parametro";
                  break;
                  
              case 2:
                  jpql = "SELECT c FROM EntidadeCliente c WHERE c.cpf LIKE :parametro";
                  break;
          
          
          default:
                throw new IllegalArgumentException("Filtro inválido: " + filtro);
          }
          
          TypedQuery<Cliente> consulta = em.createQuery(jpql, Cliente.class);
          consulta.setParameter("parametro", "%" + texto + "%");        
          List<Cliente> listaCliente = consulta.getResultList();
          
          return listaCliente;
          }
          
          finally {                 
                  JPAUtil.closeEntityManager();
                }
      }
      
      
      /**
       * Procura um cliente pela sua ID
       * @param id
       * @return 
       */
      public Cliente buscarId(int id){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              Cliente c = em.find(Cliente.class, id);
              return c;
          }
          finally{
                 JPAUtil.closeEntityManager();
          }
      }
      
      
      /**
      * Atualiza os dados de um cliente na base de dados pelo ID
      * @param selecionado
      * @param nome
      * @param cpf
      * @param bairro
      * @param rua
      * @param residencial
      */
      public void editar(Cliente selecionado, String nome, String cpf, 
                            String bairro, String rua, int residencial){
          
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              em.getTransaction().begin();
              
              Cliente c = em.find(Cliente.class, selecionado.getId());
              
              if (c != null){
                  c.setNome(nome);
                  c.setCpf(cpf);
                  c.setBairro(bairro);
                  c.setRua(rua);
                  c.setResidencial(residencial);
                  
                  em.merge(c);
              }
              
              em.getTransaction().commit();
          }
          catch (Exception e) {
          em.getTransaction().rollback();
          throw e;
          }
          
          finally {
            JPAUtil.closeEntityManager(); 
          }
      }
      
      
         /**
         * Exclui um cliente e seus empréstimos da base de dados pelo ID
         * @param idCliente
         */
        public void excluir(int idCliente){
            EntityManager em = JPAUtil.getEntityManager();

            try {
                em.getTransaction().begin();
                
                // 1) Exclue os empréstimos vinculados ao cliente
                em.createQuery("DELETE FROM EntidadeEmprestimo e WHERE e.fkCliente.id = :id")
                  .setParameter("id", idCliente)
                  .executeUpdate();
                
                
                // 2) Depois exclue o cliente
                Cliente c = em.find(Cliente.class, idCliente);

                if (c != null) {
                    em.remove(c);
                }

                em.getTransaction().commit();
            }
            catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
            finally {
                JPAUtil.closeEntityManager();
            }
        }

}
