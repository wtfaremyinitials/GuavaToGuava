import java.util.ArrayList;
public class Player
{
    // instance variables - replace the example below with your own
    ArrayList<Card> playerCards;
    String name;
    /**
     * Constructor for objects of class Game
     */
    public Player(String name)
    {
        playerCards = new ArrayList<Card>();
        this.name = name;
    }

    public void takeCard(Card card){
        playerCards.add(card);
    }

    public ArrayList<Card> getCards() {
        return playerCards;
    }
    
    public String getName() {
        return name;
    }

}
