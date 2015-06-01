import JSONObject.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    static HashMap<String,Game> games = new HashMap<String,Game>();

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/games/create", new CreateGame());
        server.createContext("/games", new GameArray());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    //converts the gameID into a string and returns it
    static class CreateGame implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            //int gameID = Game.getID();
           // String response = gameID + ""; 
            //games.put(response, new Game());
            //t.sendResponseHeaders(200, response.length());
           // OutputStream os = t.getResponseBody();
            //os.write(response.getBytes());
           // os.close();
        }
    }

    
    //converts the array list of games into a string and returns it to the server
    
    //Brady if you are feeling especially self-loathing, manually create JSON
    static class GameArray implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = new JSONArray(games.keySet()).toString(); 
            System.out.print(games.keySet());
            if (response == null || response.equals("")){ response = "null"; }
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}