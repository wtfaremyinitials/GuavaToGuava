import JSONObject.*;
import java.util.ArrayList;
/**
 * Write a description of class Deck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Deck
{
    // instance variables - replace the example below with your own
    static ArrayList<Card> cards;

    /**
     * Constructor for objects of class Deck
     */
    public Deck()
    {
        //cards = new JSONArray();

    }
    
    public Card dealCard() {
        return cards.remove(0);
    }

}
