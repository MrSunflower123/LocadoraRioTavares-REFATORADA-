
package utilidades;

import model.Cliente;

/**
 * Este Callback será usado entre CadastroEmprestimo e 
 * ListaSecundariaCliente
 * @author guise
 */
public interface ClienteCallback {
    
    /**
     * Vai avisar a CadastroEmprestimo quando um cliente for selecionado 
     * em ListaSecundariaCliente.
     * @param cliente 
     */
    void onClienteSelecionado(Cliente cliente);
 
}
