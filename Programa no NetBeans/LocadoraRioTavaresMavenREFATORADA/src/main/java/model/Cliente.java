
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que será usada para criar um novo cliente
 * @author guise
 */

@Entity(name="EntidadeCliente")
@Table(name="clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name= "nome_sobrenome")
    String nome;
    
    @Column(name= "cpf")
    private String cpf;
    
    @Column(name= "bairro")
    private String bairro;
    
    @Column(name= "rua")
    private String rua;
    
    @Column(name= "residencial")
    private int Residencial;
    
    
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
    
    
    public String getNome(){
        return nome;
    }
    
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    
    public String getCpf(){
        return cpf;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    
    public String getBairro(){
        return bairro;
    }
    
    public void setBairro(String bairro ){
        this.bairro = bairro;
    }
    
    
    public String getRua(){
        return rua;
    }
    
    public void setRua(String rua){
        this.rua = rua;
    }
    
    
    public int getResidencial(){
        return Residencial;
    }
    
    public void setResidencial(int Residencial){
        this.Residencial = Residencial;
    }
    
}
