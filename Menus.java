import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * The Menus class creates a JPanel for the Main Menu and Level Select screens depending on the constructor's parameter
 * pass. The Main Menu screen will have a title, a button to view instructions, a button to go to level select to play
 * the game, a button to view the high scores, and a button to exit the game. The Level Select screen will have a
 * title, 3 buttons for level difficulty, and 3 levels of gameplay for each level of difficulty. 
 *   
 * 
 * @author Top Of the Stack (Alice Z) on 05.16.16 
 * @version 1 05.18.16 Spent 2 hours
 * 
 * <p>
 * <b> Instance variables: </b>
 * <p>
 * <b> gbc </b> This is a reference variable to a GridBagConstraints object.
 * <p>
 * <b> gbl </b> This is a reference variable to a GridBagLayout object.
 */ 
public class Menus extends JPanel
{
  private GridBagLayout gbl = new GridBagLayout();
  private GridBagConstraints gbc = new GridBagConstraints();
  
  /**
   * The class constructor has a parameter pass to see which menu is going to be set up. If the pass is 0, the Main
   * Menu panel will be set up. Otherwise, the Level Select panel will be set up.
   * @param whichMenu An int used to determine which menu panel to set up.
   */ 
  public Menus(int whichMenu)
  {
    setLayout(gbl);
    gbc.insets = new Insets(10,10,10,10);
    if(whichMenu == 0)
      setUpMain();
    else
      setUpLevel();
  }
  
  /**
   * This will setup the Main Menu screen. The Main Menu screen will have a title, a button to view instructions
   * a button to go to level select to play the game, a button to view the high scores, and a button to exit the game.
   */ 
  private void setUpMain()
  {
    JLabel test = new JLabel("Main Menu");
    add (test,gbc);
    JButton testInst = new JButton("Instructions"),testPlay = new JButton("Play Game");
    JButton testHigh = new JButton("High Scores"),testExit = new JButton("Exit Game");
    gbc.weighty=1;
    gbc.anchor = gbc.LINE_START;
    gbc.gridy = 1;
    add(testInst,gbc);
    testInst.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //switch panel to instructions Caroline
      }});
    
    gbc.gridy=2;
    add(testPlay,gbc);
    testPlay.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //switch panel to level select Caroline make another menu panel with 1 rather than 0
      }});
    
    gbc.gridy=3;
    add(testHigh,gbc);
    testHigh.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //switch panel here to high scores Caroline remember to set up highscores panel first
      }});
    
    gbc.gridy=4;
    add(testExit,gbc);
    testExit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //switch panel here to goodbye Caroline
      }});
  }
  
  /**
   * This will set up the Level Select screen. The Level Select screen will have a title, 3 buttons for level
   * difficulty, and 3 levels of gameplay for each level of difficulty. When the user clicks a level difficulty button,
   * if it is unlocked, the 3 levels of gameplay buttons will appear. If the user clicks one and it isn't locked, the
   * user will proceed to the game screen. Otherwise, an error message will pop up.
   */ 
  private void setUpLevel()
  {
    JLabel test = new JLabel("Main Menu");
    add (test,gbc);
    JButton testEasy = new JButton("Easy"),testMed = new JButton("Medium");
    JButton testHard = new JButton("Hard"),testExit = new JButton("Back to Main Menu");
    JButton testOne = new JButton("Level 1"), testTwo = new JButton("Level 2"), testThree = new JButton("Level 3");
    gbc.weighty=1;
    gbc.anchor = gbc.LINE_START;
    gbc.gridy = 1;
    add(testEasy,gbc);
    testEasy.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //level buttons appear to go to level xx of that difficulty 
        gbc.gridy=1;
        gbc.gridx=2;
        add(testOne,gbc);
        testOne.addActionListener (new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            //switch panel to gamescreen Caroline and set up with easy, level 1
          }});
        add(testTwo,gbc);
        add(testThree,gbc);
        revalidate();
        repaint();
      }});
    
    gbc.gridy=2;
    add(testMed,gbc);
    testMed.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //if locked joptionpane
        //level buttons appear to go to level xx of that difficulty 
        add(testOne,gbc);
        testOne.addActionListener (new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            //switch panel to gamescreen Caroline and set up with medium, level 1
          }});
        add(testTwo,gbc);
        add(testThree,gbc);
      }});
    
    gbc.gridy=3;
    add(testHard,gbc);
    testHard.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //if locked joptionpane
        //level buttons appear to go to level xx of that difficulty 
        add(testOne,gbc);
        testOne.addActionListener (new ActionListener(){
          public void actionPerformed(ActionEvent e)
          {
            //switch panel to gamescreen Caroline and set up with hard, level 1
          }});
        add(testTwo,gbc);
        add(testThree,gbc);
      }});
    
    gbc.gridy=4;
    add(testExit,gbc);
    testExit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //switch panel here back to main menu Caroline
      }});
  }
}