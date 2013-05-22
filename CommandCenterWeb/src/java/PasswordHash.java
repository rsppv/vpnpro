
import compute.Task;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author aipova
 */
public class PasswordHash implements Task<String>, Serializable {

    private static final long serialVersionUID = 327L;
    public String hash;
    public String alphabet;
    public int symbolCount;

    
    int startId;
    int maxId;

    public PasswordHash(String hash, String alphabet, int startId, int maxId, int symbolCount) {
        this.hash = hash;
        this.alphabet = alphabet;
        this.startId = startId;
        this.maxId = maxId;
        this.symbolCount = symbolCount;
    }
    
    public PasswordHash(String hash, String alphabet) {
        this.hash = hash;
        this.alphabet = alphabet;
    }
    public int getSymbolCount() {
        return symbolCount;
    }

    public void setSymbolCount(int symbolCount) {
        this.symbolCount = symbolCount;
    }
    public void setInterval(int start, int end) {
        this.startId = start;
        this.maxId = end;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getMaxId() {
        return maxId;
    }

    public void setMaxId(int maxId) {
        this.maxId = maxId;
    }

    public int getStartId() {
        return startId;
    }

    public void setStartId(int startId) {
        this.startId = startId;
    }

    @Override
    public String execute() {
        System.out.println("Hash task recieved!");
        System.out.println("Executing task...");
        String password;
        String newHash;
        if (maxId > (int) Math.pow(alphabet.length(), symbolCount)) {
            maxId = (int) Math.pow(alphabet.length(), symbolCount);
        }
        System.out.println("Scanning from " + startId + " to " + maxId + " of " + Math.pow(alphabet.length(), symbolCount)); 
        for (int i = startId; i <= maxId; i++) {
            password = generateString(i);
            MessageDigest m = null;
            try {
                m = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
            }

            m.update(password.getBytes(), 0, password.length());
            newHash = new BigInteger(1, m.digest()).toString(16);
            if (newHash.equals(hash)) {
                System.out.println("task complete!");
                return password;
            }
        }
        System.out.println("task complete!");
        return "";
    }

    public String generateString(int number) {
        int base = alphabet.length();
        String result = "";
        while (number > 0) {
            result = alphabet.charAt(number % base) + result;
            number = (int) Math.floor(number / base);
        }
        return result;
    }
}
