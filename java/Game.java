import java.util.ArrayList;

public class Game
{
    ArrayList<Player> players = new ArrayList<Player>();
    Deck deck;
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        deck = new Deck("answers");
        dealCards();
    }
    
    public void dealCards()
    {
        for (Player p: players){
            for (int x = 0; x<5; x++){
              p.takeCard(deck.dealCard());
            }
        }
    }
    
    public void addPlayer(int gameID, int playerID){
        players.add(new Player());
    }


}
