public class DeckTest {

    //Main Method
    public static void main(String[] args)
    {
        testingInterface();
    }


    private static void testingInterface(){

        String[] exampleDeck = {"10 of Clubs",
                "9 of Diamonds",
                "8 of Hearts",
                "7 of Spades",
                "6 of Clubs",
                "5 of Diamonds",
                "4 of Hearts","3 of Clubs"};

        DeckInterface<String> testDeck = new Deck<>(exampleDeck);

        //1. Testing the SHUFFLE method along with the SHOW method
        testDeck.shuffle();
        System.out.println("1. Below is the deck after testing the SHOW function:"  );
        displayDeck(testDeck.show());
        System.out.println("\n");


        //2. Testing the REVEAL method
        System.out.println("2. Below is the top card of the deck after a Random Shuffle: ");
        System.out.println(testDeck.reveal());
        System.out.println("\n");

        //3.Testing the IN-SHUFFLE method
        testDeck.inShuffle();
        System.out.println("3. Below is the deck after the in-Shuffle: ");
        displayDeck(testDeck.show());
        System.out.println("\n");

        //4.Testing the OUT-SHUFFLE method
        testDeck.outShuffle();
        System.out.println("4. Below is the deck after the out-Shuffle: ");
        displayDeck(testDeck.show());
        System.out.println("\n");

        //5. Testing the FIND method using an existing card from the deck
        System.out.println("5. Testing the position for an existing card (10 of Clubs): " + testDeck.find("10 of Clubs") + "\n");

        //6. Testing the FIND method using a card not present in the deck e.g. Queen of Diamonds
        System.out.println("6. Testing the position for a non-existing card (Queen of Diamonds): " + testDeck.find("Queen of Diamonds") + "\n");

    }

    //Prints out the deck of cards
    //Based on getString method in previous ArrayUtil class
    private static <T> void displayDeck(T[] aDeck){

        //The position of the first card
        int position = 1;//Change: including int variable to indicate position of card objects

        //The opening bracket
        String result = new String("[");

        //Loop through all but the last item in the deck
        for (int i = 0 ; i  < aDeck.length - 1; i++){

            //Add the deck item to the result String
            //Change: including "1. cardObject," string for each card object
            result = result + position + ". " + aDeck[i] + ", ";

            //Increment the position
            position++;
        }

        //Add the last item to the String along with the closing bracket
        //Change: including "52. last card Object]"
        result = result + position + ". "+  aDeck[aDeck.length - 1] +  "]";

        //Print out the result String
        System.out.println(result);
    }

}
