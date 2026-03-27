
package utilidades;

import dao.UsuarioDAO;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 * Classe verifica se o usuário que está tentando logar existe na base de dados
 * @author guise
 */
public class AutenticarLogin {


    /**
     * Verifica se os dados do usuário existem na base de dados
     * @param usuario
     * @param senha
     * @param tipoUsuario
     */
    public static void autenticarUsuario (String usuario, String senha, String tipoUsuario){
        
        UsuarioDAO dao = new UsuarioDAO();
        
        Usuario user = dao.autenticar(usuario, senha, tipoUsuario);
        
        // Se retornar null, o DAO já mostrou a mensagem
        if (user == null) {
        return;
        }
        
        else {
        // Guarda o usuário logado em uma variável global
        GuardarUsuario.setUsuario(user);
       }

    }
    
    
    /**
     * Verifica o tipo de usuário que está se conectando no sistema
     * 
     * @param tipo
     * @param nomeUsuario
     */
    public static void verificarTipo(String tipo, String nomeUsuario){
        
        TipoUsuarioEnum tipoUsuario = TipoUsuarioEnum.valueOf(tipo.toUpperCase());
        
        // Determina que tipo usuário está tentando se conectar
        switch (tipoUsuario){
            
            case GERENTE:
            ConectarBD.criarConexao("Gerente", "54321");
            break;
            
            case ATENDENTE:
            ConectarBD.criarConexao("Atendente", "12345");
            break;
            
            case SUPERVISOR:
            ConectarBD.criarConexao("Supervisor", "56789");
            break;
            
            case ESTOQUISTA:
            ConectarBD.criarConexao("Estoquista", "98765");
            break;

        default:
            // Conecta como um usuário root por padrão
            ConectarBD.criarConexao("root", "gui2004");
            break;
        }
        
        JOptionPane.showMessageDialog(null, "Olá, " + nomeUsuario + ", seu tipo de usuário é: " + tipo +
                                      ". Seja bem-vindo!" );
    }
    
}
