import java.util.Random;

public final class Deck<T> implements DeckInterface<T> {

    //private variables
    private  T[] deck;
    private final int numberOfCards;

    private boolean initialised;
    private static final int MAXIMUM_NO_OF_CARDS = 52;

    //Reason for choosing array-based implementation to store the deck of cards
    //Adding items to an array is fast
    //Limited capacity for the deck of cards
    //Requires less memory than a linked chain
    //Arrays are faster at accessing elements

    //Parameterised constructor that takes an array of objects
    //as an input parameter
    public Deck(T[] inputDeck)
    {
        //Checks that the no of objects in inputDeck does not exceed 52
        if(inputDeck.length <= MAXIMUM_NO_OF_CARDS)
        {
            //Assign the inputDeck to the deck array
            //set the number of cards
            deck = inputDeck;
            numberOfCards = deck.length;

            initialised = true;
        }else
            {
                throw new IllegalStateException("The number of cards must not exceed 52!");
            }

    }

    //Finds the position of a specified card
    //Either linear search or binary search
    //Based iterative linear search "Data structures ans abstractions with java"

    public  int find(T aCard) {//Change: Changed the return type from boolean to int

        checkInitialisation();

        //Position  variable declared
        //No need for found variable
        //As the !found condition in the while loop will always be true
        int position = 0;

        //While the array index of position is less than the index of last card

        while(position < numberOfCards ) //Change: removed !found condition
        {

            if(aCard.equals(deck[position])){

                //If the found variable is set to true it is never used
                //As we are returning the position in the if condition

                //Return the position
                return position + 1; //Change: returning position in deck instead of boolean value
            }

            position++;
        }

        //Return minus 1 if an item is not found
        return -1;
    }


    //Problem: when performing either the inShuffle or outShuffle
    //Certain cards where being duplicated and some card where missing from the
    //Shuffled deck
    //Solution: Removal of the base case and the two Recursive calls
    //Changing the assignment statement from 'deck[first + i] = tempDeck[i]' to 'deck[i] = tempDeck[i]'
    //in the for loop at the end of the merge method

    //Performs a single iteration of the in-shuffle
    //Performing the inShuffle on the DeckADT so no input parameters are needed
    public void inShuffle( ) {

        checkInitialisation();

        //call the private inShuffle method

        //Calling private inShuffle helper method using array indices of the first and last card
        inShuffle(deck, 0 , numberOfCards -1  );
    }


    private  void inShuffle(T[] deck, int first, int last){


        //Checking to see if the deck has even number of cards
        //Cannot perform riffle-Shuffles on deck with odd number of cards

        if(numberOfCards % 2 == 0) //Change removed base case "first < last"
        {
            //Create the temporary deck
            @SuppressWarnings("Unchecked")
            T[] tempDeck = (T[]) new Object[numberOfCards];


            //Find the approximate middle index
            int midIndex = first + (last - first) / 2;


            //Change: removed the two recursive calls

            //Merge the two halves using inShuffle
            mergeInShuffle( deck,  tempDeck,  first,  midIndex,  last);
        }else
        {
            throw new IllegalStateException("Error! Can only perform in-Shuffle on equal halves of cards.");
        }

    }


    //Based on merge helper method from merge sort
    //Data structures and abstractions with java
    private void mergeInShuffle(T[] deck, T[] tempDeck, int first, int midIndex, int last){

        //The indices of the first and last card of the first half of the deck
        int beginFirstHalf = first;
        int endFirstHalf = midIndex;

        //The indices of the first and last card of the second half of the
        int beginSecondHalf = midIndex + 1;
        int endSecondHalf = last;

        //Index variable to retrieve all the cards stored in the temporary deck
        int index = 0;

        //While the index of the first card is less than or equal to the index of the last
        //card in both halves of the deck
        while (beginFirstHalf <= endFirstHalf && beginSecondHalf <= endSecondHalf){

            //change: interleaving card objects from each halve when the
            //While condition is true

            //Place card from second halve in first
            tempDeck[index] = deck[beginSecondHalf];
            beginSecondHalf++;
            index++;


            //Then place the card from the first halve
            tempDeck[index] = deck[beginFirstHalf];
            beginFirstHalf++;
            index++;
        }

        //Copy the cards from the tempDeck to the deck
        for(int i = 0; i < index; i++)
        {
            deck[i] = tempDeck[i];
        }


    }


    //Performs a single iteration of the out-shuffle
    public void outShuffle( ) {

        checkInitialisation();

        //Calls the private outShuffle method using indices of first and last cards
        outShuffle( deck, 0 , numberOfCards - 1 );

    }

    private void outShuffle(T[] deck , int first, int last){

        //Checking to see if the deck has even number of cards
        //Cannot perform riffle-Shuffles on deck with odd number of cards

        if(numberOfCards % 2 == 0)//Change removed base case "first < last"
        {
            //Create the temporary deck
            @SuppressWarnings("Unchecked")
            T[] tempDeck = (T[]) new Object[numberOfCards];


            //Find the approximate middle index
            int midIndex = first + (last - first) / 2;

            //Change: removed the two recursive calls

            //Merge the two halves using inShuffle
            mergeOutShuffle( deck,  tempDeck,  first,  midIndex,  last);
        }else
        {
            throw new IllegalStateException("Error! Can only perform out-Shuffle on equal halves of cards.");
        }

    }


    //Based on merge method from merge sort
    //Data structures and abstractions with java
    private void mergeOutShuffle(T[]deck, T[] tempDeck, int first, int midIndex, int last){

        //Indices of the first and last cards in the first half
        int beginFirstHalf = first;
        int endFirstHalf = midIndex;

        //Indices of the first and last card in the second half of the deck
        int beginSecondHalf = midIndex + 1;
        int endSecondHalf = last;

        //Index variable to copy the entries from the temporary deck to th original deck
        int index = 0;

        //While the index of the first card is less than or equal to the index of the last card
        //in both halves of the deck
        while(beginFirstHalf <= endFirstHalf && beginSecondHalf <= endSecondHalf){


            //change: interleaving card objects from each halve when the
            //While condition is true

            //place the card from the first halve in first
            tempDeck[index] = deck[beginFirstHalf];
            beginFirstHalf++;
            index++;

            //Then  place the card from the second halve in
            tempDeck[index] = deck[beginSecondHalf];
            beginSecondHalf++;
            index++;
        }

        //Copy the cards from the tempDeck to the deck
        for(int i = 0; i < index; i++)
        {
            deck[i] = tempDeck[i];
        }
    }

    //Return the top card of the deck
    public T reveal() {

        checkInitialisation();

        //The position of the top card in the deck is at position 0
        //Array-based implementation so the first item is at position 0
        T topCard = deck[0];


        //Return the top card of the deck
        return topCard;
    }



    //Returns the an array of items
    public T[] show(){

        //Check of the deck has been initialised
        checkInitialisation();

        //Create the resulting Array
        @SuppressWarnings("Unchecked")
        T[] result = (T[])new Object[numberOfCards];

        //Loop through the deck of cards
        for(int i = 0; i < numberOfCards; i++)
        {
            //Copy from the deck to the resulting array at index i
            result[i] = deck[i];
        }

        //Return the array
        return result;
    }

    //Performs a random shuffle on a deck of cards
    public void shuffle( ){

        checkInitialisation();

        //Create a new instance of the random class
        Random randomGenerator = new Random();


        //Loop through the deck of cards
        for(int i = 0; i < numberOfCards; i++){

            //Produce a random index between 0 and index of the last card
            int randomInt = randomGenerator.nextInt(numberOfCards);

            //Swap the card at the random position
            //with the card at position i
            T temp = deck[randomInt];
            deck[randomInt] = deck[i];
            deck[i] = temp;
        }
    }

    //Checks initialisation
    private void checkInitialisation(){

        if(!initialised)
            throw new SecurityException("Warning, the deck of cards has not been properly initialised!");
    }
}