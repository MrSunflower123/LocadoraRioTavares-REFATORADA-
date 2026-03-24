
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Jogo;

/**
 * Classe que vai acessar e manipular a tabela "jogos'
 * @author guise
 */

public class JogoDAO {
    
    /**
       * Insere um novo jogo na tabela
       * @param jogo
       */   
        public void inserir(Jogo jogo){
            EntityManager em = JPAUtil.getEntityManager();

            try{
                em.getTransaction().begin();
                em.persist(jogo);
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
     * Faz uma consulta geral dos jogos e guarda em uma lista
     * @return 
     */
      public List<Jogo> listar(){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              TypedQuery<Jogo> consulta = em.createQuery("SELECT j FROM EntidadeJogo j", Jogo.class);
              List<Jogo> listaJogo = consulta.getResultList();
              return listaJogo;
          }
          
          finally {
              JPAUtil.closeEntityManager();
          }
          
      }
      
      
      /**
      * Faz uma consulta na tabela através do titulo, plataforma ou 
      * desenvolvedora de um jogo e guarda em uma lista
      * @param filtro
      * @param texto
      * @return
      */
      public List<Jogo> listaComFiltro(int filtro, String texto){
          EntityManager em = JPAUtil.getEntityManager();
          
          try {
          String jpql;
          
          switch (filtro){
              
              case 1:
                  jpql = "SELECT j FROM EntidadeJogo j WHERE j.titulo LIKE :parametro";
                  break;
                  
              case 2:
                  jpql = "SELECT j FROM EntidadeJogo j WHERE j.plataforma LIKE :parametro";
                  break;
                  
              case 3:
                  jpql = "SELECT j FROM EntidadeJogo j WHERE j.desenvolvedora LIKE :parametro";
                  break;   
          
          
          default:
                throw new IllegalArgumentException("Filtro inválido: " + filtro);
          }
          
          TypedQuery<Jogo> consulta = em.createQuery(jpql, Jogo.class);
          consulta.setParameter("parametro", "%" + texto + "%");        
          List<Jogo> listaJogo = consulta.getResultList();
          
          return listaJogo;
          }
          
          finally {                 
                  JPAUtil.closeEntityManager();
                }
      }
      
      
      /**
       * Procura um jogo pela sua ID
       * @param id
       * @return 
       */
      public Jogo buscarId(int id){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              Jogo j = em.find(Jogo.class, id);
              return j;
          }
          finally{
                 JPAUtil.closeEntityManager();
          }
      }
      
      
      /**
      * Atualiza os dados de um jogo na base de dados pelo ID
      * @param selecionado
      * @param titulo
      * @param genero
      * @param plataforma
      * @param lancamento
      * @param desenvolvedora
      * @param copias
      */
      public void editar(Jogo selecionado, String titulo, String genero, 
                            String plataforma, int lancamento, String desenvolvedora, int copias){
          
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              em.getTransaction().begin();
              
              Jogo j = em.find(Jogo.class, selecionado.getId());
              
              if (j != null){
                  j.setTitulo(titulo);
                  j.setGenero(genero);
                  j.setPlataforma(plataforma);
                  j.setLancamento(lancamento);
                  j.setDesenvolvedora(desenvolvedora);
                  j.setCopias(copias);
                  
                  em.merge(j);
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
         * Exclui um jogo e os emprestimos feitos com esse jogo da base de dados pelo ID
         * @param idJogo
         */
         public void excluir(int idJogo){
            EntityManager em = JPAUtil.getEntityManager();

            try {
                em.getTransaction().begin();

                // 1) Exclue os empréstimos feitos com este jogo
                em.createQuery("DELETE FROM EntidadeEmprestimo emp WHERE emp.fkJogo.id = :id")
                  .setParameter("id", idJogo)
                  .executeUpdate();
                
                // 2) Depois exclue o jogo
                Jogo j = em.find(Jogo.class, idJogo);

                if (j != null) {
                    em.remove(j);
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
