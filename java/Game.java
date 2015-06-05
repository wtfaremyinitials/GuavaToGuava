import java.util.ArrayList; //importing ArrayList
import java.util.Map;
public class Game
{
    ArrayList<Player> players = new ArrayList<Player>(); //initializes the player ArrayList
    Deck answerDeck; //creates the answer deck
    Deck questionDeck; //creates the question deck
    Card currentQuestion; //creates the current question card
    int czar = -1; //sets the czar index to -1 because it is increased to 0 immediately
    Card[] selections;

    /**
     * Constructor for objects of class Game
     * 
     * This creates the new answer deck, creates the question deck,
     * deals the cards, sets the current question, and rotates the czar.
     */
    public Game()
    {
        answerDeck = new Deck("answers");          //creates the answers deck
        questionDeck = new Deck("questions");      //creates the questions deck
        currentQuestion = questionDeck.dealCard(); //sets the current question equal to the next card                              //rotates the czar
        selections = new Card[3];
    }
    
    /**
     * This int returns the czar int because it is private
     */
    public int getCzar(){
        return czar;                               //returns the czar
    }
    
    /**
     * This int rotates the czar.
     * If it is equal to the last index,
     * it resets it to zero.
     */
    private int rotateCzar(){
        if(players.size() == 0)
            return -1;
        if (czar == players.size()-1){            //checks if the current czar is the final player
          czar = 0;                               //if so, the czar is set to zero
        }
        else {
          czar++;                                 //if not, the czar is moved to the next player
        }
        players.get(czar);                        //returns the czar
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
            for (int x = 0; x<5; x++){           //for loop for five cards
                dealCard(); //deals the cards
            }
        
    }
    
    private void dealCard()
    {
        for (Player p : players){                 //goes through each player
              p.takeCard(answerDeck.dealCard()); //deals the cards
            
        }
    }
    
     /**
     * This is the addPlayer method
     * 
     * This adds a player to a given 
     * game and with a given playerID
     */
    public void addPlayer(Player p){
        players.add(p);                          //adds a new player
        if(players.size() == 3) {
            dealCards();
            rotateCzar();
            changeQuestion();
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;                          //returns the players
    }
    
    public boolean getIsReadyForCzar() {
        int missing = 0;
        for(Card c : selections)
            if(c == null)
                missing++;
        return missing == 1 || missing == 0;
    }


    public Card[] getSelections() {
        return selections;
    }
    
    public void selectCard(int pid, int cid) {
        selections[pid] = new Card("8==D", cid);
    }
    
    public boolean hasChosen(int pid) {
        return selections[pid] != null;
        
    }
    
    public void selectWinner(int cid) {
        for(int i=0; i<selections.length; i++) {
            if(selections[i] != null && selections[i].getId() == cid) {
                players.get(i).addPoint();
            }
        }
        selections = new Card[players.size()];
        dealCard();
        rotateCzar();
        changeQuestion();
    }

    private void changeQuestion() {
        currentQuestion = questionDeck.dealCard();
    }
    
    public Card getCurrentQuestion() {
        return currentQuestion;
    }
}
