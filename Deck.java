import java.util.*;
public class Deck{

    private ArrayList<Card> cards;//instance

    public Deck(){//constructor
      this.cards=new ArrayList<Card>();  
    }

    public void createFullDeck(){
        //creating cards
        for (Suit cardSuit : Suit.values()){
            for(Value cardValue: Value.values()){
            //adding new card to the deck
            this.cards.add(new Card(cardSuit, cardValue));
            }
        }
    }

    public void shuffle(){//shuffle method for Deck, creating temp deck to later on add to main deck
        ArrayList<Card> tempDeck=new ArrayList<Card>();
        //create random gen
        Random generator= new Random();
        int randomCardIndex=0;
        int originalSize=this.cards.size();
        for(int i=0; i<originalSize;i++){
            //generate random randomCardIndex
            randomCardIndex=generator.nextInt((this.cards.size()-1)+1);
            tempDeck.add(this.cards.get(randomCardIndex));//added to temp deck
            //removing from the original tempDeck
            this.cards.remove(randomCardIndex);//removed from deck
        }
        this.cards=tempDeck;//put temp to original
    }

    public String toString(){//string with all card values, w/ index
        String cardListOutput="";
        for(Card aCard : this.cards){
           cardListOutput+="\n "+ aCard.toString();
        }
        return cardListOutput; //outputs list
    }
    public void removeCard(int i){//remove index from arraylist of cards
        this.cards.remove(i);
    }
    public Card getCard(int i){
        return this.cards.get(i);
    }
    public void addCard(Card addCard){
        this.cards.add(addCard);
    }
    //Drawing from deck..
    public void draw(Deck pFrom){
        this.cards.add(pFrom.getCard(0));
        pFrom.removeCard(0);
    }

    public int deckSize(){
        return this.cards.size();
    }

    public void moveAlltoDeck(Deck moveTo){
        int thisDeckSize=this.cards.size();

        //puts cards into moveTo deck

        for(int i=0; i<thisDeckSize;i++){
            moveTo.addCard(this.getCard(i));
        }

        for(int i=0;i<thisDeckSize; i++){
            this.removeCard(0);
        }

    }

    //return value of cards in deck(Total)
    public int cardsValue(){
        int totalValue=0;
        int aces=0;

        for(Card aCard : this.cards){
            switch(aCard.getValue()){
                case TWO: totalValue+=2; break;
                case THREE: totalValue+=3; break;
                case FOUR: totalValue+=4; break;
                case FIVE: totalValue+=5; break;
                case SIX: totalValue+=6; break;
                case SEVEN: totalValue+=7; break;
                case EIGHT: totalValue+=8; break;
                case NINE: totalValue+=9; break;
                case TEN: totalValue+=10; break;
                case JACK: totalValue+=10; break;
                case QUEEN: totalValue+=10; break;
                case KING: totalValue+=10; break;
                case ACE: totalValue+=1; break;
            }
        }

        for(int i=0; i<aces; i++){
            if (totalValue>10){
                totalValue+=1;//ace value 10
            }
            else{
                totalValue+=11;//ace value 1
            }
        }
        return totalValue;
    }

}