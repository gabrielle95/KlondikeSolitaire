package cards;

import java.io.Serializable;

/**
 * Class representing target stacks, where the cards are put in order to win the game. Maximum capacity is 13 cards of the same color.
*/
public class WinStack extends Stack implements Serializable {

	static final long serialVersionUID = 4L;

    public WinStack(){
        super(13);
    }

    /**
    * Put card on top of the stack.
    * Card have to be the same colour and one value higher than that of card on top of stack.
    * @return Success of the action
    */
    public boolean put(Card card){
        if( super.size() == 0){
            if ( card.value() != 1)
                return false;
        } else {
            if( super.get().color() != card.color())
                return false;
            if( super.get().compareValue(card) != -1)
                return false;
        }
        return super.put(card);
    }

}
