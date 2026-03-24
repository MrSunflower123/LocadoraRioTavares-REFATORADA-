
package utilidades;

import dao.UsuarioDAO;
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
}
