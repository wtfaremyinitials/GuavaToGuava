import JSONObject.JSONObject;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    ArrayList<Game> games = new ArrayList<Game>();
    
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/games/create", new CreateGame());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class CreateGame implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "HEY MOTHERFUCKER THIS SHIT WORKS OK?";
            JSONObject WHATEVER = new JSONObject();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}