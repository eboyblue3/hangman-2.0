import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.TabSet;
 
public class MainWindow implements ActionListener
{
    private final static String PLAYGAME = "PLAYGAME"; //Title of Tab1
    private final static String INSTRUCTIONS = "INSTRUCTIONS"; //Title of Tab2
    private InstructionsPage ins = new InstructionsPage();
    private Board board = new Board();
    private JPanel game1, game2,game3, p, keyboard;
    private JButton b1,b2,b3,b4;
    private String level;
    public void addTabs(Container pane) throws FileNotFoundException 
    {
        JTabbedPane menu = new JTabbedPane(); //Creates Menu Pane

        p = new JPanel(); 
        p.setBackground(new Color(153, 255, 204));
        p.setLayout(new FlowLayout());
        
        //Keyboard
        keyboard = board.getKeyboard();
        
        //Window of the Game Possibilities
        Board bo1 = new Board();
        game1 = bo1.getEasyPanel();
        Board bo2 = new Board();
        game2 = bo2.getMediumPanel();
        Board bo3 = new Board();
        game3 = bo3.getHardPanel();

   
       
        b1 = new JButton("Easy");
        b2 = new JButton("Medium");
        b3 = new JButton("Hard");
        b4 = new JButton("Continue");

        
        b1.setPreferredSize(new Dimension(300, 175));
        b2.setPreferredSize(new Dimension(300, 175));
        b3.setPreferredSize(new Dimension(300, 175));
        b4.setPreferredSize(new Dimension(300, 175));
        
        
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(game1);
        p.add(game2);
        p.add(game3);

        
        game1.setVisible(false);
        game2.setVisible(false);
        game3.setVisible(false);
        keyboard.setVisible(true);
        b4.setVisible(false);
 
        JPanel instruct = null;
		try {
			instruct = ins.getPanel();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Getting Panel From Instructor Class
        
        //Constructs Menu Pane
        menu.addTab(PLAYGAME, p);
        menu.addTab(INSTRUCTIONS, instruct);
 
        pane.add(menu);
        
        b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
    }
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().contentEquals("Easy"))
		{
			level = "Easy";
			b2.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(true);
		}
		if(e.getActionCommand().contentEquals("Medium"))
		{
			level = "Medium";
			b1.setVisible(false);
			b3.setVisible(false);
			b4.setVisible(true);
		}
		if(e.getActionCommand().contentEquals("Hard"))
		{
			level = "Hard";
			b2.setVisible(false);
			b1.setVisible(false);
			b4.setVisible(true);
		}
		if(e.getActionCommand().contentEquals("Continue"))
		{
			b4.setVisible(false);
			//keyboard.setVisible(true);
			if(level.equals("Easy"))
			{
				b1.setVisible(false);
				game1.setVisible(true);
			}
			if(level.equals("Medium"))
			{
				b2.setVisible(false);
				game2.setVisible(true);
			}
			if(level.equals("Hard"))
			{
				b3.setVisible(false);
				game3.setVisible(true);
			}

		}
		
	}

    public static void show()   
    {
        //Create and set up the window.
        JFrame frame = new JFrame("HangMan 2.0");
        frame.setSize(1370,800);
        frame.setMinimumSize(new Dimension(1370,800));
        frame.setMaximumSize(new Dimension(1370,800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
 
        //Create and set up the content pane.
          MainWindow demo = new MainWindow();
          try 
          {
			demo.addTabs(frame.getContentPane());
          } 
          catch (FileNotFoundException e) 
          {
			e.printStackTrace();
          }
 
        //Display the window.
        //frame.pack();
        frame.setVisible(true);
    }
 
}