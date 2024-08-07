import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // test case
        String[] test = new String[]{"Pendanaan", "Terproteksi", "Untuk", "Dampak", "Berarti"};
        System.out.println(charGrouping(test));
    }
    public static String charGrouping(String[] words){
        // deklarasi HashMap dengan nama groupedChar dengan key bertipe Character dan value bertipe Integer
        HashMap<Character, Integer> groupedChar = new HashMap<Character, Integer>();
        // menghitung banyak kata dalam array of string yang didapat dari parameter
        int wordCount = words.length;
        // deklarasi variabel dengan nama val untuk index value yang akan dimasukan pada HashMap
        int val;
        // looping untuk memasukan setiap karakter pada string ke dalam hashmap
        for(int i = 0; i < wordCount; i++){
            // menghitung banyak character dalam string
            int charCount = words[i].length();
            // looping sepanjang banyak karakter dalam string
            for(int j = 0; j < charCount; j++){
                // value diisi nilai 0 sebagai default jika key belum ada dalam hashmap
                // sedangkan jika key sudah ada maka valuenya disimpan pada variabel val
                val = groupedChar.getOrDefault(words[i].charAt(j), 0);
                // memasukan karakter beserta valuenya ke dalam hashmap
                groupedChar.put(words[i].charAt(j), val+1);
            }
        }

        // sorting HashMap menggunakan built in method Collections
        // mengambil entri (key-value) dari HashMap groupedChar dan memulai stream
        HashMap<Character, Integer> sortedChar = groupedChar.entrySet().stream()
                // sorting entri berdasarkan value (Integer) secara menurun
                .sorted(Map.Entry.<Character, Integer>comparingByValue(Comparator.reverseOrder())
                        // Jika ada entri dengan value yang sama, maka diurutkan berdasarkan nilai ASCII key (Character)
                        .thenComparing(Map.Entry.comparingByKey()))
                // mengumpulkan hasil stream pada LinkedHashMap
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        // hasil akhir berupa LinkedHashMap untuk mempertahankan urutan
                        (e1, e2) -> e1, LinkedHashMap::new));

        // deklarasi StringBuilder
        StringBuilder result = new StringBuilder();
        for (Character key : sortedChar.keySet()) {
            // memasukkan setiap key pada StringBuilder secara berurutan
            result.append(key);
        }

        // return StringBuilder sebagai String
        return new String(result);
    }
}