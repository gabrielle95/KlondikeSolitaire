package game;

import cards.Stack;
import cards.WinStack;
import cards.GameStack;

import javax.swing.JButton;

import cards.Card;

/**
 * Class responsible for the Undo operation.
 */
public class Undo {
	public JButton undo;
	private int steps;
	public Data step[] = new Data[6];
	public Undo(){
		undo = new JButton();
		steps = 0;
	}

    /**
     * @return data from previous turn
     */
	public Data back(){
        return step[--steps - 1];
	}

    /**
     * Save game data for undo
     * @param data Data to be copied
     * @see DeepCopyData
     */
	public void Move(Data data){
        if(steps < 6){
            step[steps++] = DeepCopyData(data);
        } else{
            for (int i = 0; i < steps - 1; i++)
                    step[i] = step[i + 1];
            step[5] = DeepCopyData(data);
        }
        refresh();
	}

    /**
     * Make a deep copy of data given by argument
     * @param data data to be copied
     * @return copied data
     */
    public Data DeepCopyData(Data data){
        Data newData = new Data();
        for(int i = 0; i < data.gamestacks.length; i++)
            newData.gamestacks[i] = (GameStack)DeepCopyStack((Stack)data.gamestacks[i]);
        for(int i = 0; i < data.winstacks.length; i++)
            newData.winstacks[i] = (WinStack)DeepCopyStack((Stack)data.winstacks[i]);
        for(int i = 0; i < data.stacks.length; i++)
        	newData.stacks[i] = (Stack)DeepCopyStack((Stack)data.stacks[i]);
        return newData;
    }

    /**
     * Make a deep copy of stack
     * @param stack stack to be copied
     * @return copied stack
     */
    public Stack DeepCopyStack(Stack stack){
        Stack newStack = null;
        if( stack instanceof GameStack)
            newStack = new GameStack();
        else if( stack instanceof WinStack)
            newStack = new WinStack();
        else if( stack instanceof Stack)
            newStack = new Stack(31);

        for( int i = 0; i < stack.size(); i++){
            Card newCard = new Card(stack.cards[i].color(), stack.cards[i].value());
            if(stack.cards[i].isTurnedFaceUp())
                newCard.turnFaceUp();
            newCard.setStack(newStack);
            newStack.forcePut(newCard);
        }
        return newStack;
    }
    /**
    * Makes the button unclickable if no Undo steps are available.
    */
    public void refresh(){
    	if (steps <= 1){
    		undo.setEnabled(false);
    	}
    	else{
    		undo.setEnabled(true);
    	}
    }

}
