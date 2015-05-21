import java.util.ArrayList;

public class Game
{
    // instance variables - replace the example below with your own
    static int gameID = 999;
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Cards> gameCards = new ArrayList<Cards>();
    ArrayList<Cards> playerCards = new ArrayList<Cards>();
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
       
        
        

    }
    
    static public int getID(){
        gameID++;
        return gameID;
        
    }

}
