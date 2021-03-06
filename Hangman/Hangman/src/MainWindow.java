import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.text.BadLocationException;

class MainWindow implements ActionListener
{
    private final static String PLAYGAME = "Game"; //Title of Tab1
    private final static String INSTRUCTIONS = "Instructions"; //Title of Tab2
    
    private InstructionsPage ins = new InstructionsPage();
    private Board board = new Board();

    private JPanel game1;
    private JPanel game2;
    private JPanel game3;
    private JPanel buttons;
    private JPanel p;
    private JLabel welcome;
    private JButton b;
    private JComboBox<String> difficultySelector;
    private String[] difficulties = {"Easy", "Medium", "Hard"};
    private String level;
    
    /**
     * adds tabs and content to main window
     * @param pane
     * @throws FileNotFoundException
     */
    private void addTabs(Container pane) throws FileNotFoundException
    {
        JTabbedPane menu = new JTabbedPane(); //Creates Menu Pane
        
        welcome = new JLabel("HangMan 2.0", SwingConstants.CENTER);
		welcome.setFont(new Font("ChalkBoard", Font.BOLD, 50));
		
        buttons = new JPanel(); //Difficulty selector and continue button
        buttons.setBackground(new Color(0, 204, 204));
        buttons.setMinimumSize(new Dimension(200, 200));
        
        p = new JPanel(); //Entire panel
        p.setLayout(new GridLayout(2,2));
        p.setBackground(new Color(0, 204, 204));
        
        //Keyboard
        JPanel keyboard = board.getKeyboard();
        
        //Window of the Game Possibilities
        Board bo1 = new Board();
        game1 = bo1.getEasyPanel();
        Board bo2 = new Board();
        game2 = bo2.getMediumPanel();
        Board bo3 = new Board();
        game3 = bo3.getHardPanel();

        b = new JButton("Continue");
        b.setPreferredSize(new Dimension(300,100));
        difficultySelector = new JComboBox<>(difficulties);
        difficultySelector.setPreferredSize(new Dimension(300,100));
        
        //Buttons Panel additions
        buttons.add(difficultySelector);
        buttons.add(b);
     
        //Adding to final panel
        p.add(welcome);
        p.add(buttons);
        
        level = "Easy"; //Default level, can be changed in the difficultySelectory 

        keyboard.setVisible(true);
 
        //Necessary to prevent errors
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
        
        difficultySelector.addActionListener(this);
		b.addActionListener(this);
    }
    
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("Continue"))
		{
			b.setVisible(false);
			difficultySelector.setVisible(false);
			buttons.setVisible(false);
			welcome.setVisible(false);
			
			p.setLayout(new BorderLayout());

			//Adds desired game panel 
			if(level.equals("Easy")) 
			{
				p.add(game1, BorderLayout.CENTER);
			}
			if(level.equals("Medium"))
			{
				p.add(game2, BorderLayout.CENTER);
			}
			if(level.equals("Hard"))
			{
				p.add(game3, BorderLayout.CENTER);
			}
		}
		else
		{
			level = (String) difficultySelector.getSelectedItem();
			
			System.out.println(level);
		}
	}

    /**
     * shows window
     * @throws UnsupportedLookAndFeelException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws ClassNotFoundException 
     */
	public static void show() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        //Create and set up the window.
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("HangMan 2.0");
        frame.setSize(800,800);
        frame.setMinimumSize(new Dimension(1000,800));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
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
        frame.pack();
        frame.setVisible(true);
    }
}