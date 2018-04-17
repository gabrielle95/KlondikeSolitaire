package gui;

import cards.*;
import game.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Class representing single game
 */
public class Game extends JPanel implements Serializable{

	static final long serialVersionUID = 6L;
	private Data data;
	private GridBagLayout grid;
	private GridBagConstraints c;

	private Deck cardDeck;
	private Undo gameUndo;
	private Stack dragged;
	private Point draggedFrom;
	private Data loadedGame;

	public Game(){
		initializePanel();
        data = new Data();

        grid = new GridBagLayout();
        setLayout(grid);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        cardDeck = new Deck(); //shuffle now within constructor
        //cardDeck.shuffle();

        for(int i = 1; i < 7; i++){
        	dealCards(data.gamestacks[i], i);
        }

        // move remaining cards from cardDeck to down Stack
        deckToStack();

        gameUndo = new Undo();
        renderGUI();

        gameUndo.Move(this.data);
	}

	public void initializePanel(){
		setBackground(new Color(50, 135, 17));
        setBorder(new LineBorder(new Color(153, 153, 153), 2));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 334, Short.MAX_VALUE)
        );
	}

    /**
    * Deal cards from Deck into GameStack
    * @param stack stack cards are dealt into
    * @param size number of dealt cards
    * @return edited stack
    */
	private GameStack dealCards(GameStack stack, int size){
        for( int i = 0; i < size; i++ ){
            Card card = cardDeck.pop();
            card.setStack(stack);
            stack.forcePut( card );
        }
        stack.getCardByIndex( stack.size() -1 ).turnFaceUp();
        return stack;
    }

	private void renderGUI(){
		renderButtons();

		for(int i = 0; i < 7; i++){
			renderStacks(data.gamestacks[i], i, 2);
		}
		for(int i = 0; i < 4; i++){
			renderStacks(data.winstacks[i], i+3, 1);
		}

		renderStacks(data.stacks[0], 0, 1);
		renderStacks(data.stacks[1], 1, 1);

		data.stacks[0].emptyImg.addMouseListener( new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                refill();
            }
        });

		try {
            Thread.sleep(20);
        } catch (Exception  er) {
        }
	}

	private void renderButtons(){
		c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 3;
        JPanel buttonsContainer = new JPanel();
        add(buttonsContainer, c);
        GridLayout lay = new GridLayout(0,5);
        buttonsContainer.setLayout(lay);
        buttonsContainer.setOpaque(false);

        JButton help = new JButton("Hint");
        JButton undobutton =  new JButton("Undo");
        JButton loadbtn = new JButton("Load");
        JButton savebtn = new JButton("Save");

        gameUndo.undo = undobutton;
        gameUndo.refresh();


        buttonsContainer.add(undobutton);
        buttonsContainer.add(help);
        buttonsContainer.add(loadbtn);
        buttonsContainer.add(savebtn);

        undobutton.addActionListener((ActionEvent e) -> {
        	data = gameUndo.back();
            removeAll();
            revalidate();
            renderGUI();
            revalidate();
            repaint();
        });

        loadbtn.addActionListener((ActionEvent e) -> {
        	JFileChooser fileChooser = new JFileChooser();
        	if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        	  File file = fileChooser.getSelectedFile();
        	  loadedGame = Saving.loadGame(file);
        	}
        	if(loadedGame != null){
        		data = loadedGame;
                removeAll();
                revalidate();
                renderGUI();
                revalidate();
                repaint();
        	}
        	else{
        		msgbox("Failed to load game or you didn't select one.");
        	}

        });

        savebtn.addActionListener((ActionEvent e) -> {
        	JFileChooser fileChooser = new JFileChooser();
        	if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        	  File file = fileChooser.getSelectedFile();
        	  Saving.saveGame(data, file);
        	}
        });

        c.gridwidth = 1;
        c.insets = new Insets( 0 ,0,0,0);
	}

    /**
     * Render stack at given location
     * @param stack stack to be rendered
     * @param x x coordinate
     * @param y y coordinate
     */
	public void renderStacks(Stack stack, int x, int y){
		c.gridx = x;
        c.gridy = y;

        if( stack.isEmpty()){
            if (!stack.emptyImg.isVisible()){
                add(stack.emptyImg, c);
                stack.emptyImg.setVisible(true);
            }
            return;
        } else {
            if (stack.emptyImg.isVisible()){
                stack.emptyImg.setVisible(false);
                remove(stack.emptyImg);
            }
        }
        for( int i = stack.size() - 1; i >= 0; i-- ){
        	Card card = stack.getCardByIndex(i);
        	card.setLabel(new JLabel( card.image()));
        	card.getLabel().putClientProperty("card", card);
            if( (stack instanceof GameStack) ){
                c.insets = new Insets( i * 30 ,10,10,10); //20px
            }
            this.add( card.getLabel() , c );
            setUpListeners(card, card.getLabel());
        }
	}

    /**
     * return cards back into original Deck
     */
	private void refill(){
		while(!data.stacks[1].isEmpty()){
            Card c = data.stacks[1].pop();
            c.turnFaceDown();
            c.setStack(data.stacks[0]);
            data.stacks[0].forcePut(c);
        }
        removeStackFromScreen(data.stacks[0]);
        removeStackFromScreen(data.stacks[1]);
        renderStacks(data.stacks[0], 0, 1);
        renderStacks(data.stacks[1], 1, 1);
        gameUndo.Move(data);
        revalidate();
        repaint();
	}

	public void deckToStack(){
		int size = cardDeck.size();
		for(int i = 0; i < size; i++){
            Card card = cardDeck.pop();
            card.setStack(data.stacks[0]); //downDeck
            data.stacks[0].forcePut(card);
        }
	}

	public void removeStackFromScreen(Stack stack){
        for( int i = 0; i < stack.size(); i++){
            remove(stack.getCardByIndex(i).getLabel());
        }
    }

	public void setUpListeners(Card card, JLabel label){
		if( card.getStack() == this.data.stacks[0]){
            label.addMouseListener( new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Card newCard = data.stacks[0].pop();
                    newCard.setStack(data.stacks[1]);
                    data.stacks[1].forcePut(newCard);
                    newCard.turnFaceUp();
                    removeStackFromScreen(data.stacks[0]);
                    removeStackFromScreen(data.stacks[1]);
                    renderStacks(data.stacks[0], 0, 1);
                    renderStacks(data.stacks[1], 1, 1);
                    gameUndo.Move(data);
                    revalidate();
                    repaint();
                }
            });

        }
        else {
            label.addMouseListener( new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e)
                {
                    if(!card.isTurnedFaceUp())
                        return;

                    if((card.getStack() instanceof Stack || card.getStack() instanceof WinStack) && !(card.getStack() instanceof GameStack)){
                        Card upDeckCard = card.getStack().pop();
                        dragged = new Stack(1);
                        dragged.put(upDeckCard);
                    }
                    else
                        dragged = ((GameStack) card.getStack() ).pop(card);
                    draggedFrom = grid.location(card.getLabel().getX(), card.getLabel().getY());
                    removeStackFromScreen(card.getStack());
                    renderStacks(card.getStack(), draggedFrom.x, draggedFrom.y);
                    revalidate();
                    card.setPoint(e.getX(), e.getY());
                    for( int i = 0; i < dragged.size(); i++){
                        JLabel cardLabel = dragged.getCardByIndex(i).getLabel();
                        setComponentZOrder(cardLabel, 0);
                    }
                }

                /*** EVALUATES IF CARD CAN BE PUT TO STACK ON RELEASE ***/
                @Override
                public void mouseReleased(MouseEvent e)
                {
                    if(dragged != null){
                        int x = ((JLabel)e.getSource()).getX();
                        int y = ((JLabel)e.getSource()).getY();
                        Point loc = grid.location(x, y);
                        JLabel hoveredLabel = null;
                        if(getComponetLocation(loc.x, loc.y) instanceof JLabel )
                            hoveredLabel = (JLabel)getComponetLocation(loc.x, loc.y);
                        Stack hoveredStack = null;
                        if(hoveredLabel != null){
                            if(hoveredLabel.getClientProperty("card") == null)
                                hoveredStack = (Stack)hoveredLabel.getClientProperty("stack");
                            else
                                hoveredStack = (Stack)((Card)hoveredLabel.getClientProperty("card")).getStack();
                        }
                        if( hoveredStack == null || !(instancePut(dragged, hoveredStack))){
                            /** CARD CANNOT BE PUT TO STACK ***/

                            Stack originalStack = dragged.getCardByIndex(0).getStack();

                            for(int i = 0;  i<dragged.size(); i++)
                                originalStack.forcePut(dragged.cards[i]);

                            removeStackFromScreen(originalStack);
                            renderStacks(originalStack, draggedFrom.x, draggedFrom.y);

                        } else {
                           /* CARD CAN BE PUT TO STACK */
                            Stack originalStack = dragged.getCardByIndex(0).getStack();
                            if( !originalStack.isEmpty()){
                                Card topCardFromOriginal = originalStack.getCardByIndex( originalStack.size() - 1);
                                topCardFromOriginal.turnFaceUp();
                                topCardFromOriginal.getLabel().setIcon( topCardFromOriginal.image() );
                            }
                            for( int i = 0; i < dragged.size(); i++)
                                dragged.getCardByIndex(i).setStack(hoveredStack);
                            removeStackFromScreen(hoveredStack);
                            //deleteStackMouseListeners(hoveredStack);
                            renderStacks(hoveredStack, loc.x, loc.y);
                            gameUndo.Move(data);

                            boolean victoryFlag = false;

	        				for(int i = 0; i < 4; i++){
	        					if (data.winstacks[i].size() == 13)
	        						victoryFlag = true;
	        					else{
	        						victoryFlag = false;
	        						break;
	        					}
	        				}
	        				if(victoryFlag)
	        					msgbox("CONGRATULATIONS. YOU HAVE WON!");

                        }

                        revalidate();
                        repaint();
                        dragged = null;
                    }
                }
            });
            label.addMouseMotionListener(new MouseAdapter()   {
                /* DRAGGING */
                @Override
                public void mouseDragged(MouseEvent e)
                {
                    if(!card.isTurnedFaceUp())
                        return;
                    int mx =  - card.getX() + e.getX();
                    int my =  - card.getY() + e.getY();

                    for(int i = 0; i < dragged.size(); i++){
                        JLabel cardLabel = dragged.getCardByIndex(i).getLabel();
                        cardLabel.setLocation( cardLabel.getX() + mx, cardLabel.getY() + my);
                    }

                    repaint();
                }
            });
        }

	}

	private void msgbox(String s){
		   JOptionPane.showMessageDialog(this, s);
		}

    /**
     * Get component from location given by arguments
     * @param x x coordinate
     * @param y y coordinate
     * @return Component at given location
     */
	public Component getComponetLocation(int x, int y){
	    Component match = null;
        for (Component comp : getComponents()) {
            GridBagConstraints gbc = grid.getConstraints(comp);
            if (gbc.gridx == x && gbc.gridy == y ) {
                match = comp;
                break;
            }
        }
        return match;
	}

    /**
     * Move cards from one stack to another
     * @param from stack from which cards are moved
     * @param to stack where cards are put
     * @return Success of the action
     */
	public boolean instancePut(Stack from, Stack to){
        if( to instanceof GameStack){
            return ((GameStack)to).put(from);
        } else if ( to instanceof WinStack){
            if( from.size() != 1 )
                return false;
            return ((WinStack)to).put(from.getCardByIndex(0));
        }
        return false;
    }
}
