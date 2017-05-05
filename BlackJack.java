/*/BoRHAN SAGOR
COSC 1336
creating simple BlackJack game,
psuedo code: creating value and suit enum and implementing them in Card class to create cards; afterwards creating a deck class with setters and 
gettters to implement arraylist of card class, also setting up loops , finally making a main method with player and dealer attributes with
win, lose conditionals.*/

import java.util.Scanner;

public class BlackJack{
    public static void main(String[]args){
        

        //Welcome message
        Scanner scan= new Scanner(System.in);
        System.out.println("Welcome to BlackJack!");
        System.out.println("Enter Your Name..");
        String playerName=scan.nextLine();


        //making the playing Deck
        Deck playingDeck= new Deck();
        playingDeck.createFullDeck();//using method to crate Deck
        playingDeck.shuffle();
        //create Deck for player
        Deck playerDeck=new Deck();
        //Deck playerDeck2=new Deck();
        Deck dealerDeck=new Deck();

        double playerMoney=100.00;


        //game loop
        while(playerMoney>0){
            //keep playing
            //take userinput
            System.out.println(playerName+ " balance: $"+playerMoney+" , how much would you bet?");
            double playerBet=scan.nextDouble();
            if(playerBet>playerMoney){
                System.out.println("You can not bet more than your current balance...");
                break;//if bet more than what u have, loop ends and games over...
            }

            boolean endRound=false;

            //card Dealing...
            //player gets two cards..
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer getting 2 cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your Hand:");
                System.out.println(playerDeck.toString());//printing cards in hand
                System.out.println("Your total Card Value is: "+ playerDeck.cardsValue());

                //display dealer hand
                System.out.println("Dealer Hand: "+ dealerDeck.getCard(0).toString()+" and (Hidden)");

                //hit or stand
                System.out.println("Would you like to (1) Hit or (2) Stand?");
                int response= scan.nextInt();
                //player hits
                if (response==1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You Drew a: "+ playerDeck.getCard(playerDeck.deckSize()-1).toString());

                    //Bust if over 21
                    if(playerDeck.cardsValue()>21){
                        System.out.println("Bust. Current card value at: "+playerDeck.cardsValue());
                        playerMoney-=playerBet;
                        endRound=true;
                        break;
                    }
                }
                if (response==2){
                    break;
                }
            }
            //reveal dealer cards
            System.out.println("Dealer Cards: "+ dealerDeck.toString());
            // dealer win scenario
            if((dealerDeck.cardsValue()>playerDeck.cardsValue()) && endRound==false){
                System.out.println("Dealer Won!");
                playerMoney-=playerBet;
                endRound=true;
            }

            //Dealer draws at 16 and stands at 17...
            while((dealerDeck.cardsValue()<17) && endRound==false){
                dealerDeck.draw(playingDeck);
                System.out.println("Dealer Draws: "+dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }

            //show total values for Dealera
            System.out.println("Dealer's Hand is valued at: "+dealerDeck.cardsValue());
            //if dealer busts....

            if((dealerDeck.cardsValue()>21) && endRound==false){
                System.out.println(" Dealer busts!" +playerName+" Wins!.");
                playerMoney+=playerBet;
                endRound=true;
            }

            //if its a tie
            if((playerDeck.cardsValue()==dealerDeck.cardsValue()) && endRound==false){
                System.out.println(playerName+ "loses");
                endRound=true;
            }

            if((playerDeck.cardsValue()>dealerDeck.cardsValue()) && endRound==false){
                System.out.println(" You win!");
                playerMoney+=playerBet;
                endRound=true;
            }
            else if (endRound==false){
                System.out.println("You lose the hand. ");
                playerMoney-=playerBet;
                endRound=true;
            }

            playerDeck.moveAlltoDeck(playingDeck);
            dealerDeck.moveAlltoDeck(playingDeck);
            System.out.println("End of Hand. ");

        }   
        System.out.println(" Game over! Balance: 0 ");
        
    }
}