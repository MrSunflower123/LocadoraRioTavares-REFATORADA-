
package utilidades;

import java.awt.Color;
import java.awt.Component;
import java.util.Set;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;


/**
 * Classe que vai restringir o acesso dos usuários na tela MenuPrincipal
 * @author guise
 */
public class RestringirMenuPrincipal {
    
  
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
