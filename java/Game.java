import java.util.ArrayList;

public class Game
{
    ArrayList<Player> players = new ArrayList<Player>();
    Deck answerDeck;
    Deck questionDeck;
    Card currentQuestion;
    int czar = -1;
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        answerDeck = new Deck("answers");
        questionDeck = new Deck("questions");
        dealCards();
        currentQuestion = questionDeck.dealCard();
        rotateCzar();
    }
    
    public void rotateCzar(){
        if (czar == players.size()-1){
          czar = 0;
        }
        else {
          czar++;  
        }
    }
    
    public void dealCards()
    {
        for (Player p: players){
            for (int x = 0; x<5; x++){
              p.takeCard(answerDeck.dealCard());
            }
        }
    }
    
    public void addPlayer(int gameID, int playerID){
        players.add(new Player());
    }


}
