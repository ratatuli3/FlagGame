import java.awt.*;
import java.awt.event.*;
import java.io.File; //UNUSED UNTIL IMPORTED EXTENDED LIBRARY
import javax.swing.*;
//import jaco.mp3.player.MP3Player; //UNCOMMENT ONLY IF IMPORTED EXTENDED LIBRARY
import java.util.ArrayList;

/*A bunch of notes:
 * I've commented these lines of code: 5,72-78,83-97,258,264,265,269,270,293,299,322,323,327,328,359-362,378  because they require the extended library i have given on the zip file.
 * I really can't recommend you enough to import the library file i've given you.
 * I think this also works if you put it on a terminal. (javac -classpath pathToLibrary MyClassName.java)
 * I've also commented a lot of stuff to make it easier to understand, although i don't know if i did a good enough job with it.
 * */

public class Project2 extends JFrame implements ActionListener { //This is the Second Game
	
	//----------------------------------------------------------- //Arrays
	private String[] tempFlagName = {"Albania", "Macedonia", "Serbia", "Greece", "Nigeria", "Iraq", "China", "Algeria", "Germany", "France", "Spain", "UnitedKingdom", "Russia",
			"UnitedStates", "Ireland", "Austria", "Sweden", "Kosovo", "Jamaica", "Mexico", "Japan", "Ukraine", "SouthKorea", "India", "Norway"};
	private ArrayList<String> flagNames;
	private ArrayList<String> chosenFlags;
	private ArrayList<String> chosenFlagsCopy;
 	private ArrayList<JRadioButton> flagButtons;
	//----------------------------------------------------------- //JTextFields
	private JTextField countryText;
	private JTextField counterText;
	private JTextField responseText;
	//----------------------------------------------------------- //JLabels
	private JLabel helpLabel;
	private JLabel responseLabel;
	private JLabel blank3Label;
	private JLabel rightMario;
	private JLabel leftMario;
	private JLabel endLabel;
	private JLabel blank4Label;
	private JLabel blank4Label2;
	//----------------------------------------------------------- //JPanels
	private JPanel panelMain;
	private JPanel panelAnswer;
	private JPanel panelSouth;
	private JPanel panelCenter;
	private JPanel panelAnsButtons;
	private JPanel panelFlagX1;
	private JPanel panelFlagX2;
	private JPanel panelButtons;
	private JPanel panelMisc;
	private JPanel panelFix;
	//----------------------------------------------------------- //JButtons
	private JButton answerButton;
	private JButton nextButton;
	private JButton skipLevel;
	private JButton switchButton;
	//----------------------------------------------------------- //ButtonGroup
	private ButtonGroup cbg;
	//----------------------------------------------------------- //JFrame
	private JFrame frame;
	//----------------------------------------------------------- //ImageIcons
	private ImageIcon blank3Icon;
	private ImageIcon blank4Icon;
	private ImageIcon titleImage;
	private ImageIcon leftImage;
	private ImageIcon rightImage;
	private ImageIcon winImage;
	private ImageIcon loseImage;
	//----------------------------------------------------------- //String
	private String flagName;
	//----------------------------------------------------------- //Integers
	private int flagIndex = (int)Math.random()*5;
	private int countLose = 0;
	private int countSkip = 0;
	//----------------------------------------------------------- //MP3Players
	/*MP3Player player1;
	MP3Player playerWin1;
	MP3Player playerLose1;
	MP3Player playerCorrect1;
	MP3Player playerWrong1;
	MP3Player playerSkip1;
	MP3Player playerSwitch1;*/
	//-----------------------------------------------------------
	public Project2(){ //Constructor for Project2
		//INSTANTIATIONS
		//----------------------------------------------------------- // Music Player (UNCOMMENT ONLY AFTER IMPORTING NEW LIBRARY)
		/*player1 = new MP3Player();
		player1.setRepeat(true);
		player1.addToPlayList(new File("MarioMusic/Mario.mp3"));
		playerWin1 = new MP3Player();
		playerWin1.addToPlayList(new File("MarioMusic/MarioWin.mp3"));
		playerLose1 = new MP3Player();
		playerLose1.addToPlayList(new File("MarioMusic/MarioLose.mp3"));
		playerCorrect1 = new MP3Player();
		playerCorrect1.addToPlayList(new File("MarioMusic/MarioCorrectChoice.mp3"));
		playerWrong1 = new MP3Player();
		playerWrong1.addToPlayList(new File("MarioMusic/MarioWrongChoice.mp3"));
		playerSkip1 = new MP3Player();
		playerSkip1.addToPlayList(new File("MarioMusic/MarioSkipLevel.mp3"));
		playerSwitch1 = new MP3Player();
		playerSwitch1.addToPlayList(new File("DonkeyMusic/DonkeySwitch.mp3"));*/
		//----------------------------------------------------------- //CheckBoxGroup Instantiation
		cbg = new ButtonGroup();
		//----------------------------------------------------------- //ArrayList Instantiations
		flagNames = new ArrayList<String>();
		chosenFlags = new ArrayList<String>();
		flagButtons = new ArrayList <JRadioButton>();
		chosenFlagsCopy = new ArrayList<String>();
		//----------------------------------------------------------- //Button Instantiations
		answerButton = new JButton("Check for Answer");
		nextButton = new JButton("Next Question");
		skipLevel = new JButton("Skip Level");
		switchButton = new JButton("Switch Game");
		nextButton.setEnabled(false);
		//----------------------------------------------------------- //JTextField Instantiations
		countryText = new JTextField(13);
		counterText = new JTextField(100);
		responseText = new JTextField(7);
		counterText.setText("No errors made yet!");
		//----------------------------------------------------------- //JLabel Instantiations
		helpLabel = new JLabel("Help: ");
		responseLabel = new JLabel("Answer: ");
		blank3Label = new JLabel();
		blank4Label = new JLabel();
		blank4Label2 = new JLabel();
		rightMario = new JLabel();
		leftMario = new JLabel();
		endLabel = new JLabel();
		//----------------------------------------------------------- //JPanel Instantiations
		panelMain = new JPanel();
		panelSouth = new JPanel();
		panelMain = new JPanel();
		panelCenter = new JPanel();
		panelFlagX1 = new JPanel();
		panelFlagX2 = new JPanel();
		panelAnsButtons = new JPanel();
		panelAnswer = new JPanel();
		panelButtons = new JPanel();
		panelMisc = new JPanel();
		panelFix = new JPanel();
		//----------------------------------------------------------- //ImageIcon Instantiations
		blank3Icon = new ImageIcon("Blanks/blank3.png");
		blank4Icon = new ImageIcon("Blanks/blank2.png");
		titleImage = new ImageIcon("SuperMario/SUPA.png");
		leftImage = new ImageIcon("SuperMario/gifLeft.gif");
		rightImage = new ImageIcon("SuperMario/gifRight.gif");
		winImage = new ImageIcon("SuperMario/gifWin.gif");
		loseImage = new ImageIcon("SuperMario/gifLose.gif");
		//-----------------------------------------------------------
		//CONFIGURATIONS
		//----------------------------------------------------------- //Adding from the Array to the ArrayList
		for(int i = 0; i<25; i++)
			flagNames.add(tempFlagName[i]);	
		//----------------------------------------------------------- //Adding 6 randomly generated flags
		for(int i = 0; i<6; i++) {
			int random = (int)(Math.random()*flagNames.size());
			chosenFlags.add(flagNames.get(random));
			flagNames.remove(random);
		}
		//----------------------------------------------------------- //First Generated flag
		flagName = (chosenFlags.get((int)(flagIndex)));
		//----------------------------------------------------------- //Making a Dummy array so it doesn't interfere with action performed when we remove elements from it
		for (int i = 0; i<6; i++) {
			chosenFlagsCopy.add(chosenFlags.get(i));
		}
		//----------------------------------------------------------- //Creating Check Boxes
		for (int i = 0; i<6; i++) {
			int random = (int)(Math.random()*chosenFlagsCopy.size()-1);
			JRadioButton tempo = new JRadioButton(new ImageIcon("FlagsAndImagesProject2/"+chosenFlagsCopy.get(random).toLowerCase()+".jpg"));
			tempo.setName(chosenFlagsCopy.get(random));
			flagButtons.add(tempo);
			chosenFlagsCopy.remove(random);
		}
		//----------------------------------------------------------- //Buttons Configuration
		answerButton.setActionCommand("answer");
		answerButton.addActionListener(this);
		nextButton.setActionCommand("next");
		nextButton.addActionListener(this);
		skipLevel.setActionCommand("skip");
		skipLevel.addActionListener(this);
		switchButton.setActionCommand("switch");
		switchButton.addActionListener(this);
		//----------------------------------------------------------- //ButtonGroup Configuration
		for (int i=0; i<6; i++)
			cbg.add(flagButtons.get(i));
		//----------------------------------------------------------- //JLabel Configurations
		blank3Label.setIcon(blank3Icon);
		blank4Label.setIcon(blank4Icon);
		blank4Label2.setIcon(blank4Icon);
		rightMario.setIcon(rightImage);
		leftMario.setIcon(leftImage);
		//----------------------------------------------------------- //JTextFields Configurations
		countryText.setText(flagName);
		countryText.setEditable(false);
		countryText.setMaximumSize(new Dimension(400, 50));
		responseText.setEditable(false);
		responseText.setMaximumSize(new Dimension(200, 25));
		counterText.setEditable(false);
		counterText.setMaximumSize(new Dimension(200,25));
		//----------------------------------------------------------- //Answer Panel (Includes the Response Label and the JTextField Response)
		panelAnswer.setLayout(new BoxLayout(panelAnswer, BoxLayout.X_AXIS));
		panelAnswer.add(responseLabel);
		panelAnswer.add(responseText);
		//----------------------------------------------------------- //Answer Buttons Panel
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		panelButtons.add(helpLabel);
		panelButtons.add(skipLevel);
		panelButtons.add(blank3Label);
		panelButtons.add(switchButton);
		//----------------------------------------------------------- //Answer Buttons Panel
		panelAnsButtons.setLayout(new BoxLayout(panelAnsButtons, BoxLayout.X_AXIS));
		panelAnsButtons.add(answerButton);
		panelAnsButtons.add(nextButton);
		//----------------------------------------------------------- //Fix Panel
		panelFix.setLayout(new BoxLayout(panelFix, BoxLayout.X_AXIS));
		panelFix.add(blank4Label2);
		panelFix.add(endLabel);
		panelFix.add(blank4Label);
		//----------------------------------------------------------- //FlagX Panels
		panelFlagX1.setLayout(new BoxLayout(panelFlagX1, BoxLayout.X_AXIS));
		panelFlagX1.add(flagButtons.get(0));
		panelFlagX1.add(flagButtons.get(1));
		panelFlagX1.add(flagButtons.get(2));
		panelFlagX2.setLayout(new BoxLayout(panelFlagX2, BoxLayout.X_AXIS));
		panelFlagX2.add(flagButtons.get(3));
		panelFlagX2.add(flagButtons.get(4));
		panelFlagX2.add(flagButtons.get(5));
		//----------------------------------------------------------- //Buttons and Text Panel
		panelMisc.setLayout(new BoxLayout(panelMisc, BoxLayout.Y_AXIS));
		panelMisc.add(counterText);
		panelMisc.add(countryText);
		panelMisc.add(panelAnsButtons);
		panelMisc.add(panelAnswer);
		//----------------------------------------------------------- //Center Panel
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));
		panelCenter.add(leftMario);
		panelCenter.add(panelMisc);
		panelCenter.add(rightMario);
		//----------------------------------------------------------- //North Panel
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));
		panelSouth.add(panelFlagX1);
		panelSouth.add(panelFlagX2);
		panelSouth.add(panelButtons);
		//----------------------------------------------------------- //Main Panel
		panelMain.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panelMain.setLayout(new BorderLayout());
		panelMain.add(panelSouth, BorderLayout.SOUTH);
		panelMain.add(panelCenter, BorderLayout.CENTER);
		}
	public void skip() { //Functionality of the skip level button
		countSkip++;
		for(int i = 0; i<chosenFlags.size(); i++) {
            if (flagButtons.get(i).getName().equals(flagName) && chosenFlags.size() != 1) {
                chosenFlags.remove(flagIndex);
            	flagIndex = (int)(Math.random()*chosenFlags.size()-1);
                flagButtons.get(i).setEnabled(false);
              	flagButtons.remove(i);
                flagName = (chosenFlags.get(flagIndex));
                countryText.setText(flagName);
                skipLevel.setEnabled(false);
                nextButton.setEnabled(false);
                //playerSkip1.play();
                break;
                }
            else if(chosenFlags.size() <= 1) { //This is what happens when the game ends
            	if(countLose <= 3) {
            		endLabel.setIcon(winImage);
            		//player1.stop();
            		//playerWin1.play();
            	}
            	else if(countLose > 3) {
            		endLabel.setIcon(loseImage);
            		//player1.stop();
            		//playerLose1.play();          		
            	}
            	panelSouth.remove(panelFlagX1);
            	panelSouth.remove(panelFlagX2);
            	panelSouth.remove(panelButtons);
            	panelSouth.add(panelFix);
            	panelSouth.add(panelButtons);
            	panelSouth.updateUI();
            	flagButtons.get(0).setEnabled(false);
            	nextButton.setEnabled(false);
            	answerButton.setEnabled(false);
            	skipLevel.setEnabled(false);
            }
		}
	}
	public void answer() { //Functionality of the answer button
		for(int i = 0; i<chosenFlags.size(); i++) {
			if (flagButtons.get(i).getName().equals(flagName) && flagButtons.get(i).isSelected()) { //If correct
				nextButton.setEnabled(true);
				responseText.setText("Correct");
				answerButton.setEnabled(false);
				flagButtons.get(i).setEnabled(false);
				skipLevel.setEnabled(false);
				//playerCorrect1.play();
			}
			else if(!flagButtons.get(i).getName().equals(flagName) && flagButtons.get(i).isSelected()) { //If wrong
            	responseText.setText("Wrong");
            	countLose++;
        		counterText.setText("Times Failed: "+countLose);
				//playerWrong1.play();
				}
			}
	}
	public void next() { //The Functionality of the Next Button
				answerButton.setEnabled(true);
		for(int i = 0; i<chosenFlags.size(); i++) { //For loop so it can check each check box
            if (flagButtons.get(i).getName().equals(flagName) && flagButtons.get(i).isSelected() && responseText.getText().equals("Correct") && chosenFlags.size() != 1) {
                chosenFlags.remove(flagIndex);
            	flagIndex = (int)(Math.random()*chosenFlags.size()-1);
              	flagButtons.remove(i);
                flagName = (chosenFlags.get(flagIndex));
                countryText.setText(flagName);
                skipLevel.setEnabled(true);
                if(countSkip == 1) {
                	skipLevel.setEnabled(false);
                }
                nextButton.setEnabled(false);
                break;
            } 
            else if(chosenFlags.size() == 1) { //This is what happens when the game ends
            	if(countLose <= 3) {
            		endLabel.setIcon(winImage);
            		//player1.stop();
            		//playerWin1.play();
            	}
            	else if(countLose > 3) {
            		endLabel.setIcon(loseImage);
            		//player1.stop();
            		//playerLose1.play();
            	}
            	panelSouth.remove(panelFlagX1);
            	panelSouth.remove(panelFlagX2);
            	panelSouth.remove(panelButtons);
            	panelSouth.add(panelFix);
            	panelSouth.add(panelButtons);
            	panelSouth.updateUI();
            	nextButton.setEnabled(false);
            	answerButton.setEnabled(false);
            	skipLevel.setEnabled(false);
            }
        }
	}
	

	public void actionPerformed(ActionEvent e) { //Action Performed Method
		
		if(e.getActionCommand().equals("answer")) {  //Calling the answer method
			answer();	
		}
		else if(e.getActionCommand().equals("next")) {  //Calling the "next" method
            next();
        }
		else if(e.getActionCommand().equals("skip")) { //Calling the skip method
			skip();
		}
		else if(e.getActionCommand().equals("switch")) { //Switches the game
			Project obj = new Project();
			obj.recall();
			frame.setVisible(false);
			/*player1.stop();
			playerWin1.stop();
			playerLose1.stop();
			playerSwitch1.play();*/
		}
		repaint();
	}


	public void run() { //Runs Project2
		new Project2();
		frame = new JFrame();
		frame.add(panelMain, BorderLayout.CENTER);
		frame.setIconImage(titleImage.getImage());
		frame.setTitle("SOUP-A MARIO COUNTRY 4");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(1250, 950);
		//player1.play();
		}
	}
