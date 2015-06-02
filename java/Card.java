public class Card
{
    String text;
    int id;

    public Card(String str, int num)
    {
        text = str;
        id = num;
    }

    public String getText()
    {
        return text;
    }
    
    public int getId() {
        return id;
    }
}
