
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import model.Emprestimo;

/**
 * Classe que vai acessar e manipular a tabela "emprestimos'
 * @author guise
 */

public class EmprestimoDAO {
    
    
       /**
       * Insere um novo emprestimo na tabela
       * @param emp
       */   
        public void inserir(Emprestimo emp){
            EntityManager em = JPAUtil.getEntityManager();

            try{
                em.getTransaction().begin();
                em.persist(emp);
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
     * Faz uma consulta geral dos emprestimos e guarda em uma lista
     * @return 
     */
      public List<Emprestimo> listar(){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              TypedQuery<Emprestimo> consulta = em.createQuery("SELECT e FROM EntidadeEmprestimo e", Emprestimo.class);
              List<Emprestimo> listaEmprestimo = consulta.getResultList();
              return listaEmprestimo;
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
      public List<Emprestimo> listaComFiltro(int filtro, String texto){
          EntityManager em = JPAUtil.getEntityManager();
          
          try {
          String jpql;
          
          switch (filtro){
              
              case 1:
                  jpql = "SELECT emp FROM EntidadeEmprestimo emp WHERE emp.nomeCliente LIKE :parametro";
                  break;
                  
              case 2:
                  jpql = "SELECT emp FROM EntidadeEmprestimo emp WHERE emp.cpf LIKE :parametro";
                  break;
          
          
          default:
                throw new IllegalArgumentException("Filtro inválido: " + filtro);
          }
          
          TypedQuery<Emprestimo> consulta = em.createQuery(jpql, Emprestimo.class);
          consulta.setParameter("parametro", "%" + texto + "%");        
          List<Emprestimo> listaEmprestimo = consulta.getResultList();
          
          return listaEmprestimo;
          }
          
          finally {                 
                  JPAUtil.closeEntityManager();
                }
      }
      
      
      /**
       * Procura um emprestimo pela sua ID
       * @param id
       * @return 
       */
      public Emprestimo buscarId(int id){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              Emprestimo emp = em.find(Emprestimo.class, id);
              return emp;
          }
          finally{
                 JPAUtil.closeEntityManager();
          }
      }
      
      
      /**
      * Atualiza os dados de um emprestimo na base de dados pelo ID
      * @param selecionado
      * @param nome
      * @param cpf
      * @param titulo
      * @param dataEmp
      * @param devolucao
      */
      public void editar(Emprestimo selecionado, String nome, String cpf, 
                            String titulo, LocalDate dataEmp, LocalDate devolucao){
          
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              em.getTransaction().begin();
              
              Emprestimo emp = em.find(Emprestimo.class, selecionado.getId());
              
              if (emp != null){
                  emp.setNomeCliente(nome);
                  emp.setCpf(cpf);
                  emp.setTituloJogo(titulo);
                  emp.setDataEmp(dataEmp);
                  emp.setDevolucao(devolucao);
                  
                  em.merge(emp);
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
         * Exclui um emprestimo da base de dados pelo ID
         * @param id
         */
        public void excluir(int id){
            EntityManager em = JPAUtil.getEntityManager();

            try {
                em.getTransaction().begin();

                Emprestimo emp = em.find(Emprestimo.class, id);

                if (emp != null) {
                    em.remove(emp);
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

