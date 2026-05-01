/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cadastroJogo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import view.CadastroJogo;

/**
 *
 * @author guise
 */
public class TesteCadastroJogoJUnit {
    
    public TesteCadastroJogoJUnit() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    // Verificando se há um campo vazio
    // Deve lançar uma exceção se um dos campos estiver vazio
    // Uma exceção é lançada com uma mensagem específica para aquele campo
    @Test
    public void campoVazio(){
        
       CadastroJogo cadastro = new CadastroJogo();
       
       Exception ex = assertThrows(Exception.class, () -> {
       
       cadastro.validarCampos(
                "", // titulo do jogo vazio
                "RPG",
                "PS5",
                "2000",
                "Valve",
                "20"     
            );
       });
        
       // mensagem específica para o campo  de titulo
       assertEquals("Campo Titulo não pode ser vazio.", ex.getMessage());
           
    }// fecha teste
    
    // Verificando se há campos vazios
    // Não deve lançar exceção se não há nenhum campo vazio
    @Test
    public void campoPreenchido() throws Exception{
        
       CadastroJogo cadastro = new CadastroJogo();
         
       cadastro.validarCampos(
                "Jogo", // titulo do jogo vazio
                "RPG",
                "PS5",
                "2000",
                "Valve",
                "20"     
            );
        
       // mensagem específica para o campo  de titulo
       assertTrue(true);
           
    }// fecha teste
    
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
