import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by alex on 15/06/16.
 */
public class BotAlgorhitms {

    /**
     * Ordina alfabeticamente le lettere di una parola.
     * @param in la parola da ordinare
     * @return la parola ordinata.
     */
    public static String sortWord(String in){
        String out = "";
        char[] chars = in.toCharArray();
        ArrayList<Character> charlist = new ArrayList<Character>();
        for(char c: chars){
            charlist.add(c);
        }

        Collections.sort(charlist);

        StringBuilder builder = new StringBuilder();
        for(char c: chars){
            builder.append(c);
        }
        out=builder.toString();
        return out;
    }

}