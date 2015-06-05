import JSONObject.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Deck
{
    // instance variables - replace the example below with your own
    static ArrayList<Card> cards; //creates the static ArrayList Cards

    /**
     * Constructor for objects of class Deck
     * 
     * In this constructor, the JSON array is created, 
     * the file is read, the arraylist is created, and the cards are added
     */
    public Deck(String type)
    {
        JSONArray read = null;                                       //sets the JSON array to null.
        try {
            read = new JSONArray(readFile("../js/" + type + ".json"));  //reads the JSON file of the type passes in (it is either "answers" or "questions"
        } catch(Exception e) { e.printStackTrace(); }
        cards = new ArrayList<Card>(); 
        for(int i=0; i<read.getArrayList().size(); i++)
            cards.add(new Card((String) read.getArrayList().get(i), i));                                         //adds the card
    }

    /**
     * This method removes dealt cards from the cards list
     */
    public Card dealCard() {
        return cards.remove(0); //returns the removal of the card from the deck
    }

    /**
     * This method utilizes file reader to read the contents of a passed in fileName
     * and organize it into JSON format.
     */
    private String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));  //creates a new buffered reader br
        try {
            StringBuilder sb = new StringBuilder();                        //creates a new string builder
            String line = br.readLine();                                   //reads the string line

            while (line != null) {
                sb.append(line);                                           //append the string buffer to the  String "line"
                sb.append("\n");                                           //append the string buffer to create a new line
                line = br.readLine();                                      //sets the line equal to the buffer reader's readline
            }
            return sb.toString();                                          //returns the String
        } finally {
            br.close();                                                    //closes the buffer reader
        }
    }

}
