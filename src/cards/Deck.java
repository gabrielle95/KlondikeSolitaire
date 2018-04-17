package cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class representing initial card deck with 52 shuffled cards.
*/
public class Deck{
	Card deck[];
	int size = 0;

    public Deck(){
    	deck =  new Card[52];
        for (int i = 1; i <= 13; i++){
            for (Card.Color c : Card.Color.values()){
                Card card = new Card(c,i);
                deck[size++] = card;
            }
        }
        this.shuffle();
    }

    /**
    * Shuffle cards in the deck.
    */
    public void shuffle(){
        long seed = System.nanoTime();
        ArrayList<Card> tmp = new ArrayList<Card>();
        for (Card c : this.deck){
            tmp.add(c);
        }
        Collections.shuffle(tmp, new Random(seed));
        for (int i = 0; i < tmp.size(); i++){
            this.deck[i] = tmp.get(i);
        }
    }

    /**
    * Pop card from the top of the deck. Return null if deck is empty.
    * @return top card
    */
    public Card pop(){
    	if(size == 0)
    		return null;
    	return deck[--size];
    }

    /**
    * @return number of cards in deck
    */
    public int size(){
    	return this.size;
    }
}
