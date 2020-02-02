import java.util.Scanner;

public class CardForceClient {

    //Main method
    public static void main(String[] args){

        //Calls Card force simulation method
        cardForceSimulation();
    }

    public static void cardForceSimulation(){

        //1. Create an instance of a deck of cards
        System.out.println("Step 1. Creating the deck of cards...\n");

        //The new deck takes in the String of cards as an input parameter
        DeckInterface<Card> myDeck = new Deck<>(makeDeck());

        //Reasons for passing the deck of cards as an input parameter when instantiating
        //an object of the ADT deck
        //An 'add' method is not one of the operations of the abstract data type
        //No need to add all 52 cards to the ADT deck manually


        //2. Display the current state of the deck using the SHOW operation
        System.out.println("Step 2. Showing the initial state of the deck:" );
        displayDeck(myDeck.show());
        System.out.println("\n");


        //3. Randomly shuffle the deck using the  SHUFFLE operation,
        //then display the state of the card using the SHOW operation
        System.out.println("Step 3. Showing the state of the deck after random shuffle:");
        myDeck.shuffle();
        displayDeck(myDeck.show());
        System.out.println("\n");

        //4. Show the top card of the deck using the REVEAL operation
        System.out.println("Step 4. Below is the top card of the deck:");
        Card topCard = myDeck.reveal();
        System.out.println(topCard);
        System.out.println("\n");

        //5. Perform the card trick (ask user for input), then
        //display the state of the deck using the SHOW operation
        int position = getPosition("Step 5. Please enter the position you would like to move the card to: \n ");
        String binaryPosition = convertToBinary(position - 1 );
        cardForce(myDeck, binaryPosition);

        System.out.println("Below is the state of the deck after the Card-Force trick was performed:");
        displayDeck(myDeck.show());
        System.out.println("\n");



        //6. Verify that the card is at the specified position using the
        //FIND operation
        System.out.println("Step 6. Verifying the card is in the specified position: "  + myDeck.find(topCard));



    }

    //Get the position the user wants to move the top card to
    //Based on getInt method from SortTestInstrumented class
    private static int getPosition(String positionPrompt){


        //Default position
        int result = 26; //Change: use 26 as the default position to move the card to

        //Try-catch block
        try{

            //Scanner object
            Scanner in = new Scanner(System.in);

            //Print out the prompt
            System.out.println(positionPrompt);

            //Get user input
            result = in.nextInt();

            //Input validation to check if the number entered is within range
            //Change: included while condition below
            while (result < 1 || result >  52){

                //Print out an error message
                System.out.println("Please enter a number Between 1 and 52");

                //Get user input
                result = in.nextInt();
            }
            //Catch when user doesn't enter a number
        }catch (NumberFormatException e){

            System.out.println("Warning: could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Warning: will use 26 as the default value\n");

            //Catch any other exception
        }catch (Exception e){

            System.out.println("Warning: there was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Warning: will use 26 as the default value\n");

        }



        return result;
    }




    //Problem: When user enters 1 for the position, a stack-overflow error occurs
    //Edit: change to base case
    //Convert the position specified to binary
    //Based on recursive binary conversion
    private static String convertToBinary(int position ){

        //If the position is 0 then return "0"
        //Change: changed base case to "position == 0" to account for (N - 1)
        //e.g. position entered by the user = 1
        //so position - 1 = 0
        //therefore binary position of 0 is "0"

        if(position == 0)
            return "0";

        //Make a recursive call with the value half the size of the specified position
        //Continues until the base case reaches 0, then the stack starts to unwind,
        //returning the corresponding 0s and 1s from the base case to the original call

        return convertToBinary(position/2) + (position % 2);
    }


    //Reads the binary representation of a position from left to right
    //then performs the corresponding in-Shuffles and out-Shuffles
    private static void cardForce(DeckInterface<Card> aDeck, String binaryPosition ){

        //Loop through the binary position, from the most
        //Significant bit to the least significant bit
        for(int i = 0; i < binaryPosition.length(); i++){

            //If the character at i equals '1'
            if(binaryPosition.charAt(i) == '1'){

                //Perform an inShuffle
                aDeck.inShuffle();

            }else if (binaryPosition.charAt(i) == '0'){

                //Else, perform the outShuffle
                aDeck.outShuffle();
            }
        }
    }


    //Prints out the deck of cards
    //Based on getString method in previous ArrayUtil class
    private static <T> void displayDeck(T[] aDeck){

        //The position of the first card
        int position = 1; //Change: including int variable to indicate position of card objects

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



    //Static method to create a deck of card objects
    private static Card[] makeDeck()
    {
        final String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
        final String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

        Card[] deck = new Card[52];

        //Problem: nested for-loop creating null objects
        for(int i = 0; i < deck.length; i++)
        {
            //For loop to loop through the empty string array
            // assignment statement from youtube video https://www.youtube.com/watch?v=GeEbs9-VLp0

            //Example i = 34
            // rank  array position = 34 % 13 = 8  = 9
            // Suit  array position =  34 / 13 = 2 = Hearts

            //Therefore card at position 34 is 9 of hearts

            deck[i] = new Card(ranks[i % 13], suits[i/13]);
        }

        return deck;
    }

}
