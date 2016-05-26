import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * The HighScores class can create the JPanel for the High Scores screen and it has a method that updates the 
 * high scores file with a passed in String. The new score will be sorted into the high scores if it is high enough.
 * There is a button on the High Scores screen that can print the high scores. There is another button that is used to
 * go back to the Menu screen.
 *  
 * 
 * @author Top Of the Stack (Alice Z) on 05.16.16 
 * @version 1 05.18.16 Spent 1.5 hours
 * 
 * <p>
 * <b> Instance variables: </b>
 * <p>
 * <b> MAX_HIGHSCORES </b> This final int is used to store the maximum number of high scores in the file.
 * <p>
 * <b> highScores </b> This String 2D array will be used to store the individual parts of the high scores already
 * stored in file.
 */ 
public class HighScores extends JPanel
{
  private final int MAX_HIGHSCORES = 10;
  private String [][] highScores = new String[MAX_HIGHSCORES][3];
  /**
   * The class constructor will read in the high scores from the file and store them in the class's String array.
   */ 
  public HighScores()
  {
    try
    {
      BufferedReader in = new BufferedReader(new FileReader ("Input.txt"));
      for(int x = 0;x<MAX_HIGHSCORES;x++)
      {
        String line = in.readLine();
        if(line == null)
        {
          highScores[x][0] = "";
          highScores[x][1] = "";
          highScores[x][2] = "";
        }
        else
          highScores[x] = line.split(" ");
      }
      for(int x = 0;x < MAX_HIGHSCORES;x++)
      {
        for(int y = 0;y < 3;y++)
        {
          System.out.print(highScores[x][y] + " ");
        }
        System.out.println();
      }
    }
    catch(IOException e)
    {}
  }
  
   /**
   * This will take in a String array for the new score and sort it into the high scores. The updated high scores will
   * then be printed in the file. Nothing will change if new score isn't high enough.
   * @param newScore This String array will contain the individual parts of the new high score entry.
   */ 
  public void update(String [] newScore) // already split
  {
    for(int x = 0;x < MAX_HIGHSCORES;x++)
    {
      if(highScores[x][1] == "")
      {
        highScores[x] = newScore;
        break;
      }
      else
        if(Integer.parseInt(newScore[1]) > Integer.parseInt(highScores [x][1]))
      {
        String [] temp = highScores[x];
        highScores[x] = newScore;
        newScore = temp;
      }
    }
    try
    {
      PrintWriter out = new PrintWriter(new FileWriter("Input.txt"));
      for(int x = 0;x < MAX_HIGHSCORES;x++)
      {
        for(int y = 0;y < 3;y++)
        {
          out.print(highScores[x][y] + " ");
        }
        out.println();
      }
      out.close();
    }
    catch(IOException e)
    {}
  }
  
    /**
   * This will setup the High Scores screen. The High Scores screen will have a title, a button to print the high
   * scores, a button to go back to main menu, and it will display the list of high scores.
   * The parameter for the inner methods is e - a reference variable for ActionEvent.
   */ 
  private void setUpHighScoresPanel()
  {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel test = new JLabel("High Scores");
    JLabel testName= new JLabel("Name"),testScore= new JLabel("Score"),testLevel= new JLabel("Level");
    JButton testExit = new JButton("Back To Menu"), testPrint = new JButton("Print High Scores");
    
    setLayout(gbl);
    gbc.insets = new Insets(10,10,10,10);
    gbc.gridx=0;
    gbc.gridy=0;
    gbc.gridwidth = 5;
    gbc.anchor = gbc.CENTER;
    add (test,gbc);
    gbc.gridwidth = 1;
    
    gbc.weighty = 1;
    gbc.anchor = gbc.LINE_START;
    gbc.gridy=1;
    add(testName,gbc);
    
    gbc.gridx=1;
    add(testScore,gbc);gbc.gridx=0;
    
    gbc.gridx=2;
    add(testLevel,gbc);
    
    for(int x = 2; x<=11;x++)
    {
      gbc.gridx=0;
      gbc.gridy=x;
      testName = new JLabel((x-1) + ". " + highScores[x-2][0]);
      add(testName,gbc);
      
      gbc.gridx=1;
      testScore = new JLabel(highScores[x-2][1]);
      add(testScore,gbc);
      
      gbc.gridx=2;
      testLevel = new JLabel(highScores[x-2][2]);
      add(testLevel,gbc);
    }
    gbc.gridx=4;
    gbc.gridy=7;
    add(testPrint,gbc);
    testPrint.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //print here
      }});
    
    gbc.gridy=9;
    add(testExit,gbc);
    testExit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //switch panel here to menu Caroline
      }});
  }
}