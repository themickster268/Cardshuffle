
//A class for creating card objects
public class Card {

    //Private String variables to store the suit and rank
    private String rank;
    private String suit;


    //The constructor for a card object
    public Card(String rank, String suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    //Overridden toString method
    public String toString()
    {
        //Example "Ace of Diamonds"
        return rank + " of " + suit;
    }


}
