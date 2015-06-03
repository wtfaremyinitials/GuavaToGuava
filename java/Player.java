import java.util.ArrayList;
public class Player
{
    // instance variables - replace the example below with your own
    ArrayList<Card> playerCards;  //creates the arrayList for the player's hand
    String name;                  //The string for the name of the player
    /**
     * Constructor for objects of class Player
     * 
     * This contructor creates the players hand and set's their name
     */
    public Player(String name)
    {
        playerCards = new ArrayList<Card>();  //creates a new array list for the player's hand
        this.name = name;                     //sets the name equal to the passed in name
    }

    /**
     * This method adds a taken card to the player's hand
     */
    public void takeCard(Card card){
        playerCards.add(card);     //adds a card to the player's hand
    }

    /**
     * This method returns the cards a player has in their hand.
     */
    public ArrayList<Card> getCards() {
        return playerCards;        //this method returns the player's cards
    }
    
    /**
     * This method returns the name of a player
     */
    public String getName() {
        return name; //returns the name
    }

}
