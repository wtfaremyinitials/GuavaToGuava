import java.util.ArrayList;
public class Player
{
    // instance variables - replace the example below with your own
    ArrayList<Card> playerCards;
    /**
     * Constructor for objects of class Game
     */
    public Player()
    {
        playerCards = new ArrayList<Card>();
    }

    public void takeCard(Card card){
        Deck.cards.add(card);
    }

    public void getPlayerCards(int playerID) {

    }

}
