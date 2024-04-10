import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class HashEncryptor {

    // Metódus az üzenet titkosítására a megadott hash algoritmus segítségével
    private static String hashEncrypt(String message, String algorithm) {
        try {
            // MessageDigest példány létrehozása a megadott algoritmussal
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            // Üzenet bájtsorának titkosítása
            byte[] encodedHash = digest.digest(message.getBytes());

            // Titkosított hash átalakítása hexadecimális stringgé
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                // Hexadecimális string hozzáadása a hexString-hez
                hexString.append(hex.length() == 1 ? "x" + hex : hex);
            }
            return hexString.toString(); // Titkosított üzenet visszaadása
        } catch (NoSuchAlgorithmException e) { // Algoritmus hibakezelése
            e.printStackTrace();
            return null;
        }
    }

    // Metódus felhasználói bemenet fogadására a megadott prompt alapján
    private static String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine(); // Felhasználó által megadott válasz visszaadása
    }

    public static void main(String[] args) {
        // Felhasználói üzenet bekérése
        String message = getUserInput("Írja be a titkosítani kívánt üzenetet: ");
        String algorithm = "MD5"; // Alapértelmezett hash algoritmus

        // Üzenet titkosítása és kiíratása
        String encryptedMessage = hashEncrypt(message, algorithm);
        if (encryptedMessage != null) {
            System.out.println("Üzenet titkosítva: " + encryptedMessage);
        } else {
            System.out.println("Nem sikerült a titkosítás.");
        }
    }
}
