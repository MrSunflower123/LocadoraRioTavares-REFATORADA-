
package utilidades;

import java.io.File;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 * Esta classe é resposável por criar e recuperar backups do sistema
 * @author guise
 */
public class BackupUtil {
    
   
    private static final String MYSQL_BIN = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\";

    private static final String USUARIO = "Administrador";
    private static final String SENHA   = "12345";
    private static final String BANCO   = "locadora_riotavares";

    
        /**
         * Executa os comandos para exportar um backup no MySQLWorkbenc
         * @param arquivoDestino
         * @throws Exception 
         */
          public static void exportarBackup(File arquivoDestino) throws Exception {

        String comando = String.format(
            "\"%smysqldump.exe\" --no-tablespaces --default-character-set=utf8mb4 "
          + "-u%s -p%s %s",
            MYSQL_BIN, USUARIO, SENHA, BANCO
        );

        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", comando);

        // Apenas o SQL vai para o arquivo
        pb.redirectOutput(arquivoDestino);

        // Não misturar erro com saída
        pb.redirectErrorStream(false);

        Process processo = pb.start();
        int exitCode = processo.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException(
                "Erro ao criar backup (código " + exitCode + ")"
            );
        }
    }


        /**
         * Executa os comandos para importar um backup no MySQLWorkbenc
         * @param arquivoSQL
         * @throws Exception 
         */
        public static void importarBackup(File arquivoSQL) throws Exception {

        ProcessBuilder pb = new ProcessBuilder(
            MYSQL_BIN + "mysql.exe",
            "--default-character-set=utf8mb4",
            "-u" + USUARIO,
            "-p" + SENHA,
            BANCO
        );

        pb.redirectInput(arquivoSQL);   // <-- AQUI ESTÁ A CHAVE
        pb.redirectErrorStream(true);

        Process processo = pb.start();
        int exitCode = processo.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException(
                "Erro ao importar backup (código " + exitCode + ")"
            );
        }
    }



    /**
     * Cria um backup em:
     * C:/Usuarios/nome_usuario/Downloads/dump/backup.sql
     */
    public void criarBackup() {

        int resposta = JOptionPane.showConfirmDialog(
            null,
            "Deseja fazer um backup de todos os dados do sistema?",
            "Realizar backup",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE
        );
        
        if (resposta != JOptionPane.YES_OPTION) return;
        
        try {
            String downloadsPath =
                System.getProperty("user.home") + "\\Downloads";

            File pastaDump = new File(downloadsPath, "dump");

            if (!pastaDump.exists()) {
                pastaDump.mkdirs();
            }

            File arquivoBackup = new File(pastaDump, "backup.sql");

            exportarBackup(arquivoBackup);

            JOptionPane.showMessageDialog(
                null,
                "Backup criado com sucesso!\n\nLocal:\n"
              + arquivoBackup.getAbsolutePath(),
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Erro ao criar backup:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

        } finally {
            trocarConexao();
        }
    }

    /**
     * Restaura um backup guardado em:
     * C:/Usuarios/nome_usuario/Downloads/dump/backup.sql
     */
    public void recuperarBackup() {

        int resposta = JOptionPane.showConfirmDialog(
            null,
            "ATENÇÃO!\n\n"
          + "Todos os dados atuais serão substituídos pelo backup.\n"
          + "Deseja continuar?",
            "Confirmar restauração",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (resposta != JOptionPane.YES_OPTION) return;

        try {
            String downloadsPath =
                System.getProperty("user.home") + "\\Downloads";

            File arquivoBackup =
                new File(downloadsPath + "\\dump\\backup.sql");

            if (!arquivoBackup.exists()) {
                JOptionPane.showMessageDialog(
                    null,
                    "Backup não encontrado.\n\nEsperado em:\n"
                  + arquivoBackup.getAbsolutePath(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            importarBackup(arquivoBackup);

            JOptionPane.showMessageDialog(
                null,
                "Backup restaurado com sucesso!\n"
              + "Reinicie o sistema para aplicar as alterações.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                null,
                "Erro ao restaurar backup:\n" + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );

        } finally {
            trocarConexao();
        }
    }

    
    /**
     * Retorna a conexão para o usuário adequado depois de realizar o backup
     */
    public void trocarConexao() {

        Usuario user = GuardarUsuario.getUsuario();
        String tipo = user.getTipoUsuario();

        switch (tipo) {
            case "Atendente":
                ConectarBD.criarConexao("Atendente", "12345");
                break;

            case "Gerente":
                ConectarBD.criarConexao("Gerente", "54321");
                break;

            default:
                ConectarBD.criarConexao("root", "gui2004");
                break;
        }
    }
        
                   
}
