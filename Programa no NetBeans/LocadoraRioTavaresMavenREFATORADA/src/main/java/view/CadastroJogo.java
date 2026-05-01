            // Pega os dados que devem ser verificados
            String titulo = txtTitulo.getText();           
            String genero = txtGenero.getText();            
            String plataforma = txtPlataforma.getText();           
            String lanca= txtLancamento.getText().trim();          
            String desen = txtDesenvolvedora.getText();            
            String copias = txtCopias.getText();
            // Verifica se os campos estão seguindos todas as regras antes de cadastrar
            validarCampos(titulo, genero, plataforma, lanca, desen, copias );
    /**
     * Valida se os campos não estão vazios e no formato correto
     * @param titulo
     * @param genero
     * @param plataforma
     * @param lancamento
     * @param desenvolvedora
     * @param copias
     * @throws java.lang.Exception
     */ 
        public void validarCampos(String titulo, String genero, String plataforma,
                                     String lancamento, String desenvolvedora, String copias) throws Exception {
