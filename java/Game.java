import java.util.ArrayList; //importing ArrayList
public class Game
{
    ArrayList<Player> players = new ArrayList<Player>(); //initializes the player ArrayList
    Deck answerDeck; //creates the answer deck
    Deck questionDeck; //creates the question deck
    Card currentQuestion; //creates the current question card
    int czar = -1; //sets the czar index to -1 because it is increased to 0 immediately
    
    /**
     * Constructor for objects of class Game
     * 
     * This creates the new answer deck, creates the question deck,
     * deals the cards, sets the current question, and rotates the czar.
     */
    public Game()
    {
        answerDeck = new Deck("answers");          //creates the answers deck
        questionDeck = new Deck("questions");
        dealCards();
        currentQuestion = questionDeck.dealCard();
        rotateCzar();
    }
    
    /**
     * This int returns the czar int because it is private
     */
    public int getCzar(){
        return czar;
    }
    
    /**
     * This int rotates the czar.
     * If it is equal to the last index,
     * it resets it to zero.
     */
    private int rotateCzar(){
        if (czar == players.size()-1){
          czar = 0;
        }
        else {
          czar++;  
        }
        players.get(czar);
        return czar;
    }
    
    /**
     * This is the dealCards method
     * 
     * This method deals cards to each player 
     * using a for each loop to go through the
     * players and a regular for loop to set 
     * the deal limit to 5 cards
     */
    private void dealCards()
    {
        for (Player p: players){
            for (int x = 0; x<5; x++){
              p.takeCard(answerDeck.dealCard());
            }
        }
    }
    
     /**
     * This is the addPlayer method
     * 
     * This adds a player to a given 
     * game and with a given playerID
     */
    private void addPlayer(int gameID, int playerID){
        players.add(new Player());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }


}
