package cards;

import java.io.Serializable;

import javax.swing.JLabel;

import cards.Card;
import game.Images;

/**
 * Class representing a stack of cards, can be extended into target (winnig) stack, or working (game) stack.
 * Contains information about capacity, size and assigned label image.
*/
public class Stack implements Serializable{

	static final long serialVersionUID = 2L;

    public final Card[] cards;
    public final int capacity;
    private int size;
    public JLabel emptyImg;

    public Stack(int capacity){
        cards = new Card[capacity];
        this.capacity = capacity;
        this.size = 0;

        this.emptyImg = new JLabel( new Images().setImage("/images/cards/blank.png"));
        this.emptyImg.putClientProperty("stack", this);
        this.emptyImg.setVisible(false);
    }

    /**
     * Return card on index given by argument or null if index is greater than size of stack.
     * @param index index of the card
     * @return card on given index
     */
    public Card getCardByIndex(int index){
    	if(index > this.size)
    		return null;
        return cards[index];
    }

    public boolean isEmpty(){
    	return this.size == 0;
    }

    /**
     * @return number of cards in stack
     */
    public int size(){
        return this.size;
    }

    public void setsize(int size){
    	this.size = size;
    }

    /**
     * Put card in stack.
     * @param card card to be put in stack
     * @return Success of the action
     */
    public boolean put(Card card){
        if(this.size() == this.capacity)
            return false;
        this.cards[ this.size++ ] = card;
        return true;
    }

    /**
     * Put card in stack.
     * @param card card to be put
     */
    public void forcePut(Card card){
    	//put(card);
    	this.cards[ this.size++ ] = card;
        //return true;
    }

    /**
     * Pop card from the top of stack. Return null if stack is empty.
     * @return top card
     */
    public Card pop(){
        if(size() == 0)
            return null;
        return this.cards[ --this.size];
    }

    /**
     * Return the card from the top of stack without removing it. Return null if stack is empty.
     * @return top card
     */
    public Card get(){
        if( this.size == 0)
            return null;
        return this.cards[this.size -1 ];
    }

    /**
     * Return card if stack contains only one card.
     * @return card
     */
    public Card toCard() {
        if(this.size != 1)
            return null;
        return this.pop();
    }

    /**
     * Check whether it is possible to put card on Stack.
     * @param card card that should be put on Stack
     * @return possibility of put
     */
    public boolean tryPut(Card card){
        if(put(card)) {
            pop();
            return true;
        }
        return false;
    }
}
