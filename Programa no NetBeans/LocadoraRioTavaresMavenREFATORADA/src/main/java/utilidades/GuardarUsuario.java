
package utilidades;

import model.Usuario;


/**
 * Esta classe cria uma variável global
 * Guardar o usuário que está logado no sistema
 * @author guise
 */
public class GuardarUsuario {
    
    /**
    * Variável global, guardam quem é o usuário logado
    */ 
    private static Usuario usuarioLogado;

    public static void setUsuario(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuario() {
        return usuarioLogado;
    }
}
