package utilidades;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe que vai gerar uma chave criptográfica hashMD5
 * @author guise
 */
public class ChaveCriptografica {
    /**
     * Gera uma nova chave criptográfica
     * @param texto
     * @return 
     */
public static String getMD5(String texto) {
    try {

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(texto.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }

    // Em caso de erro, é lançada uma exceção
    catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
        }
     }
}
