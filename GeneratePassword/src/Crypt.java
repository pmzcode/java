import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by MSI on 21.02.2017.
 */
public class Crypt {
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\MSI\\Downloads\\J_lab\\Crypt\\src\\text.txt");

        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int symbol = bufferedReader.read();

        ArrayList<Character> text = new ArrayList<Character>();
        HashSet<Character> hs = new HashSet<Character>();

        while (symbol != -1) {
            char c = (char) symbol;
            symbol = bufferedReader.read();
            text.add(Character.toLowerCase(c));
        }

        for (int i = 0; i < text.size(); i++) {
            if((int)text.get(i) > (int)'а' && (int)text.get(i) < (int)'я')
                hs.add(text.get(i));
        }

        Map<Character, Integer> dictionary = new HashMap<Character, Integer>();
        Map<Character, Double> dictchast = new HashMap<Character, Double>();


        for (Character f:hs)
        {
            dictionary.put(f,(Collections.frequency(text,f)) );
        }

        for (Map.Entry<Character, Integer> entry:dictionary.entrySet())
        {
            Double chast = new Double(entry.getValue()) / new Double(text.size());
            dictchast.put(entry.getKey(),new Double(chast));
        }

        Map<Character, Double> dictEntropy = new HashMap<Character, Double>();;

        for (Map.Entry<Character, Double> entry:dictchast.entrySet())
        {
            dictEntropy.put(entry.getKey(),new Double(-Math.log((double)entry.getValue())));
        }

        List list = new ArrayList(dictEntropy.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character,Double>>() {
            @Override
            public int compare(Map.Entry<Character,Double> o1, Map.Entry<Character,Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        ArrayList<String> passwordSymbols = new ArrayList<String>();
        for (int i=0; i < list.size(); i++)
        {
            passwordSymbols.add(new String(list.get(i).toString().substring(0,1)));
        }

        System.out.println("Password:" + generatePassword(passwordSymbols));

    }

    public static String generatePassword (ArrayList<String> symbols) {
        String str = new String("");
        for (int i = 0; i < 10; i++) {
            int a;
            a = (int)(Math.random()*11);
            str += symbols.get(a);
        }
        return str;
    }
}
