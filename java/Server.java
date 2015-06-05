//imports the JSON library as well as other java libraries
import JSONObject.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

//imports the Server Library
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

//imports HashMap and ArrayLsit
import java.util.HashMap;
import java.util.ArrayList;

/**
 * This is the server class, this runs and manages the creation of url's for the JSON
 * data involved in managaing the game,
 * 
 * @Brady Africk and Will Franzen
 * @1.0.0
 */
public class Server {
    static ArrayList<Game> games = new ArrayList<Game>();    //creates the games arraylist

    private static HttpServer httpserver;                    //creates the http server
    
    /**
     * This method initializes the server, on http://localhost:8000
     */
    public static void main(String[] args) throws Exception {
        games.add(new Game());                                              //creates an empty game to occupy the 0 slot. This is needed for the frond end
        httpserver = HttpServer.create(new InetSocketAddress(8000), 0);     //creates a new server at http://localhost:8000
        httpserver.createContext("/games/create", new CreateGame());        //sets the url to create a new game
        httpserver.createContext("/games", new GameArray());                //sets the url to see active games
        httpserver.createContext("/static", new ServeStatic());             //serve static resources to client 
        httpserver.setExecutor(null);                                       //creates a default executor
        httpserver.start();                                                 //starts the server
    }

    //returns the games and status
    static class CreateGame implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            Game g = new Game();                                                           //creates a new game "g"
            games.add(g);                                                                  //adds it to the arraylist
            String response = games.size()-1 + "";                                         //sets the reponse to the last item in the arraylist
            httpserver.createContext("/games/" + response + "/status", new GetStatus(g));  //creates the status url using the game ID
            t.sendResponseHeaders(200, response.length());                                 //send the response
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());                                                 //writes the reponse
            os.close();                                                                    //closes the response
        }
    }

    //converts the array list of games into a string and returns it to the server
    static class GameArray implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            JSONArray array = new JSONArray();                                             //creates a new JSONArray
            for (int x = 0; x < games.size(); x++){                                        //for loop to put an ID with each game
                array.put(x);
            }
            String response = array.toString();                                            //converts the array to string
            if (response == null || response.equals("")){ response = "null"; }             //checks if the response equals null
            t.sendResponseHeaders(200, response.length());                                 //send the response
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());                                                 //writes the reponse
            os.close();                                                                    //closes the response
        }
    }

    //converts the array list of games into a string and returns it to the server
    static class GetStatus implements HttpHandler {
        
        private Game game;                                                                         //creates a game
        
        public GetStatus(Game game) {
            this.game = game;                                                                      //sets the game equals this.game
        }
        
        public void handle(HttpExchange t) throws IOException {
            HashMap<String, String> hm = queryToMap(t.getRequestURI().getQuery());                 //creats a dual string HashMap
            int playerId = Integer.parseInt(hm.get("pid"));                                        //creates an int with an id of "pid"
            Player player = game.getPlayers().get(playerId);                                       //creates a player named player from the array of players  
            JSONArray cardsArray = new JSONArray();                                                //creates a JSONArray called cardsArray
            
            if(game.getCzar() != playerId) {                                                       //If the czar doesnt equal the playerID
                for(Card c : player.getCards()) {                                                  //for loop that goes through the players cards
                    cardsArray.put(c.getId());                                                     //puts the card in the array
                } 
            }    
                    
            JSONArray playersArray = new JSONArray();                                              //creates a JSON playersArray
            
            for(int i=0; i<game.getPlayers().size(); i++) {                                        //for loop that continues until i is less than the size of players     
                JSONObject p = new JSONObject();                                                   //creates a new JSONObject
                p.putOpt("name",  game.getPlayers().get(i).getName());                             //adds name to the name of the player
                p.putOpt("score", game.getPlayers().get(i).getScore());                            //puts score and null
                p.putOpt("czar",  i == game.getCzar());                                            //puts czar and null
                playersArray.put(p);                                                               //puts p in the player's array
            }
            
            JSONObject jsobj = new JSONObject();                                                   //creates a new jsobj
            jsobj.putOpt("players", playersArray);                                                 //puts players in the playersArray
            jsobj.putOpt("cards",   cardsArray);                                                   //puts players in the cardsArray
            String response = jsobj.toString();                                                    //converts the JSON object to string
            t.sendResponseHeaders(200, response.length());                                         //send the response
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());                                                         //writes the reponse
            os.close(); 
        }
    }
    
    // serves static resources to the client
    static class ServeStatic implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {   
            String url = t.getRequestURI().getQuery().getPath();                                   //sets the string url to get the url, querey, and path
            String fileName = url.substring(url.lastIndexOf('/')+1, url.length());                 //sets the filename to everything atfter a "/"                                       
            String response =  readFile("./" + fileName);                                          //reads the hidden folder containt a given filename            
            t.sendResponseHeaders(200, response.length());                                         
            OutputStream os = t.getResponseBody();                                                 //send the response
            os.write(response.getBytes());                                                         //writes the reponse             
            os.close();                                                                    
        }
    }

    public static HashMap<String, String> queryToMap(String query){              
        HashMap<String, String> result = new HashMap<String, String>();           //creates the Hashmap
        for (String param : query.split("&")) {                                   //for loop to split the querey 
            String pair[] = param.split("=");                                     //creates the array with the split param with "="
            if (pair.length>1) {
                result.put(pair[0], pair[1]);                                     //if the pair is longer than 1, put 0 and 1
            }else{
                result.put(pair[0], "");                                          //if the pair is anything else is longer than 1, put 0 and 1
            }
        }
        return result;                                                            //returns the result
    }
    
<<<<<<< HEAD
    public static String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");
=======
    private String readFile( String file ) throws IOException {
        BufferedReader reader = new BufferedReader( new FileReader (file));       //creates a new buffere reader
        String         line = null;                                               //sets a string line to null
        StringBuilder  stringBuilder = new StringBuilder();                       //creates a new string builder
        String         ls = System.getProperty("line.separator");                 //creates a line separator
>>>>>>> f2c2c463edced61d6a65a401220dec83721a7c27

        while( ( line = reader.readLine() ) != null ) {                           //while the line does not equal null...
            stringBuilder.append( line );                                         //append the string builder with the string line
            stringBuilder.append( ls );                                           //append the string builder with the line separator
        }

        return stringBuilder.toString();                                          //returns the string builer in the form of a string
    }

}