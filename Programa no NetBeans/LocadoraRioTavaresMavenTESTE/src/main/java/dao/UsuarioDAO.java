
package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;
import utilidades.ChaveCriptografica;

/**
 * Classe que vai acessar e manipular a tabela "usuarios'
 * @author guise
 */

public class UsuarioDAO {
    
       /**
       * Insere um novo usuario na tabela
       * @param usuario
       */   
        public void inserir(Usuario usuario){
            EntityManager em = JPAUtil.getEntityManager();

            try{
                em.getTransaction().begin();
                em.persist(usuario);
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
     * Faz uma consulta geral dos usuarios e guarda em uma lista
     * @return 
     */
      public List<Usuario> listar(){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              TypedQuery<Usuario> consulta = em.createQuery("SELECT u FROM EntidadeUsuario u", Usuario.class);
              List<Usuario> listaUsuario = consulta.getResultList();
              return listaUsuario;
          }
          
          finally {
              JPAUtil.closeEntityManager();
          }
          
      }
      
      
      /**
       * Procura um usuario pela sua ID
       * @param id
       * @return 
       */
      public Usuario buscarId(int id){
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              Usuario u = em.find(Usuario.class, id);
              return u;
          }
          finally{
                 JPAUtil.closeEntityManager();
          }
      }
      
      
      /**
      * Faz uma consulta na tabela através do nome de um usuario e guarda em uma lista
      * @param filtro
      * @param texto
      * @return
      */
      public List<Usuario> listaComFiltro(int filtro, String texto){
          EntityManager em = JPAUtil.getEntityManager();
          
          try {
          String jpql;
          
          switch (filtro){
              
              case 1:
                  jpql = "SELECT u FROM EntidadeUsuario u WHERE u.nomeUsuario LIKE :parametro";
                  break;
  
          default:
                throw new IllegalArgumentException("Filtro inválido: " + filtro);
          }
          
          TypedQuery<Usuario> consulta = em.createQuery(jpql, Usuario.class);
          consulta.setParameter("parametro", "%" + texto + "%");        
          List<Usuario> listaUsuario = consulta.getResultList();
          
          return listaUsuario;
          }
          
          finally {                 
                  JPAUtil.closeEntityManager();
                }
      }
      
      
      /**
      * Atualiza os dados de um usuario na base de dados pelo ID
      * @param selecionado
      * @param nome
      * @param senha
      * @param tipoUsuario
      */
      public void editar(Usuario selecionado, String nome, String senha, 
                            String tipoUsuario){
          
          EntityManager em = JPAUtil.getEntityManager();
          
          try{
              em.getTransaction().begin();
              
              Usuario u = em.find(Usuario.class, selecionado.getId());
              
              if (u != null){
                  u.setNomeUsuario(nome);
                  u.setSenha(senha);
                  u.setTipoUsuario(tipoUsuario);
                  
                  em.merge(u);
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
         * Exclui um usuario do sistema da base de dados pelo ID
         * @param id
         */
        public void excluir(int id){
            EntityManager em = JPAUtil.getEntityManager();

            try {
                em.getTransaction().begin();

                Usuario u = em.find(Usuario.class, id);

                if (u != null) {
                    em.remove(u);
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
     * Verifica se o usuário e senha existem no banco de dados
     * @param nomeUsuario
     * @param senha
     * @param tipo
     * @return 
     */
    public Usuario autenticar(String nomeUsuario, String senha, String tipo){
           EntityManager em = JPAUtil.getEntityManager();
           
           String senhaCripto = ChaveCriptografica.getMD5(senha);
           
           try{
               TypedQuery<Usuario> consulta = em.createQuery(
               "SELECT u FROM EntidadeUsuario u WHERE u.nomeUsuario = :parametro1 AND u.senha = :parametro2 AND u.tipoUsuario = :parametro3", 
               Usuario.class);
               
               consulta.setParameter("parametro1", nomeUsuario);
               consulta.setParameter("parametro2", senhaCripto);
               consulta.setParameter("parametro3", tipo);
               
               return consulta.getSingleResult();
               
           } catch (NoResultException e){
               JOptionPane.showMessageDialog(null, "O Usuário, Senha ou o Tipo estão incorretos!");
               return null;
           }
        
           finally {
            JPAUtil.closeEntityManager();
        }
    }
}
