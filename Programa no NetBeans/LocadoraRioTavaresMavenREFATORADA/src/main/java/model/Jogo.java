
package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe que será usada para criar um novo jogo
 * @author guise
 */

@Entity(name="EntidadeJogo")
@Table(name="jogos")
public class Jogo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column(name= "titulo")
    private String titulo;
    
    @Column(name= "genero")
    private String genero;
    
    @Column(name= "plataforma")
    private String plataforma;
    
    @Column(name= "lancamento")
    private int lancamento;
    
    @Column(name= "desenvolvedora")
    private String desenvolvedora;
    
    @Column(name= "copias")
    private int copias;
    
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
    
    
     public String getTitulo(){
        return titulo;
    }
    
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
    
    
    public String getPlataforma(){
        return plataforma;
    }
    
    public void setPlataforma(String plataforma){
        this.plataforma = plataforma;
    }
    
    
    public int getLancamento(){
        return lancamento;
    }
    
    public void setLancamento(int lancamento){
        this.lancamento = lancamento;
    }
    
    
    public String getDesenvolvedora(){
        return desenvolvedora;
    }
    
    public void setDesenvolvedora(String desenvolvedora){
        this.desenvolvedora = desenvolvedora;
    }
    
    
    public int getCopias(){
        return copias;
    }
    
    public void setCopias(int copias){
        this.copias = copias;
    }
}
