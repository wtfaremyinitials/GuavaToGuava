import JSONObject.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    static ArrayList<String> games = new ArrayList<String>();

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
            int gameID = Game.getID();
            String response = new Integer(gameID).toString();
            games.add(new Integer(gameID).toString());
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    
    //converts the array list of games into a string and returns it to the server
    static class GameArray implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String gameIDs = "";
            for (String str : games)
            {
                gameIDs += str + "\n";
            }
            String response = gameIDs;  
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}