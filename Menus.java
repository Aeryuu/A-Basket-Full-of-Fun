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
 * added paintComponents in order to create graphics(20 clouds), imported images,
 * chnaged constraints in order to center buttons
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 3 06.02.16 Spent 0.5 hour
 * Now, the JFrame from GameRunner is being passed in as a reference variable. It is used to switch the panels that are
 * being displayed on it.
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 3 06.04.16 Spent 2.5 hours
 * Main menu can now smoothly transition to instructions, high scores, level select, and goodbye. Also, the panel will
 * respond to pressing the "F1" key.
 * 
 * @author Top Of the Stack (Alice Z)
 * @version 3.1 06.04.16 Spent 1 hours
 * Added code into the ActionListeners of the Level 2 and Level 3 buttons so that unless the previous level is complete,
 * the user will get an error message telling them to complete the previous level.
 * 
 * @author of modification C Liu on 06.08.16
 * @version 4 06.08.16 Spent  10 mins
 * edited x values of graphics to center buttons
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
  private JFrame j;
  private static boolean levelOneDone = false, levelTwoDone = false;
  /**
   * The class constructor has a parameter pass to see which menu is going to be set up. If the pass is 0, the Main
   * Menu panel will be set up. Otherwise, the Level Select panel will be set up.
   * @param whichMenu An int used to determine which menu panel to set up.
   */ 
  public Menus(int whichMenu, JFrame jf)
  {
    super();
    setPreferredSize(new Dimension( 1000,900));
    setLayout(gbl);
    gbc.insets = new Insets(150,0,0,0);
    if(whichMenu ==0)
      setUpMain();
    else
      setUpLevel();
    j = jf;
    this.getInputMap().put(KeyStroke.getKeyStroke("F1"),
                           "pressed");
    this.getActionMap().put("pressed",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        //insert chm file Caroline
      }});
  }
  /** Purpose: The purpose of this method is to 
    * paint the panel. It draws the sky, some clouds, and adds the text.
    * @param g Graphics allows use to the Graphics class.
    */
  public void paintComponent (Graphics g)
  {
    
    super.paintComponent(g);
    g.setColor (Colours.skyB);
    g.fillRect(0,0,1000,900);
    
    g.setColor (Color.white);
    g.fillOval(120,40,200,30);
    g.fillOval(650,40,120,30);
    
    g.fillOval(120,540,200,30);
    g.fillOval(190,560,220,40);
    g.fillOval(140,520,90,40);
    
    g.fillOval(680,540,120,30);
    g.fillOval(690,550,220,40);
    g.fillOval(710,520,90,70);
    
    for (int i=320;i<800;i=i+340)
    {
      g.fillOval(400,i,200,50);
      g.fillOval(290,i+30,300,60);
      g.fillOval(530,i+30,200,50);
      g.fillOval(410,i+50,200,50);
      g.fillOval(270,i+60,200,30);
    }
    
    g.fillOval(700,200,200,50);
    g.fillOval(690,230,300,60);
    g.fillOval(830,230,200,50);
    g.fillOval(710,250,200,50);
    g.fillOval(670,260,200,30);
    
    g.fillOval(150,200,200,50);
    g.fillOval(40,230,300,60);
    g.fillOval(280,230,200,50);
    g.fillOval(160,250,200,50);
    g.fillOval(20,260,200,30);
    
    try{
      BufferedImage logo = ImageIO.read(new File ("Title.jpg"));
      g.drawImage(logo,400,0,null);
      
    }
    catch(Exception e){
    }
    //clouds
    g.fillOval(30,40,70,20);
    g.fillOval(60,20,70,50);
    g.fillOval(80,30,70,20);
    g.fillOval(140,40,70,30);
    
    g.fillOval(680,20,70,50);
    g.fillOval(620,30,70,20);
    g.fillOval(680,40,70,30);
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
    
    setSize (800, 800);
    setVisible (true);
    gbc.weighty=0;
    gbc.anchor = gbc.LINE_START;
    gbc.gridy = 1;
    add(inst,gbc);
    inst.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new InstructionScreen(j));
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=2;
    add(play,gbc);
    play.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new Menus(1,j));
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=3;
    add(high,gbc);
    high.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        HighScores h = new HighScores(j);
        h.setUpHighScoresPanel();
        j.remove(Menus.this);
        j.add(h);
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=4;
    add(exit,gbc);
    exit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new Goodbye());
        j.revalidate();
        j.repaint();
      }});
    revalidate();
    repaint();
  }
  
  public static boolean getLevelOneDone()
  {
    return levelOneDone;
  }
  
  public static void setLevelOneTrue()
  {
    levelOneDone = true;
  }
  
  public static void setLevelTwoTrue()
  {
    levelTwoDone = true;
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
    gbc.gridy=1;
    gbc.gridx=2;
    add(levelOne,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        //j.add(new BasketFun(1,"back1", new Color (37,177,77) , j));
        j.revalidate();
        j.repaint();
      }});
    
    gbc.gridy=2;
    add(levelTwo,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
       // if(levelOneDone)
         // new BasketFun(2,"back2", new Color (37,177,77), j);
       // else
          //JOptionpane
        //  System.out.println(":p");
      }});
    
    gbc.gridy=3;
    add(levelThree,gbc);
    levelOne.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
     //   if(levelTwoDone)
         // new BasketFun(3,"back3", new Color (37,177,77), j);
     //   else
          //JOptionpane
      //    System.out.println(":p");
      }});
    
    gbc.gridy=4;
    add(exit,gbc);
    exit.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        j.remove(Menus.this);
        j.add(new Menus(0,j));
        j.revalidate();
        j.repaint();
      }});
    
    revalidate();
    repaint();
  }
  
//  public static void main(String[] args) { 
//    JFrame jf=new JFrame("A Basket Full Of Fun: Menu");
//    jf.setSize(800,800);
//    
//    jf.add(new Menus(0,jf));
//    jf.setVisible (true);
//  }
}