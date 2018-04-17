package cards;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import game.*;


/**
 * Class representing playing card, contains information about colour, value, image and location of card.
 */
public class Card implements Serializable{

	static final long serialVersionUID = 1L;

    /**
     * Enum type representing colour of card.
     */
	public enum Color{
        CLUBS("C"), DIAMONDS("D"), HEARTS("H"), SPADES("S");

        private final String sh;
        Color(String name){
            sh = name;
        }

        @Override
        public String toString(){
            return sh;
        }

        /**
        * Method testing whether the card has similar colour to that given by argument
        *
        * @param c Compared colour
        * @return True if similar, false if not.
        */
        public boolean similarColorTo(Card.Color c){
            if (c.equals(CLUBS) || c.equals(SPADES))
                return (this.sh.equals("C") || this.sh.equals("S"));
            else
                return (this.sh.equals("H") || this.sh.equals("D"));
        }
    }

    /**
    * Returns string representation of card value
    * @return string representation of card value
    */
    private String cardValue(int val){
        switch (val){
            case 1: return "A";
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10: return String.valueOf(val);
            case 11: return "J";
            case 12: return "Q";
            case 13: return "K";
            default: throw new IllegalArgumentException("Invalid card value: "+val); //should never occur
        }
    }

    private int val;
    private Card.Color color;
    private boolean up;
    private ImageIcon image;
    private JLabel label;
    public Dimension prefsize = new Dimension();
    private int cardX;
    private int cardY;

    public Card(Card.Color c, int value){
        List<Color> list = Arrays.asList(Color.values());
        if (!list.contains(c))
            throw new IllegalArgumentException("Invalid card: "+val);
        else
            this.color = c;
        if (value < 1 || value > 13)
            throw new IllegalArgumentException("Invalid card: "+val);
        else
            this.val = value;
        this.up = false;
        this.image = new Images().setImage("/images/cards/cardBack.png");
    }

    public void setPoint(int X, int Y){
        this.cardX = X;
        this.cardY = Y;
    }

    /**
    * @return X coordinate of the card.
    */
    public int getX(){
    	return this.cardX;
    }

    /**
    * @return Y coordinate of the card.
    */

    public int getY(){
    	return this.cardY;
    }

    public JLabel getLabel(){
    	return this.label;
    }

    public void setLabel(JLabel label){
    	this.label = label;
    }

    /**
    * @return Card's image.
    */
    public ImageIcon image(){
        return this.image;
    }

    /**
    * @return Card's value.
    */
    public int value(){
        return this.val;
    }

    /**
    * @return Card's color
    */
    public Card.Color color(){
        return this.color;
    }

    /**
    * Tests whether the card is turned up.
    * @return result of the test.
    */
    public boolean isTurnedFaceUp(){
        return this.up;
    }

    /**
     * Tests whether the card is similar colour (Red or Black) to card given by argument.
     * @param c tested card
     * @return Information about similarity of cards.
     */
    public boolean similarColorTo(Card c){
        return (this.color.similarColorTo(c.color()));
    }

    /**
    * Turn the card face up. If the card is already turned doesn't do anything.
    * @return Information, whether the card was turned.
    */
    public boolean turnFaceUp() {
        if (this.up){
            return false;
        }
        else{
            this.up = true;
            this.image = new Images().setImage("/images/cards/" + this.toString() + ".png");
            return true;
        }
    }

    /**
     * Turn the card face down. If the card is already turned doesn't do anything.
     * @return Information, whether the card was turned.
     */
    public boolean turnFaceDown() {
        if (!this.up){
            return false;
        }
        else{
            this.up = false;
            this.image = new Images().setImage("/images/cards/cardBack.png");
            return true;
        }
    }

    /**
    * Compare value of the card with that given by argument.
    * @param c compared card
    * @return Difference between cards.
    */
    public int compareValue(Card c){
        return this.val - c.value();
    }

    /**
    * Create stack containing this card.
    * @return created stack
    */
    public Stack toStack(){
    	Stack stack = new Stack(1);
    	stack.put(this);
    	return stack;
    }
    @Override
    public String toString(){
        return cardValue(this.val)+"("+this.color.toString()+")";
    }

    @Override
    public int hashCode(){
        return 31 * this.val * this.color.toString().hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Card))
            return false;
        Card card = (Card) o;
        return (card.color == this.color && card.val == this.val);
    }

    private Stack fromStack;

    /**
    * @return stack cointaining this card
    */
    public Stack getStack(){
        return this.fromStack;
    }

    public void setStack(Stack stack){
        this.fromStack = stack;
    }


}
