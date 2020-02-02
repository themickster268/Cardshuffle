public interface DeckInterface<T> {

    //Finds the position of a specified card in the deck
    //Returns the position of the card in the deck
    public int find(T aCard);

    //Performs a single iteration of the inShuffle
    public void inShuffle( );

    //Performs a single iteration of the outShuffle
    public void outShuffle( );

    //Returns the suit and rank of the top card of the deck
    public T reveal();

    //Returns the suit and rank of all the cards in the deck
    public  T[] show();

    //Performs a single iteration of a random shuffle
    public void shuffle();
}
