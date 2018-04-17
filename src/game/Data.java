package game;

import cards.GameStack;
import cards.WinStack;
import cards.Stack;
import java.io.Serializable;

/**
 * Class containing game data.
 */
public class Data implements Serializable{
    public GameStack gamestacks[] =  new GameStack[7];
    public WinStack winstacks[] = new WinStack[4];
    public Stack stacks[] = new Stack[2];

    static final long serialVersionUID = 5L;

    public Data(){
        for( int i = 0; i < gamestacks.length; i++)
        	gamestacks[i] = new GameStack();
        for( int i = 0; i < winstacks.length; i++)
        	winstacks[i] = new WinStack();
        for( int i = 0; i < stacks.length; i++)
        	stacks[i] = new Stack(31);
    }
}
