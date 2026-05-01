
package restricoes;

import javax.swing.JButton;
import model.Usuario;
import utilidades.GuardarUsuario;
import utilidades.TipoUsuarioEnum;

/**
 * 
 * Restringe o acesso do Supervisor e do Atendente na tela PerfilJogo
 * @author guise
 */
public class RestringirPerfilJogo {
    
    
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
    
    
    // Limita os botões que o Atendente pode acessar
    public static void restringirAtendente(JButton btnConfirmar, JButton btnEditar, JButton btnExcluir){
        
        Usuario user = GuardarUsuario.getUsuario();
        
        TipoUsuarioEnum usuarioLogado = TipoUsuarioEnum.valueOf(user.getTipoUsuario().toUpperCase());
        
            if (usuarioLogado == TipoUsuarioEnum.ATENDENTE) {

                btnConfirmar.setEnabled(false);

                btnEditar.setEnabled(false);
                
                btnExcluir.setEnabled(false);
                
            }
        
    }
    
    
}
