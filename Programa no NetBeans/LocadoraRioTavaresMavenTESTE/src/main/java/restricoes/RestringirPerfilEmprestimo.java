
package restricoes;

import javax.swing.JButton;
import model.Usuario;
import utilidades.GuardarUsuario;
import utilidades.TipoUsuarioEnum;

/**
 * Classe que vai restringir o acesso do Supervisor na tela PerfilEmprestimo
 * @author guise
 */
public class RestringirPerfilEmprestimo {
    
    
    // Limita os botões que o Supervisor pode acessar
    public static void restringirSupervisor(JButton btnConfirmar, JButton btnEditar, JButton btnExcluir){
        
        Usuario user = GuardarUsuario.getUsuario();
        
        TipoUsuarioEnum usuarioLogado = TipoUsuarioEnum.valueOf(user.getTipoUsuario().toUpperCase());
        
            if (usuarioLogado == TipoUsuarioEnum.SUPERVISOR) {

                btnConfirmar.setEnabled(false);

                btnEditar.setEnabled(false);
                
                btnExcluir.setEnabled(false);
                
            }
        
    }
    
    
}
