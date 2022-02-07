package Miner;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class StringHashing {

    //Применяет алгоритм хеширования sha256 к заданной строке
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuffer hexString = new StringBuffer(); // Буферная переменная для содержания нашего конечного хеша
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Возвращает строку с заданной сложностью, если сложность 5 - вернет 00000
    public static String getDificultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

}

