import java.awt.*;
import java.awt.event.*;
import java.io.File; //UNUSED UNTIL IMPORTED EXTENDED LIBRARY
import javax.swing.*;
//import jaco.mp3.player.MP3Player; //UNCOMMENT ONLY IF IMPORTED EXTENDED LIBRARY
import java.util.ArrayList;

/*A bunch of notes:
 * I've commented these lines of code: 5,76-82,87-102,272,287,288,292,293,310,321,344,349,361 and 380-383  because they require the extended library i have given on the zip file.
 * I really can't recommend you enough to import the library file i've given you.
 * I think this also works if you put it on a terminal. (javac -classpath pathToLibrary MyClassName.java)
 * I've also commented a lot of stuff to make it easier to understand, although i don't know if i did a good enough job with it.
 * */

public class Project extends JFrame implements ActionListener { //This is the first Game
	
	//Initializing starts here
	//----------------------------------------------------------- //Arrays
	private String[] tempFlagName = {"Albania", "Macedonia", "Serbia", "Greece", "Nigeria", "Iraq", "China", "Algeria", "Germany", "France", "Spain", "UnitedKingdom", "Russia",
			"UnitedStates", "Ireland", "Austria", "Sweden", "Kosovo", "Jamaica", "Mexico", "Japan", "Ukraine", "SouthKorea", "India", "Norway"};
	private ArrayList<String> flagNames;
	private ArrayList<String> chosenFlags;
	private ArrayList<String> chosenFlagsCopy;
 	private ArrayList<JCheckBox> flagList;
 	//----------------------------------------------------------- //JProgressBar
 	private JProgressBar progressBar;
	//----------------------------------------------------------- //JFrame
	private JFrame frame;
	//----------------------------------------------------------- //JTextFields
	private JTextField responseText;
	//----------------------------------------------------------- //JLabels
	private JLabel responseLabel;
	private JLabel flagLabel;
	private JLabel helpLabel;
	private JLabel leftMonkey;
	private JLabel rightMonkey;
	private JLabel blankLabelRight;
	private JLabel blankLabelUnderRight;
	private JLabel blankLabelLeft;
	private JLabel blankLabelUnderLeft;
	private JLabel blank2Label;
	//----------------------------------------------------------- //JButtons
	private JButton answerButton;
	private JButton nextButton;
	private JButton skipLevel;
	private JButton switchButton;
	//----------------------------------------------------------- //ButtonGroups
	private ButtonGroup cbg;
	//----------------------------------------------------------- //JPanels
	private JPanel panelMain;
	private JPanel panelAnswer;
	private JPanel panelHelp;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JPanel panelCenter;
	private JPanel panelEast;	
	private JPanel panelAnsButtons;
	private JPanel panelMonkey;
	//----------------------------------------------------------- //ImageIcons
	private ImageIcon differentFlag;
	private ImageIcon monkeyGif;
	private ImageIcon monkeyWin;
	private ImageIcon monkeyFail;
	private ImageIcon blankIcon; //Alignment
	private ImageIcon blank2Icon; //Alignment
	private ImageIcon titleImage;
	//----------------------------------------------------------- //String
	private String flagName;
	//----------------------------------------------------------- //Integers
	private int flagIndex = (int)Math.random()*9;
	private int count = 0;
	private int countSkip = 0;
	//----------------------------------------------------------- //Boolean
	private boolean firstTime = true;
	//----------------------------------------------------------- //MP3Players
	/*MP3Player player;
	MP3Player playerWin;
	MP3Player playerLose;
	MP3Player playerCorrect;
	MP3Player playerWrong;
	MP3Player playerSkip;
	MP3Player playerSwitch;*/
	
	public Project(){
		//INSTANTIATIONS
		//----------------------------------------------------------- // Music Player (UNCOMMENT ONLY AFTER IMPORTING NEW LIBRARY)
		/*player = new MP3Player();
		player.setRepeat(true);
		player.addToPlayList(new File("DonkeyMusic/Donkey.mp3"));
		player.play();
		playerWin = new MP3Player();
		playerWin.addToPlayList(new File("DonkeyMusic/DonkeyWin.mp3"));
		playerLose = new MP3Player();
		playerLose.addToPlayList(new File("DonkeyMusic/DonkeyLose.mp3"));
		playerCorrect = new MP3Player();
		playerCorrect.addToPlayList(new File("DonkeyMusic/DonkeyCorrectChoice.mp3"));
		playerWrong = new MP3Player();
		playerWrong.addToPlayList(new File("DonkeyMusic/DonkeyWrongChoice.mp3"));
		playerSkip = new MP3Player();
		playerSkip.addToPlayList(new File("DonkeyMusic/DonkeySkipLevel.mp3"));
		playerSwitch = new MP3Player();
		playerSwitch.addToPlayList(new File("MarioMusic/MarioSwitch.mp3"));*/
		//----------------------------------------------------------- //JProgressBar Instantiation
		progressBar = new JProgressBar(0,10);
		//----------------------------------------------------------- //CheckBoxGroup Instantiation
		cbg = new ButtonGroup();
		//----------------------------------------------------------- //ArrayList Instantiations
		flagNames = new ArrayList<String>();
		chosenFlags = new ArrayList<String>();
		flagList = new ArrayList <JCheckBox>();
		chosenFlagsCopy = new ArrayList<String>();
		//----------------------------------------------------------- //Button Instantiations
		answerButton = new JButton("Check for Answer");
		nextButton = new JButton("Next Question");
		skipLevel = new JButton("Skip Level");
		switchButton = new JButton("Switch Game");
		nextButton.setEnabled(false);
		//----------------------------------------------------------- //JTextField Instantiation
		responseText = new JTextField(7);
		//----------------------------------------------------------- //JLabel Instantiations
		responseLabel = new JLabel("Answer: ");
		leftMonkey = new JLabel();
		rightMonkey = new JLabel();
		flagLabel = new JLabel();
		blankLabelRight = new JLabel();
		blankLabelUnderRight = new JLabel();
		blankLabelLeft = new JLabel();
		blankLabelUnderLeft = new JLabel();
		blank2Label = new JLabel();
		helpLabel = new JLabel("Help: ");
		//----------------------------------------------------------- //JPanel Instantiations
		panelAnswer = new JPanel(); 
		panelHelp = new JPanel();
		panelSouth = new JPanel();
		panelWest = new JPanel();
		panelEast = new JPanel();
		panelMain = new JPanel();
		panelCenter = new JPanel();
		panelAnsButtons = new JPanel();
		panelMonkey = new JPanel();
		//----------------------------------------------------------- //ImageIcon Instantiation
		monkeyGif = new ImageIcon("DonkeyKong/DonkeyKong.gif");
		monkeyWin = new ImageIcon("DonkeyKong/MonkeyWin.gif");
		monkeyFail = new ImageIcon("DonkeyKong/MonkeyFail.gif");
		blankIcon = new ImageIcon("Blanks/blank.png");
		blank2Icon = new ImageIcon("Blanks/blank2.png");
		titleImage = new ImageIcon("DonkeyKong/Title.jpeg");
		//----------------------------------------------------------- //Frame Instantiation
		frame = new JFrame();
		//----------------------------------------------------------- 
		//CONFIGURATIONS
		//----------------------------------------------------------- //Adding from the Array to the ArrayList
		for(int i = 0; i<25; i++)
			flagNames.add(tempFlagName[i]);		
		//----------------------------------------------------------- //Adding 10 randomly generated flags
		for(int i = 0; i<10; i++) {
			int random = (int)(Math.random()*flagNames.size());
			chosenFlags.add(flagNames.get(random));
			flagNames.remove(random);
		}
		//----------------------------------------------------------- //First Generated flag
		flagName = (chosenFlags.get((int)(flagIndex)));
		//----------------------------------------------------------- //Making a Dummy array so it doesn't interfere with action performed when we remove elements from it
		for (int i = 0; i<10; i++) {
			chosenFlagsCopy.add(chosenFlags.get(i));
		}
		//----------------------------------------------------------- //Creating Check Boxes
		for (int i = 0; i<10; i++) {
			int random = (int)(Math.random()*chosenFlagsCopy.size()-1);
			flagList.add(new JCheckBox(chosenFlagsCopy.get(random)));
			chosenFlagsCopy.remove(random);
			}
		//----------------------------------------------------------- //ButtonGroup Configuration
		for (int i=0; i<10; i++)
			cbg.add(flagList.get(i));
		//----------------------------------------------------------- //ImageIcon Instantiation 2
		differentFlag = new ImageIcon("FlagsAndImages/"+flagName.toLowerCase()+".jpg");
		//----------------------------------------------------------- //Buttons Configuration
		answerButton.setActionCommand("answer");
		answerButton.addActionListener(this);
		nextButton.setActionCommand("next");
		nextButton.addActionListener(this);
		skipLevel.setActionCommand("skip");
		skipLevel.addActionListener(this);
		switchButton.setActionCommand("switch");
		switchButton.addActionListener(this);
		//----------------------------------------------------------- //JProgressBar Configuration
		progressBar.setMaximumSize(new Dimension(1000, 25));
		progressBar.setStringPainted(true);
		//----------------------------------------------------------- //JTextFields Configuration
		responseText.setEditable(false);
		responseText.setMaximumSize(new Dimension(150, 100));
		//----------------------------------------------------------- //Labels Configuration
		flagLabel.setIcon(differentFlag);
		rightMonkey.setIcon(monkeyGif);
		leftMonkey.setIcon(monkeyGif);
		blankLabelRight.setIcon(blankIcon);
		blankLabelUnderRight.setIcon(blankIcon);
		blankLabelLeft.setIcon(blankIcon);
		blankLabelUnderLeft.setIcon(blankIcon);
		blank2Label.setIcon(blank2Icon);
		//----------------------------------------------------------- //Answer Panel (Includes the Response Label and the JTextField Response)
		panelAnswer.setLayout(new BoxLayout(panelAnswer, BoxLayout.X_AXIS));
		panelAnswer.add(responseLabel);
		panelAnswer.add(responseText);
		//----------------------------------------------------------- //Answer Buttons Panel
		panelAnsButtons.setLayout(new BoxLayout(panelAnsButtons, BoxLayout.X_AXIS));
		panelAnsButtons.add(answerButton);
		panelAnsButtons.add(nextButton);
		//----------------------------------------------------------- //South Panel
		panelSouth.setLayout(new BoxLayout(panelSouth, BoxLayout.Y_AXIS));
		panelSouth.add(panelAnsButtons);
		panelSouth.add(panelAnswer);
		//----------------------------------------------------------- //Help Panel (Includes 50/50 and Skip Level)
		panelHelp.setLayout(new BoxLayout(panelHelp, BoxLayout.X_AXIS));
		panelHelp.add(helpLabel);
		panelHelp.add(skipLevel);
		//----------------------------------------------------------- //Left Alignment Panel
		panelMonkey.setLayout(new BoxLayout(panelMonkey, BoxLayout.X_AXIS));
		panelMonkey.add(leftMonkey);
		panelMonkey.add(blank2Label);
		//----------------------------------------------------------- //West Panel
		panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
		panelWest.add(panelMonkey);
		panelWest.add(blankLabelLeft);
		for (int i = 0; i<5; i++)
			panelWest.add(flagList.get(i));
		panelWest.add(blankLabelUnderLeft);
		panelWest.add(panelHelp);
		//----------------------------------------------------------- //East Panel
		panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
		panelEast.add(rightMonkey);
		panelEast.add(blankLabelRight);
		for (int i = 5; i<10; i++)
			panelEast.add(flagList.get(i));
		panelEast.add(blankLabelUnderRight);
		panelEast.add(switchButton);
		//----------------------------------------------------------- //Center Panel
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.add(flagLabel);
		panelCenter.add(progressBar);
		//----------------------------------------------------------- //Main Panel (Basically getContentPane but i just prefer to use it as a main panel)
		panelMain.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		panelMain.setLayout(new BorderLayout());
		panelMain.add(panelCenter, BorderLayout.CENTER);
		panelMain.add(panelSouth, BorderLayout.SOUTH); 
		panelMain.add(panelWest, BorderLayout.WEST);
		panelMain.add(panelEast, BorderLayout.EAST);
		//----------------------------------------------------------- //Frame Configuration (I prefer to put it on the constructor so i can have a nice clean main method)
		frame.add(panelMain, BorderLayout.CENTER);
		frame.setIconImage(titleImage.getImage());
		frame.setTitle("Monkey Country 65 (Includes Monkeys!!!)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack(); //Looked better than with a set size.
		
		//------------------------------------------------------------
	}
	public void skip() { //Functionality of the skip level button
		countSkip++;
		for(int i = 0; i<chosenFlags.size(); i++) {
            if (flagList.get(i).getText().equals(flagName) && chosenFlags.size() != 1) {
                chosenFlags.remove(flagIndex);
            	flagIndex = (int)(Math.random()*chosenFlags.size()-1);
                flagList.get(i).setEnabled(false);
              	flagList.remove(i);
                flagName = (chosenFlags.get(flagIndex));
                flagLabel.setIcon(new ImageIcon("FlagsAndImages/"+flagName+".jpg"));
                skipLevel.setEnabled(false);
                nextButton.setEnabled(false);
                //playerSkip.play();
                if (firstTime) {
					count++;
					progressBar.setValue(count);
					progressBar.setString("First Time Guesses:" +count+"/10");
				}
                firstTime = true;
                break;
                }
            else if(chosenFlags.size() == 1) { //This is what happens when the game ends
            	count++;
				progressBar.setValue(count);
				progressBar.setString("First Time Guesses:" +count+"/10");
            	if(count > 5) {
            		flagLabel.setIcon(monkeyWin);
            		//player.stop();
            		//playerWin.play();
            	}
            	else if(count <= 5) {
            		flagLabel.setIcon(monkeyFail);
            		//player.stop();
            		//playerLose.play();            		
            	}
            	flagList.get(0).setEnabled(false);
            	nextButton.setEnabled(false);
            	answerButton.setEnabled(false);
            	skipLevel.setEnabled(false);
            }
		}
	}
	public void answer() { //Functionality of the answer button
		for(int i = 0; i<chosenFlags.size(); i++) {
			if (flagList.get(i).getText().equals(flagName) && flagList.get(i).isSelected()) { //If correct
				nextButton.setEnabled(true);
				responseText.setText("Correct");
				answerButton.setEnabled(false);
				flagList.get(i).setEnabled(false);
				skipLevel.setEnabled(false);
				//playerCorrect.play();
				if (firstTime) {
					count++;
					progressBar.setValue(count);
					progressBar.setString("First Time Guesses:" +count+"/10");
				}
				firstTime = true;
			}
			else if(!flagList.get(i).getText().equals(flagName) && flagList.get(i).isSelected()) { //If wrong
            	responseText.setText("Wrong");
				firstTime = false;
				//playerWrong.play();
				}
			}
	}
	public void next() { //The Functionality of the Next Button
				answerButton.setEnabled(true);
		for(int i = 0; i<chosenFlags.size(); i++) { //For loop so it can check each check box
            if (flagList.get(i).getText().equals(flagName) && flagList.get(i).isSelected() && responseText.getText().equals("Correct") && chosenFlags.size() != 1) {
                chosenFlags.remove(flagIndex);
            	flagIndex = (int)(Math.random()*chosenFlags.size()-1);
              	flagList.remove(i);
                flagName = (chosenFlags.get(flagIndex));
                flagLabel.setIcon(new ImageIcon("FlagsAndImages/"+flagName+".jpg"));
                skipLevel.setEnabled(true);
                if(countSkip == 1) {
                	skipLevel.setEnabled(false);
                }
                nextButton.setEnabled(false);
                break;
            } 
            else if(chosenFlags.size() == 1) { //This is what happens when the game ends
            	if(count > 5) {
            		flagLabel.setIcon(monkeyWin);
            		//player.stop();
            		//playerWin.play();
            	}
            	else if(count <= 5) {
            		flagLabel.setIcon(monkeyFail);
            		//player.stop();
            		//playerLose.play();
            	}
            	nextButton.setEnabled(false);
            	answerButton.setEnabled(false);
            	skipLevel.setEnabled(false);
            }
        }
	}
	
	public void recall() { //Recalls Project1 from Project2 (this allows for switching from P2 to P1)
		frame.setVisible(true);
		//player.play();
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
		else if(e.getActionCommand().equals("switch")) { //Switches the game by activating the run method in Project2
			Project2 obj = new Project2();
			obj.run();
			frame.setVisible(false);
			/*player.stop();
			playerWin.stop();
			playerLose.stop();
			playerSwitch.play();*/
		}
		repaint();
	}

	
	public static void main(String[] args){ //Nice clean Main method that calls everything (felt like putting stuff on the main method looked convoluted so i'm trying to avoid it as much as possible)
		
		new Project(); 
	}	
}
