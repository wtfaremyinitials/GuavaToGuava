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
    static ArrayList<Game> games = new ArrayList<Game>();

    private static HttpServer httpserver;

    public static void main(String[] args) throws Exception {
        games.add(new Game());
        httpserver = HttpServer.create(new InetSocketAddress(8000), 0);
        httpserver.createContext("/games/create", new CreateGame());
        httpserver.createContext("/games", new GameArray());
        httpserver.setExecutor(null); // creates a default executor
        httpserver.start();
    }

    //converts the gameID into a string and returns it
    static class CreateGame implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            games.add(new Game());
            String response = games.size()-1 + "";
            httpserver.createContext("/games/" + response + "/status", new GetStatus());
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    //converts the array list of games into a string and returns it to the server
    static class GameArray implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            JSONArray array = new JSONArray();
            for (int x = 0; x < games.size(); x++){
                array.put(x);
            }
            String response = array.toString(); 
            if (response == null || response.equals("")){ response = "null"; }
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    //converts the array list of games into a string and returns it to the server
    static class GetStatus implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            HashMap<String, String> hm = queryToMap(t.getRequestURI().getQuery());
            int playerId = Integer.parseInt(hm.get("pid"));
            String response = "";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
            
            
        }
    }

    public static HashMap<String, String> queryToMap(String query){
        HashMap<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

}