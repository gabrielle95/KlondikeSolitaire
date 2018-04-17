package cards;

import java.io.Serializable;

/**
 * Extends stack, represents 7 working stacks with a maximum of 19 cards.
*/
public class GameStack extends Stack implements Serializable {

	static final long serialVersionUID = 3L;

    public GameStack(){
        super(13+6);
    }

    /**
     * Pop cards from top of the stack, starting from card given by argument. Return null if card isn't in stack.
     * @param card to be popped.
     * @return stack of cards from top of GameStack
     */
    public Stack pop(Card card){
        for(int i = 0; i < super.size(); i++){
            if(super.cards[i].equals(card)){
                int prevSize = super.size();
                super.setsize(i);
                Stack newStack = new Stack(13);
                for(; i < prevSize; i++){
                    newStack.put(super.cards[i]);
                }
                return newStack;
            }
        }
        return null;
    }

    /**
    * Put stack of cards on top of GameStack.
    * First card from stack have to be different colour and its value one lower than that of the card on top of GameStack.
    * @param stack stack to put on GameStack
    * @return Success of the action
    */
    public boolean put(Stack stack){
        if(super.size() + stack.size() > super.capacity)
            return false;

        if( super.size() == 0 ){
            if ( stack.cards[0].value() != 13 )
                return false;
        } else {

            if( super.get().similarColorTo(stack.cards[0]))
                return false;
            if( super.get().compareValue(stack.cards[0]) != 1)
                return false;
        }


        for(int i = 0;  i<stack.size(); i++)
            if (!super.put(stack.cards[i])){
                return false;
            }
        return true;
    }

    /**
     * Check whether it is possible to put card on GameStack.
     * @param card card that should be put on GameStack
     * @return possibility of put
     */
    public boolean tryPut(Card card){
        if(this.put(card.toStack())) {
            super.pop();
            return true;
        }
        return false;
    }

}
