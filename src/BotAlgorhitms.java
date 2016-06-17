import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
        for(char c: charlist){
            builder.append(c);
        }
        out=builder.toString();
        return out;
    }

    /**
     * Versione inefficiente di sortWord (non usa StringBuilder)
     * @param in la parola da ordinare
     * @return la parola ordinata.
     */
    public static String sortWordSlow(String in){
        String out = "";
        char[] chars = in.toCharArray();
        ArrayList<Character> charlist = new ArrayList<Character>();
        for(char c: chars){
            charlist.add(c);
        }

        Collections.sort(charlist);
        for(char c: charlist){
            out=out+c;
        }
        return out;
    }

    /**
     * Versione errata di sortWord (introduce errori casuali)
     * @param in la parola da ordinare
     * @return la parola ordinata.
     */
    public static String sortWordErr(String in){
        String out = "";
        char[] chars = in.toCharArray();
        ArrayList<Character> charlist = new ArrayList<Character>();
        for(char c: chars){
            charlist.add(c);
        }

        Collections.sort(charlist);

        StringBuilder builder = new StringBuilder();
        for(char c: charlist){
            builder.append(c);
        }
        out=builder.toString();

        // introduce errore casuale
        int rand = new Random().nextInt(100);
        if(rand<=2){
            out=out.replace("a","x");
        }

        return out;
    }



    /**
     * Inverte le lettere di una parola.
     * @param in la parola in ingresso
     * @return la parola scritta al contrario.
     */
    public static String invertWord(String in){
        StringBuilder sb = new StringBuilder(in);
        return sb.reverse().toString();
    }

    /**
     * Inverte le lettere di una parola - versione inefficiente
     * @param in la parola in ingresso
     * @return la parola scritta al contrario.
     */
    public static String invertWordSlow(String in){
        String out = "";
        char[] chars = in.toCharArray();
        ArrayList<Character> charlist = new ArrayList<Character>();
        for(char c: chars){
            charlist.add(c);
        }

        for(int i=0; i<charlist.size(); i++){
            char c = charlist.get(charlist.size()-i-1);
            out = out+c;
        }

        return out;
    }

    /**
     * Versione errata di invertWord (introduce errori casuali)
     * @param in la parola in ingresso
     * @return la parola scritta al contrario.
     */
    public static String invertWordErr(String in){
        StringBuilder sb = new StringBuilder(in);
        String out=sb.reverse().toString();

        // introduce errore casuale
        int rand = new Random().nextInt(100);
        if(rand<=2){
            out=out.replace("a","x");
        }

        return out;
    }


    /**
     * Calcola la checksum di una parola.
     * Somma i valori di tutti i caratteri.
     * @param in la parola in ingresso
     * @return la checksum
     */
    public static int calcChecksum(String in){
        int checksum=0;
        char[] chars = in.toCharArray();
        for(char c: chars){
            checksum+=(int)c;
        }
        return checksum;
    }


    /**
     * Decripta una parola in base a una chiave data.
     * @param in la parola da decrittare
     * @param key la chiave
     * @return la parola decrittata.
     * La somma delle vocali contenute nella chiave dà un numero segreto.
     * Alle lettere in posizione pari va aggiunto il numero segreto
     * Alle lettere in posizione dispari va sottratto il numero segreto
     */
    public static String decryptWord(String in, String key){
        return in;
    }




}
