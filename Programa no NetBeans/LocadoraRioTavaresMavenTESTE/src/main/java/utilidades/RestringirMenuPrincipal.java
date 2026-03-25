
package utilidades;

import java.awt.Color;
import java.awt.Component;
import java.util.Set;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import model.Usuario;


/**
 * Classe que vai restringir o acesso dos usuários na tela MenuPrincipal
 * @author guise
 */
public class RestringirMenuPrincipal {
    
    // Método vai restringir o acesso do Atendente ao botões do menu principal
    public static void restringirAtendente(JComboBox<String> cbxUsuarios, JComboBox<String> cbxJogos){
        
        Usuario user = GuardarUsuario.getUsuario();
        
        String usuarioLogado = user.getTipoUsuario();
        
            if (usuarioLogado.equals("Atendente")) {

                cbxUsuarios.setEnabled(false);

                desabilitarOpcoes(cbxJogos, Set.of(1));

                JOptionPane.showMessageDialog(null, "Acesso Restringido. Este tipo de usuário não terá acesso a todas as funções.\n" 
                                                      + "Para mudar isso, escolha o tipo de usuário (Gerente).");   
            }
        
    }
    
    /**
     * Desabilita botões do sistema, de acordo com o tipo de usuário que está conectado
     * @param comboBox
     * @param indicesDesabilitados
     */ 
     protected static void desabilitarOpcoes(JComboBox<String> comboBox, Set<Integer> indicesDesabilitados) {
    
        // Customiza o renderizador para mostrar itens desabilitados, mas apenas visualmente
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (indicesDesabilitados.contains(index)) {
                    component.setEnabled(false); 
                    component.setForeground(Color.GRAY); 
                } else {
                    component.setEnabled(true);
                    component.setForeground(Color.BLACK);
                }
                return component;
            }
        });

        // Impede a seleção de opções desabilitadas
        comboBox.addActionListener(e -> {
            if (indicesDesabilitados.contains(comboBox.getSelectedIndex())) {
                JOptionPane.showMessageDialog(comboBox, "Acesso Negado a esta opção.");
                comboBox.setSelectedIndex(0); // volta para "Selecione"
            }
        });
    }
}
