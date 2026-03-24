
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Classe que será usada para criar um novo jogo
 * @author guise
 */

@Entity(name="EntidadeEmprestimo")
@Table(name="emprestimos")
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    
    // Chave estrangeira, chave primária de EntidadeCliente
    @ManyToOne
    @JoinColumn(
    name = "id_cliente",
    foreignKey = @ForeignKey(name = "fk_id_cliente"))                     
    private Cliente fkCliente;
    
    // Chave estrangeira, chave primária de EntidadeJogo
    @ManyToOne
    @JoinColumn(
    name = "id_jogo",
    foreignKey = @ForeignKey(name = "fk_id_jogo"))  
    private Jogo fkJogo;
    
    
    
    @Column(name= "nome_cliente")
    private String nomeCliente;
    
    @Column(name= "cpf_cliente")
    private String cpf;
    
    @Column(name= "titulo_jogo")
    private String tituloJogo;
    
    @Column(name= "data_emprestimo")
    private LocalDate dataEmp;
    
    @Column(name= "devolucao")
    private LocalDate devolucao;
    
    
    /**
     * Métodos getters e setters da classe
     * @return 
     */
    
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    
    
    public Cliente getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Cliente fkCliente) {
        this.fkCliente = fkCliente;
    }

    
    
    public Jogo getFkJogo() {
        return fkJogo;
    }

    public void setFkJogo(Jogo fkJogo) {
        this.fkJogo = fkJogo;
    }
    
    
    public String getNomeCliente(){
        return nomeCliente;
    }
    
    public void setNomeCliente(String nomeCliente ){
        this.nomeCliente = nomeCliente;
    }
    
    
    public String getCpf(){
        return cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    
    public String getTituloJogo(){
        return tituloJogo;
    }
    
    public void setTituloJogo(String tituloJogo){
        this.tituloJogo = tituloJogo;
    }
    
    
    public LocalDate getDataEmp(){
        return dataEmp;
    }
    
    public void setDataEmp(LocalDate dataEmp){
        this.dataEmp = dataEmp;
    }
    
    
    public LocalDate getDevolucao(){
        return devolucao;
    }
    
    public void setDevolucao(LocalDate devolucao){
        this.devolucao = devolucao;
    }
}
