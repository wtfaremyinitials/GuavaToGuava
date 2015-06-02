import JSONObject.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Deck
{
    // instance variables - replace the example below with your own
    static ArrayList<Card> cards;

    /**
     * Constructor for objects of class Deck
     */
    public Deck(String type)
    {
        JSONArray read = null;
        try {
            read = new JSONArray(readFile("../" + type + ".json"));
        } catch(Exception e) { e.printStackTrace(); }
        cards = new ArrayList<Card>();
        for(Object o : read.getArrayList())
            cards.add(null);
    }

    public Card dealCard() {
        return cards.remove(0);
    }

    private String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

}
