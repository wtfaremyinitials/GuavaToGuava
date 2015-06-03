public class Card
{
    String text; //the text of the card
    int id;      //the id of the card

    /**
     * The Card constructor
     * 
     * It creates the cards text and it's ID
     */
    public Card(String str, int num)
    {
        text = str;  //set the car's text equal to the string
        id = num;    //set the ID equal to a passed in int
    }

    /**
     * This string returns the text of the card
     */
    public String getText()
    {
        return text; //returns the text
    }
    
    /**
     * This int returns the id of the card
     */
    public int getId() {
        return id; //returns the ID
    }
}
