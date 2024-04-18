import java.util.HashMap;
import java.util.Map;

public class RotorCipher {
    // Rotorokat tároló adatstruktúra
    private Map<Character, Character> rotor1;
    private Map<Character, Character> rotor2;
    private Map<Character, Character> rotor3;

    // Konstruktor inicializálja a rotort
    public RotorCipher() {
        // Rotorok inicializálása
        rotor1 = createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        rotor2 = createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "AJDKSIRUXBLHWTMCQGZNPYFVOE");
        rotor3 = createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "BDFHJLCPRTXVZNYEIWGAKMUSQO");
    }

    // Segédmetódus a rotorok létrehozásához
    private Map<Character, Character> createRotor(String input, String output) {
        Map<Character, Character> rotor = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            rotor.put(input.charAt(i), output.charAt(i));
        }
        return rotor;
    }

    // Metódus egy karakter titkosításához
    private char encryptChar(char ch) {
        char encryptedChar = rotor1.get(ch);
        encryptedChar = rotor2.get(encryptedChar);
        encryptedChar = rotor3.get(encryptedChar);
        return encryptedChar;
    }

    // Metódus egy karakter visszafejtéséhez
    private char decryptChar(char ch) {
        char decryptedChar = getKeyByValue(rotor3, ch);
        decryptedChar = getKeyByValue(rotor2, decryptedChar);
        decryptedChar = getKeyByValue(rotor1, decryptedChar);
        return decryptedChar;
    }

    // Segédmetódus: adott érték alapján kulcs lekérdezése a Map-ből
    private char getKeyByValue(Map<Character, Character> map, char value) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return '\0'; // Ha nem találunk értéket, visszaadjuk a null karaktert
    }

    // Metódus a szöveg titkosításához
    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        // A szöveg minden karakterén végigmegyünk
        for (char ch : plaintext.toUpperCase().toCharArray()) {
            // Csak betűket titkosítunk, a többit változatlanul hagyjuk
            if (Character.isLetter(ch)) {
                ciphertext.append(encryptChar(ch)); // Egy karakter titkosítása
            } else {
                ciphertext.append(ch); // Nem betű karakterek változatlanul maradnak
            }
        }
        return ciphertext.toString();
    }

    // Metódus a szöveg visszafejtéséhez
    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        // A titkosított szövegen végigmegyünk
        for (char ch : ciphertext.toUpperCase().toCharArray()) {
            // Csak betűket visszafejtünk, a többit változatlanul hagyjuk
            if (Character.isLetter(ch)) {
                plaintext.append(decryptChar(ch)); // Egy karakter visszafejtése
            } else {
                plaintext.append(ch); // Nem betű karakterek változatlanul maradnak
            }
        }
        return plaintext.toString();
    }

    // A teszteléshez használt main metódus
    public static void main(String[] args) {
        RotorCipher rotorCipher = new RotorCipher();

        String plaintext = "HELLO";
        String encryptedText = rotorCipher.encrypt(plaintext);
        String decryptedText = rotorCipher.decrypt(encryptedText);

        System.out.println("Plain text: " + plaintext);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
