package gui;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.Serializable;

public class MainWindow extends JFrame implements Serializable{

	static final long serialVersionUID = 7L;
	/**
	 * Variable declarations
	 */

	private GridLayout layout;
    private JMenuBar jMenuBar1;
    private JMenu jMenu1;
    private JMenuItem newBtn;
    private JMenu jMenu2;
    private JMenu jMenu6;
    private JMenuItem field1btn;
    private JMenuItem field2btn;
    private JMenuItem field3btn;
    private JMenuItem field4btn;
    private JMenuItem quit1btn;
    private JMenuItem quit2btn;
    private JMenuItem quit3btn;
    private JMenuItem quit4btn;
    private JMenu quitSub;
    private int gameCount;
    private JPanel contentPanel;

    private Game[] games;

	/**
	 * Launch the application.
     * @param args main args
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
	}

	/**
	 * Constructor for the frame.
	 */
	public MainWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/images/cards/icon.png")));
		initWindow();
		layout = new GridLayout(1,1);
		getContentPane().setLayout(layout);
		this.gameCount = 0;
		contentPanel = new JPanel();
		this.games = new Game[4];
		changeGameCount(1, -1);
	}

	/**
	 * Window init
	 */
	public void initWindow(){
        jMenu1 = new javax.swing.JMenu();

        newBtn = new javax.swing.JMenuItem();
        newBtn.setFont(new Font("Verdana", Font.BOLD, 12));
        newBtn.setHorizontalAlignment(SwingConstants.CENTER);
        jMenu2 = new javax.swing.JMenu();

        jMenu6 = new javax.swing.JMenu();
        jMenu6.setFont(new Font("Verdana", Font.BOLD, 12));
        jMenu6.setHorizontalAlignment(SwingConstants.CENTER);
        field1btn = new javax.swing.JMenuItem();
        field1btn.setFont(new Font("Verdana", Font.BOLD, 12));
        field1btn.setHorizontalAlignment(SwingConstants.CENTER);
        field2btn = new javax.swing.JMenuItem();
        field2btn.setFont(new Font("Verdana", Font.BOLD, 12));
        field2btn.setHorizontalAlignment(SwingConstants.CENTER);
        field3btn = new javax.swing.JMenuItem();
        field3btn.setFont(new Font("Verdana", Font.BOLD, 12));
        field3btn.setHorizontalAlignment(SwingConstants.CENTER);
        field4btn = new javax.swing.JMenuItem();
        field4btn.setFont(new Font("Verdana", Font.BOLD, 12));
        field4btn.setHorizontalAlignment(SwingConstants.CENTER);
        //jMenu3 = new javax.swing.JMenu();

        jMenu1.setFont(new Font("Verdana", Font.BOLD, 12));
        jMenu1.setHorizontalAlignment(SwingConstants.CENTER);
        jMenu2.setFont(new Font("Verdana", Font.BOLD, 12));
        jMenu2.setHorizontalAlignment(SwingConstants.CENTER);

        quitSub = new JMenu();
        quitSub.setHorizontalAlignment(SwingConstants.CENTER);
        quitSub.setFont(new Font("Verdana", Font.BOLD, 12));
        quit1btn = new JMenuItem("Quit 1");
        quit1btn.setFont(new Font("Verdana", Font.BOLD, 12));
        quit1btn.setHorizontalAlignment(SwingConstants.CENTER);
        quit2btn = new JMenuItem("Quit 2");
        quit2btn.setFont(new Font("Verdana", Font.BOLD, 12));
        quit2btn.setHorizontalAlignment(SwingConstants.CENTER);
        quit3btn = new JMenuItem("Quit 3");
        quit3btn.setFont(new Font("Verdana", Font.BOLD, 12));
        quit3btn.setHorizontalAlignment(SwingConstants.CENTER);
        quit4btn = new JMenuItem("Quit 4");
        quit4btn.setFont(new Font("Verdana", Font.BOLD, 12));
        quit4btn.setHorizontalAlignment(SwingConstants.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("KlondikeSolitaire");
        setBounds(new java.awt.Rectangle(100, 100, 800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));


        jMenu1.setText("Game");

        newBtn.setText("New");
        quitSub.setText("Quit");
        jMenu2.setText("Settings");
        jMenu6.setText("No of playfields");


        quit1btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	changeGameCount(gameCount-1, 1);
            }
        });
        quit2btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	changeGameCount(gameCount-1, 2);
            }
        });
        quit3btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	changeGameCount(gameCount-1, 3);
            }
        });
        quit4btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	changeGameCount(gameCount-1, 4);
            }
        });



        quitSub.add(quit1btn);
        quitSub.add(quit2btn);
        quitSub.add(quit3btn);
        quitSub.add(quit4btn);

        jMenu1.add(newBtn);
        jMenu1.add(quitSub);

        jMenuBar1 = new JMenuBar();
		jMenuBar1.add(jMenu1);


        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	fieldIncrGame(evt);
            }
        });

        field1btn.setText("1 field");
        field1btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	field1btnActionPerformed(evt);
            }
        });


        field2btn.setText("2 fields");
        field2btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	field2btnActionPerformed(evt);
            }
        });


        field3btn.setText("3 fields");
        field3btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	field3btnActionPerformed(evt);
            }
        });


        field4btn.setText("4 fields");
        field4btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	field4btnActionPerformed(evt);
            }
        });

        jMenu6.add(field1btn);
        jMenu6.add(field2btn);
        jMenu6.add(field3btn);
        jMenu6.add(field4btn);

        jMenu2.add(jMenu6);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
	}

	public void changeGameCount( int newCount, int gameOrder){

		if(gameOrder != -1){
        	switch(gameOrder){
        	case 1:
        		games[1] = games[0];
        		games[2] = games[1];
        		games[3] = games[2];
        		break;
        	case 2:
        		games[2] = games[1];
        		games[3] = games[2];
        		break;
        	case 3:
        		games[3] = games[2];
        		break;
        	default:
        		break;
        	}

        }
        if ( gameCount == 2)
            this.remove(contentPanel);

        if ( newCount < gameCount){ //gamecount--
            while ( gameCount != newCount)
                this.remove(this.games[--gameCount] );
        }
        else if ( newCount > gameCount){ // gamecount ++
            while ( gameCount != newCount){
                this.games[gameCount] = new Game();
                getContentPane().add(this.games[gameCount++]);
            }
        }

        if (newCount == 1){
            this.layout.setColumns(1);
            this.layout.setRows(1);
        } else {
            this.layout.setColumns(2);
            this.layout.setRows(2);
        }
        if (newCount == 2){
            getContentPane().add(contentPanel);
        }

        this.revalidate();
        this.repaint();
    }

	private void fieldIncrGame(java.awt.event.ActionEvent evt) {
		if(gameCount < 4)
			changeGameCount(gameCount+1, -1);
		else
			msgbox("Max number of games reached!");
    }

    private void field1btnActionPerformed(java.awt.event.ActionEvent evt) {
        changeGameCount(1, -1);
    }

    private void field2btnActionPerformed(java.awt.event.ActionEvent evt) {
    	changeGameCount(2, -1);
    }

    private void field3btnActionPerformed(java.awt.event.ActionEvent evt) {
    	changeGameCount(3,-1);
    }

    private void field4btnActionPerformed(java.awt.event.ActionEvent evt) {
    	changeGameCount(4,-1);
    }

	private void msgbox(String s){
		   JOptionPane.showMessageDialog(this, s);
		}
}
