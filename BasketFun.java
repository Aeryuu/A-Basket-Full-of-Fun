import java.awt.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;

/** The BasketFun class creates the game screen panel and contains all of the game logic. The game screen will have 5
  * food buttons that are different depending on the selected level, a pause button to pause the game midway, a
  * button to reset everything in the basket, and a check button to see if the user has fulfilled the request. These
  * buttons are created in this class. It will also add the background to the panel.
  * 
  * @author Top Of The Stack(C Liu)
  * @version 1 05.20.16 Spent 4 hours
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 2 05.20.16 Spent 3 hours
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 2 06.02.16 Spent 3 hours
  * 
  * <p>
  * <b> Instance variables: </b>
  * <p>
  * <b> levelNum </b> This int is used to store which level the user chose to play.
  * <p>
  * <b> bCol </b> This Color variable is used to store the background colour.
  * <p>
  * <b> backName </b> This String is used to store the file name of the background image being used for the level.
  * <p>
  * <b> j </b> This referene variable is used to point at the JFrame object that is created in the constructor.
  */ 

public class BasketFun extends JPanel{
  
  private int levelNum;
  private String backName;
  private Color bCol;
  JFrame j;
   String [] chars;
  
  /**The class constructor will create a JPanel that is added to a JFrame that is also created here. The buttons that
    * all levels have in common: pause, empty, and check, are made and added here as well. The layout used is flow. 
    * Depending on the level selected, the backgrounds, button creation methods, and themes are different. The if
    * statement is to determine which button creation method to call.
    * @param levelNum This int is used to store which level the user has chosen to play.
    * @param bName This String is used to store the name of the background file.
    * @param s This Color is used to store the colour of the background.
    */
  public BasketFun(int levelNum, String bName, Color s,String []chars ) { 
    super();
    this.levelNum=levelNum;
    backName=bName;
    bCol=s;
    this.chars=chars;
    System.out.println(backName);
    j=new JFrame("A Basket Full Of Fun: Level 1");
    j.setSize(1000,850);
    this.setPreferredSize(new Dimension( 1000,900));
    j.add(this);
    j.setVisible (true);
    
    FlowLayout f=new FlowLayout();
    f.setHgap(40);
    f.setAlignment (FlowLayout.LEFT);
    this.setLayout(f);
    JButton check=new JButton("CHECK!");
    JButton empty=new JButton ("Empty the basket!");
    
    JButton pause = makeButtons("Pause","Click here to pause the game!");
    add(pause);
    pause.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        //stop timer, make things appear
      }});
    
    if (levelNum==1)
    {
      makePanel1();
      generateRequest(3);
    }
    else if (levelNum==2)
    {
      makePanel2();
      generateRequest(5);
    }
    else
    {
      makePanel3();
      generateRequest(7);
    }
    add(check);
    add(empty);
    add(makeButtons("Basket","Clicking the buttons, puts food into this basket!"));
    revalidate();
    repaint();
  }
  
  private void generateRequest(int maxNum)
  {
    int randAmnt,randAmnt2;
    if(levelNum == 3)
    {
      randAmnt= randomizeAmounts(maxNum);
      randAmnt2= randomizeAmounts(maxNum);
      System.out.println("I would like to have " + randAmnt + randomizeFoods(randAmnt) + " and " + randAmnt2 + randomizeFoods(foods,randAmnt2) +".");
    }
    else
    {
      randAmnt= randomizeAmounts(maxNum);
      System.out.println("I would like to have " + randAmnt + randomizeFoods(foods,randAmnt)+".");
    }
  }
  
  private String randomizeFoods(int randAmnt)
  {
    String 
    int r = (int)(Math.random()*foods.size());
    if(randAmnt !=1 && (levelNum != 2 && (r == 4 || r == 5)))
      return " "+foods.get(r)+"s";
    return " "+foods.get(r);
  }
  
  private int randomizeAmounts(int maxNum)
  {
    int r = (int)(Math.random()*maxNum +1);
    return r;
  }
  
  /**This method will make and add the forest themed buttons to the panel for Level 1.
    * The parameter for the inner methods is e - a reference variable for ActionEvent.
    * The try/catch is to errortrap FileIO.
    */
  private void makePanel1()
  {
    JButton apple =makeButtons("Apple","Click here to drop an apple into the basket!"), orange = makeButtons("Orange","Click here to drop an orange into the basket!");
    add(apple);
    apple.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          Image test =ImageIO.read (new File ("apple.jpg"));
          ImageIcon icon=new ImageIcon(test);
          JLabel label=new JLabel(icon);
          add(label);
          revalidate();
          repaint();
        }
        catch(IOException i)
        {}
      }});
    add(orange);
    orange.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          Image test =ImageIO.read (new File ("orange.jpg"));
          ImageIcon icon=new ImageIcon(test);
          JLabel label=new JLabel(icon);
          add(label);
          revalidate();
          repaint();
        }
        catch(IOException i)
        {}
      }});
    add(makeButtons("Banana","Click here to drop a banana into the basket!"));
    add(makeButtons("Grape","Click here to drop a grape into the basket!"));
    add(makeButtons("Watermelon","Click here to drop a watermelon into the basket!"));
  }
  
  
  /**This method will draw the level backgrounds depending on the level.
    * @param g This will be used to draw the image of the background onto the panel.
    * The try/catch is to errortrap FileIO.
    */
  public void paintComponent(Graphics g)
  {
    BufferedImage b=null, c=null;
    super.paintComponent(g);
    try
    {
      b = ImageIO.read(new File (backName+".jpg"));
      g.drawImage(b,0,0,null);
  
      //for (int i=0;i<chars.length;i++)
      //{
            c=ImageIO.read(new File("Monkey"+".jpg"));
      g.drawImage(c,400,590,null);
      //}
    }
    catch(IOException e){
    }
  }
  
  /**This method will make and add the underwater themed buttons to the panel for Level 2. */
  private void makePanel2()
  {
    add(makeButtons("Red","Click here to drop a red fish into the basket!"));
    add(makeButtons("Yellow","Click here to drop a yellow fish into the basket!"));
    add(makeButtons("Green","Click here to drop a green fish into the basket!"));
    add(makeButtons("Octopus","Click here to drop an octopus into the basket!"));
    add(makeButtons("Crab","Click here to drop a crab into the basket!"));
  }
  
  /**This method will make and add the farm themed buttons to the panel for Level 3.*/
  private void makePanel3()
  {
    add(makeButtons("Tomato","Click here to drop a tomato into the basket!"));
    add(makeButtons("RedA","Click here to drop a red apple into the basket!"));
    add(makeButtons("GreenA","Click here to drop a green apple into the basket!"));
    add(makeButtons("Carrot","Click here to drop a carrot into the basket!"));
    add(makeButtons("Potato","Click here to drop a potato into the basket!"));
  }
  
  /**This method creates the buttons based off of the passed in image file's name and the text for the tool tip. The
    * method then returns the button so that it can be added to the panel.
    * 
    * @param imageName String passed in the for the image file's name.
    * @param text String passed in text for the tool tip.
    */
  private JButton makeButtons(String imageName,String text)
  {
    ImageIcon icon=new ImageIcon(this.getClass().getResource( imageName + ".jpg"));
    JButton button=new JButton(icon);
    button.setBackground(bCol);
    button.setToolTipText(text);
    return button;
  }
  
//  public void actionPerformed(ActionEvent e)
//  {
//    
//    
//  }
  
  public static void main(String[] args) { 
    String []c={"Bunny","Monkey","Panda"};
    BasketFun s= new BasketFun (1,"back1", new Color (37,177,77),c);
    //forest green and farm grass green;new Color(37,177,77)
    //ocean 0,126,255
  }
}
