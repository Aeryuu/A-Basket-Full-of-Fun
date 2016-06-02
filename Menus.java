import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.lang.*;
import java.awt.image.*;
/**
 * The Menus class creates a JPanel for the Main Menu and Level Select screens depending on the constructor's parameter
 * pass. The Main Menu screen will have a title, a button to view instructions, a button to go to level select to play
 * the game, a button to view the high scores, and a button to exit the game. The Level Select screen will have a
 * title, 3 buttons for level difficulty, and 3 levels of gameplay for each level of difficulty. 
 *   
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 1 05.16.16 Spent 3 hours
 * 
 * @author of modification C Liu on 05.26.16
 * @version 2 05.26.16 Spent 3 hours
 * 
 * @author Top Of the Stack (Alice Z) on 05.16.16 modified by C Liu on 05.26.16
 * @version 3 05.18.16 Spent 3 hours
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
  JFrame j;
  JPanel r = this;
  private int whichMenu;
  /**
   * The class constructor has a parameter pass to see which menu is going to be set up. If the pass is 0, the Main
   * Menu panel will be set up. Otherwise, the Level Select panel will be set up.
   * @param whichMenu An int used to determine which menu panel to set up.
   */ 
  public Menus(int whichMenu)
  {
    this.whichMenu = whichMenu;
    if(whichMenu == 1)
      setUpMain();
    else
      if(whichMenu ==2)
      setUpLevel();
  }
  /** Purpose: The purpose of this method is to 
    * paint the panel. It draws the sky, some clouds, and adds the text.
    * @param g Graphics allows use to the Graphics class.
    */
  public void paintComponent (Graphics g)
  {
    
    super.paintComponent(g);
    g.setColor (Colours.skyB);
    g.fillRect(0,0,800,800);
    
    g.setColor (Color.white);
    g.fillOval(20,40,200,30);
    g.fillOval(550,40,120,30);
    
    g.fillOval(20,540,200,30);
    g.fillOval(90,560,220,40);
    g.fillOval(40,520,90,40);
    
    g.fillOval(550,540,120,30);
    g.fillOval(570,550,220,40);
    g.fillOval(610,520,90,70);
    
    for (int i=320;i<800;i=i+340)
    {
      g.fillOval(300,i,200,50);
      g.fillOval(190,i+30,300,60);
      g.fillOval(430,i+30,200,50);
      g.fillOval(310,i+50,200,50);
      g.fillOval(170,i+60,200,30);
    }
    
    g.fillOval(600,200,200,50);
    g.fillOval(490,230,300,60);
    g.fillOval(730,230,200,50);
    g.fillOval(610,250,200,50);
    g.fillOval(470,260,200,30);
    
    g.fillOval(50,200,200,50);
    g.fillOval(-60,230,300,60);
    g.fillOval(180,230,200,50);
    g.fillOval(60,250,200,50);
    g.fillOval(-80,260,200,30);
    
    try{
      BufferedImage logo = ImageIO.read(new File ("Title.jpg"));
      g.drawImage(logo,290,0,null);
      
    }
    catch(Exception e){
    }
    //clouds
    g.fillOval(0,40,70,20);
    g.fillOval(40,20,70,50);
    g.fillOval(80,30,70,20);
    g.fillOval(140,40,70,30);
    
    g.fillOval(660,20,70,50);
    g.fillOval(600,30,70,20);
    g.fillOval(660,40,70,30);
    g.fillOval(700,40,80,30);
    
  }
  /**
   * This will setup the Main Menu screen. The Main Menu screen will have a title, a button to view instructions
   * a button to go to level select to play the game, a button to view the high scores, and a button to exit the game.
   * The parameter for the inner methods is e - a reference variable for ActionEvent.
   */ 
  private void setUpMain()
  {
    JButton inst = new JButton("Instructions"),play = new JButton("Play Game");
    JButton high = new JButton("High Scores"),exit = new JButton("Exit Game");
    gbc.weighty=0;
    gbc.anchor = gbc.LINE_START;
    gbc.gridy = 1;
    add(inst,gbc);
    inst.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        new InstructionScreen();
      }});
    
    gbc.gridy=2;
    add(play,gbc);
    play.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        new Menus(1);
      }});
    
    gbc.gridy=3;
    add(high,gbc);
    high.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        HighScores h = new HighScores();
        h.setUpHighScoresPanel();
        j.remove(r);
        j.add(h);
        j.revalidate();
        j.repaint();
        
      }});
    
    gbc.gridy=4;
    add(exit,gbc);
    exit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        
        new Goodbye();
      }});
    revalidate();
    repaint();
  }
  
  /**
   * This will set up the Level Select screen. The Level Select screen will have a title, 3 buttons for level
   * difficulty, and 3 levels of gameplay for each level of difficulty. When the user clicks a level difficulty button,
   * if it is unlocked, the 3 levels of gameplay buttons will appear. If the user clicks one and it isn't locked, the
   * user will proceed to the game screen. Otherwise, an error message will pop up.
   * The parameter for the inner methods is e - a reference variable for ActionEvent.
   */ 
  private void setUpLevel()
  {
    
    JButton exit = new JButton("Back to Main Menu");
    JButton levelOne = new JButton("Level 1"), levelTwo = new JButton("Level 2"), levelThree = new JButton("Level 3");
    gbc.weighty=1;
    gbc.anchor = GridBagConstraints.LINE_START;
    gbc.gridy = 1;
    //level buttons appear to go to level xx of that difficulty 
    gbc.gridy=1;
    gbc.gridx=2;
    add(levelOne,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        new BasketFun(1,"back1", new Color (37,177,77));
      }});
    
    gbc.gridy=2;
    add(levelTwo,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        new BasketFun(2,"back2", new Color (37,177,77));
      }});
    
    gbc.gridy=3;
    add(levelThree,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        new BasketFun(3,"back3", new Color (37,177,77));
      }});
    
    gbc.gridy=4;
    add(exit,gbc);
    exit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        new Menus(0);
      }});
    
    revalidate();
    repaint();
  }
  public static void main(String[] args) { 
    Menus g=new Menus(0);
    g.j=new JFrame("A Basket Full Of Fun: Menu");
    g.j.setSize(800,800);
    g.setPreferredSize(new Dimension( 800,800));
    g.j.add(g);
    g.j.setVisible (true);
    g.setLayout(g.gbl);
    g.gbc.insets = new Insets(150,0,0,0);
    if(g.whichMenu == 0)
      g.setUpMain();
    else
      g.setUpLevel();
  }
}