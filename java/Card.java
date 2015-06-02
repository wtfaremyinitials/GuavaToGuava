public class Card
{
    String text; //the text of the card
    int id;      //the id of the card

    /**
     * The Card constructor
     * 
     * 
     */
    public Card(String str, int num)
    {
        text = str;  //set the car's text equal to the string
        id = num;    //set the ID equal to a passed in int
    }

    public String getText()
    {
        return text;
    }
    
    public int getId() {
        return id;
    }
}
