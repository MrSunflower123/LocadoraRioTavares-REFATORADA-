
package utilidades;

import model.Jogo;

/**
 * Este Callback será usado entre CadastroEmprestimo e 
 * ListaSecundariaJogo
 * @author guise
 */
public interface JogoCallback {
    
    /**
     * Vai avisar a CadastroEmprestimo quando um jogo for selecionado 
     * em ListaSecundariaJogo.
     * @param jogo 
     */
     void onJogoSelecionado(Jogo jogo);
}
