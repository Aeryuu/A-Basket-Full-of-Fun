import java.awt.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.awt.event.*;
import javax.swing.Timer;

/** The BasketFun class creates the game screen panel and contains all of the game logic. The game screen will have 5
  * food buttons that are different depending on the selected level, a pause button to pause the game midway, a
  * button to reset everything in the basket, and a check button to see if the user has fulfilled the request. These
  * buttons are created in this class. It will also add the background to the panel.
  * 
  * @author Top Of The Stack(C Liu)
  * @version 1 05.18.16 Spent 4 hours
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 2 05.20.16 Spent 3 hours
  * Added actionListeners to some food buttons. Now, a picture of the food they represent appears onto the screen.
  * The constructor now only takes in the level number rather than both the level difficulty and level number. The
  * constructor will make the level panel depending on the level number. There are various themes for each level. Level
  * 1 is forest themed, level 2 is ocean themed, and level 3 is farm themed.
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 3 06.02.16 Spent 2 hours
  * Added methods to generate requests, randomize amounts of fruits, and randomize foods. Each level generates a
  * different kind of food and has a different maximum amount. The constructor now calls the method to generate the
  * requests after setting up the level panel. Added a String array to store the names of the foods to help with the
  * randomization of the foods.
  * 
  * @author of modifications by Top of The Stack (Caroline L)
  * @version 4 06.02.16 Spent  hours
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 4.1 06.04.16 Spent 0.5 hours
  * The constructor now has the JFrame from GameRunner being passed in as a reference variable.
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 5 06.05.16 Spent 3 hours
  * Now, when the panel is created, the images of the food and the amount of it are already in the basket. Each time the
  * user presses the button, the number of that food increases. Empty the Basket button is now functional and when
  * pressed, all the added food values are set to 0. The method to randomize foods is fully functional as well. The
  * method to generate requests has been modified so that the food amounts needed to fulfill the level are stored under
  * the new variable foodRequest. The processing for the Check button is being worked on and the method generateRequest
  * is being modified to fit with it.
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 5.1 06.06.16 Spent 0.5 hours
  * Check button is now fully functioning and will unlock and move on to the next level if a certain amount of requests
  * is fulfilled.
  * 
  * @author of modifications by Top of The Stack (Alice Z)
  * @version 6 06.07.16 Spent 4 hours
  * All levels are working, buttons are now located on the basket rather than at the top of the screen, all buttons
  * except pause are working, and the transitions between levels are smooth. Added an instance variable used to store
  * the user's name.
  * 
  * @author of modifications by Top of The Stack (Caroline L)
  * @version 7 06.09.16 Spent 1 hour
  * addition of the opening of the CHM file, try catch added in order to errortap opening of CHM.
  * Added Timer and added implementation of  pause button and score creation. Creation of pause method. 
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
  * <b> j </b> This reference variable is used to point at the JFrame object that is created in the constructor.
  * <p>
  * <b> chars </b> This String array contains the names of the animal JPG's used in our game.
  * <p>
  * <b> foods </b> This String array contains the names of the foods.
  * <p>
  * <b> randAmnt1 </b> This int is used to store the first randomly generated amount of foods. Used in all levels.
  * <p>
  * <b> randAmnt2 </b> This int is used to store the second randomly generated amount of foods. Used only in level 3.
  * <p>
  * <b> requestNum </b> This int stores the number of requests that the user has already completed.
  * <p>
  * <b> user </b> This String is used to store the name of the user.
  * <p>
  * <b> s </b> This SpringLayout reference variable is used to refer to a spring layout object and is used to format
  * the game screen.
  * <p>
  * <b> foodCount </b> This int array is used to store the amount of food that the user clicks.
  * <p>
  * <b> foodRequest </b> This int array is used to store the amount of food that is needed to fulfill the request. If
  * foodCount equals foodRequest, then, the user fulfills a request.
  * <p>
  * <b> requestText </b> This JLabel is used to display what each character wants onto the screen.
  */ 

public class BasketFun extends JPanel{
  Timer timer;
  private int levelNum, randAmnt1, randAmnt2, requestNum = 0;
  private String backName, user;
  private Color bCol;
  private JFrame j;
  private String [] chars, foods = new String [5];
  private SpringLayout s;
  private int [] foodCount = {0,0,0,0,0}, foodRequest = new int[5];
  private JLabel requestText;
  
  /**The class constructor will create a JPanel that is added to a JFrame that is also created here. The buttons that
    * all levels have in common: pause, empty, and check, are made and added here as well. The layout used is flow. 
    * Depending on the level selected, the backgrounds, button creation methods, and themes are different. The if
    * statement is to determine which button creation method to call. The inner method is to open the chm file when
    * the user presses F1. The try catch used in this method is used to  errotrap the opening of the CHM.
    * The parameter for the inner methods is e - a reference variable for ActionEvent.
    * @param levelNum This int is used to store which level the user has chosen to play.
    * @param bName This String is used to store the name of the background file.
    * @param s This Color is used to store the colour of the background.
    */
  public BasketFun(int level, String bName, Color w, JFrame jf, String userName) { 
    super();
    user = userName;
    levelNum=level;
    backName=bName;
    bCol=w;
    j = jf;
    s = new SpringLayout();
    setLayout(s);
    this.getInputMap().put(KeyStroke.getKeyStroke("F1"),"pressed");
    this.getActionMap().put("pressed",new AbstractAction() {
      public void actionPerformed(ActionEvent e) {
        try 
        {
         Runtime.getRuntime().exec("hh.exe GameHelp.chm");
        }
        catch(IOException i)
        {
          System.out.println("HI");
        }
      }});
    JButton check=new JButton("CHECK!"), empty=new JButton ("Empty the basket!"),pause = makeButtons("Pause","Click here to pause the game!");
    s.putConstraint (s.NORTH, pause, 0, s.WEST, this);
    add(pause);
    pause.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
       pause();
      }});
    int maxNum;
    if (levelNum==1) //3 requests
    {
      foods[0] = "Apple";
      foods[1] = "Orange";
      foods[2] = "Banana";
      foods[3] = "Grape";
      foods[4] = "Watermelon";
      makePanel1();
      maxNum = 3;
    }
    else if (levelNum==2) //5 requests
    {
      foods[0] = "Red";
      foods[1] = "Yellow";
      foods[2] = "Green";
      foods[3] = "Octopus";
      foods[4] = "Crab";
      makePanel2();
      maxNum = 5;
    }
    else //5 requests
    {
      foods[0] = "Tomato";
      foods[1] = "RedA";
      foods[2] = "GreenA";
      foods[3] = "Carrot";
      foods[4] = "Potato";
      makePanel3();
      maxNum = 7;
    }
    generateRequest(maxNum);
    s.putConstraint (s.NORTH, check, 220, s.NORTH, this);
    s.putConstraint (s.WEST, check, 350, s.WEST, this);
    add(check);
    check.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        for(int x = 0;x < 5;x++)
        {
          System.out.println(foodCount[x] + " count");
          System.out.println(foodRequest[x] + " request");
        }
        if(foodAreSame())
        {
          requestNum++;
          remove(requestText);
          revalidate();
          repaint();
          if(requestNum ==3 && levelNum == 1)
          {
            if(!Menus.getLevelOneDone())
            {
              Menus.setLevelOneTrue();
              System.out.println(Menus.getLevelOneDone());
            }
              j.remove(BasketFun.this);
              j.add(new BasketFun(2,"back2", new Color (0,126,255), j, user));
          }
          else if (requestNum == 5)
          {
            if(levelNum == 2)
            {
            if(!Menus.getLevelTwoDone())
            {
              Menus.setLevelTwoTrue();
              System.out.println(Menus.getLevelTwoDone());
            }
              j.remove(BasketFun.this);
              j.add(new BasketFun(3,"back3", new Color (37,177,77), j, user));
            }
            else
              if(levelNum ==3)
            {
              //String updateScore = user + " " + score + " " + levelNum;
              HighScores h = new HighScores(j);
              //h.update(updateScore.split());
              h.setUpHighScoresPanel();
              j.remove(BasketFun.this);
              j.add(h);
            }
          }
          else
          {
            for(int x = 0;x < 5;x++)
              foodRequest[x]=0;
            generateRequest(maxNum);
          }
          j.revalidate();
          j.repaint();
        }
        else
        {
          timer=new Timer(3000,this);
          timer.start();
          remove(requestText);
          JLabel text = new JLabel("That isn't right! Try again!");
          if(levelNum == 1)
          {
            s.putConstraint (s.NORTH, requestText,640, s.NORTH, BasketFun.this);
            s.putConstraint (s.WEST, requestText, 560, s.WEST, BasketFun.this);
          }
          else if(levelNum == 2)
          {
            s.putConstraint (s.NORTH, requestText,500, s.NORTH, BasketFun.this);
            s.putConstraint (s.WEST, requestText, 540, s.WEST, BasketFun.this);
          }
          else
          {
            s.putConstraint (s.NORTH, text,470, s.NORTH, BasketFun.this);
            s.putConstraint (s.WEST, text, 540, s.WEST, BasketFun.this);
          }
          add(text);
          revalidate();
          repaint();
          timer.stop();
          remove(text);
          if(levelNum == 1)
          {
            s.putConstraint (s.NORTH, requestText,640, s.NORTH, BasketFun.this);
            s.putConstraint (s.WEST, requestText, 560, s.WEST, BasketFun.this);
          }
          else if(levelNum == 2)
          {
            s.putConstraint (s.NORTH, requestText,500, s.NORTH, BasketFun.this);
            s.putConstraint (s.WEST, requestText, 540, s.WEST, BasketFun.this);
          }
          else
          {
            s.putConstraint (s.NORTH, requestText,500, s.NORTH, BasketFun.this);
            s.putConstraint (s.WEST, requestText, 540, s.WEST, BasketFun.this);
          }
          add(requestText);
          revalidate();
          repaint();
        }
        for(int x = 0;x < 5;x++)
          foodCount[x] = 0;
      }});
    s.putConstraint (s.NORTH, empty, 300, s.NORTH, this);
    s.putConstraint (s.WEST, empty, 300, s.WEST, this);
    add(empty); 
    empty.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        for(int x = 0;x < 5;x++)
          foodCount[x] = 0;
        revalidate();
        repaint();
      }});
    revalidate();
    repaint();
  }
  
  public void actionPerformed(ActionEvent a) {
    timer.stop();
  }
  public boolean foodAreSame()
  {
    for(int x= 0;x < 5;x ++)
      if(foodCount[x]!=foodRequest[x])
      return false;
    return true;
  }
  private void generateRequest(int maxNum)
  {
    String randFood, randFood2;
    if(levelNum == 3)
    {
      randAmnt1= randomizeAmounts(maxNum);
      randFood = randomizeFoods(randAmnt1);
      if(randFood.indexOf("Tomato") >= 0)
        foodRequest[0] = randAmnt1;
      else if(randFood.indexOf("Red")>=0)
      {
        foodRequest[1]=randAmnt1;
        if(randFood.indexOf("s")>=0)
          randFood = " Red Apples";
        else
          randFood = " Red Apple";
      }
      else if(randFood.indexOf("Green")>=0)
      {
        foodRequest[2]=randAmnt1;
        if(randFood.indexOf("s")>=0)
          randFood = " Green Apples";
        else
          randFood = " Green Apple";
      }
      else if(randFood.indexOf("Carrot")>=0)
        foodRequest[3]=randAmnt1;
      else
        foodRequest[4]=randAmnt1;
      randAmnt2= randomizeAmounts(maxNum);
      randFood2 = randomizeFoods(randAmnt2);
      if(randFood2.indexOf("Tomato") >= 0)
        foodRequest[0] = randAmnt2;
      else if(randFood2.indexOf("Red")>=0)
      {
        foodRequest[1]=randAmnt2;
        if(randFood2.indexOf("s")>=0)
          randFood2 = " Red Apples";
        else
          randFood2 = " Red Apple";
      }
      else if(randFood2.indexOf("Green")>=0)
      {
        foodRequest[2]=randAmnt2;
        if(randFood2.indexOf("s")>=0)
          randFood2 = " Green Apples";
        else
          randFood2 = " Green Apple";
      }
      else if(randFood2.indexOf("Carrot")>=0)
        foodRequest[3]=randAmnt2;
      else
        foodRequest[4]=randAmnt2;
      System.out.println("I would like to have " + randAmnt1 + randFood + " and " + randAmnt2 + randFood2 +".");
      requestText = new JLabel("<html><body>I would like to have<br>" + randAmnt1 + randFood + " and <br>" + randAmnt2 + randFood2 +".</body></html>");
      s.putConstraint (s.NORTH, requestText,460, s.NORTH, BasketFun.this);
      s.putConstraint (s.WEST, requestText, 570, s.WEST, BasketFun.this);
      add(requestText);
    }
    else
    {
      randAmnt1= randomizeAmounts(maxNum);
      randFood = randomizeFoods(randAmnt1);
      if(randFood.indexOf("Apple") >= 0 || randFood.indexOf("Red") >= 0 )
        foodRequest[0] = randAmnt1;
      else if(randFood.indexOf("Orange")>=0|| randFood.indexOf("Yellow") >= 0 )
        foodRequest[1]=randAmnt1;
      else if(randFood.indexOf("Banana")>=0|| randFood.indexOf("Green") >= 0 )
        foodRequest[2]=randAmnt1;
      else if(randFood.indexOf("Grape")>=0|| randFood.indexOf("Octo") >= 0 )
        foodRequest[3]=randAmnt1;
      else
        foodRequest[4]=randAmnt1;
      System.out.println("I would like to have " + randAmnt1 + randFood +".");
      requestText = new JLabel("<html><body>I would like to have <br>" + randAmnt1 + randFood +".</body><html>");
      if(levelNum ==1)
      {
        s.putConstraint (s.NORTH, requestText,640, s.NORTH, BasketFun.this);
        s.putConstraint (s.WEST, requestText, 560, s.WEST, BasketFun.this);
      }
      else
      {
        s.putConstraint (s.NORTH, requestText,415, s.NORTH, BasketFun.this);
        s.putConstraint (s.WEST, requestText, 180, s.WEST, BasketFun.this);
      }
      add(requestText);
    }
  }
  
  private String randomizeFoods(int randAmnt)
  {
    int r = (int)(Math.random()*5);
    if(foodRequest[r] != 0)
    {
      System.out.println("redone");
      return randomizeFoods(randAmnt);
    }
    if(randAmnt !=1 && (levelNum != 2 || (r == 4)))
    {
      return " "+foods[r]+"s";
    }
    if(levelNum == 2)
    {
      if(r == 0 || r == 1 || r == 2)
        return " " + foods[r] + " Fish";
      else
        if(randAmnt > 1)
        return " Octopi";
      else
        return " Octopus";
    }
    return " "+foods[r];
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
    JButton apple =makeButtons(foods[0],"Click here to drop an apple into the basket!"), orange = makeButtons(foods[1],"Click here to drop an orange into the basket!");
    JButton banana =makeButtons(foods[2],"Click here to drop a banana into the basket!"), grape = makeButtons(foods[3],"Click here to drop a grape into the basket!");
    JButton watermelon =makeButtons(foods[4],"Click here to drop a watermelon into the basket!");
    
    s.putConstraint (s.WEST, apple, 460, s.WEST, this);
    s.putConstraint(s.NORTH, apple,110, s.NORTH, this);
    add(apple);
    apple.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[0]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, orange, 460, s.WEST, this);
    s.putConstraint(s.NORTH, orange,300, s.NORTH, this);
    add(orange);
    orange.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      { foodCount[1]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, banana, 600, s.WEST, this);
    s.putConstraint(s.NORTH, banana,210, s.NORTH, this);
    add(banana);
    banana.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[2]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, grape, 790, s.WEST, this);
    s.putConstraint(s.NORTH, grape,110, s.NORTH, this);
    add(grape);
    grape.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[3]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, watermelon, 780, s.WEST, this);
    s.putConstraint(s.NORTH, watermelon,300, s.NORTH, this);
    add(watermelon);
    watermelon.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[4]++;
        revalidate();
        repaint();
      }});
  }
  
  /**This method will make and add the underwater themed buttons to the panel for Level 2. */
  private void makePanel2()
  {
    JButton red =makeButtons(foods[0],"Click here to drop a red fish into the basket!"), yellow = makeButtons(foods[1],"Click here to drop a yellow fish into the basket!");
    JButton green =makeButtons(foods[2],"Click here to drop a green fish into the basket!"), octo = makeButtons(foods[3],"Click here to drop an octopus into the basket!");
    JButton crab =makeButtons(foods[4],"Click here to drop a crab into the basket!");
    
    s.putConstraint(s.WEST, red,460, s.WEST, this);
    s.putConstraint(s.NORTH, red,110, s.NORTH, this);
    add(red);
    red.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[0]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint(s.WEST, yellow,460, s.WEST, this);
    s.putConstraint(s.NORTH, yellow,300, s.NORTH, this);
    add(yellow);
    yellow.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      { 
        foodCount[1]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint(s.WEST, green,600, s.WEST, this);
    s.putConstraint(s.NORTH, green,210, s.NORTH, this);
    add(green);
    green.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[2]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint(s.WEST, octo,770, s.WEST, this);
    s.putConstraint(s.NORTH, octo,110, s.NORTH, this);
    add(octo);
    octo.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[3]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint(s.WEST, crab,780, s.WEST, this);
    s.putConstraint(s.NORTH, crab,300, s.NORTH, this);
    add(crab);
    crab.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[4]++;
        revalidate();
        repaint();
      }});
  }
  
  /**This method will make and add the farm themed buttons to the panel for Level 3.*/
  private void makePanel3()
  {
    JButton tomato =makeButtons(foods[0],"Click here to drop a tomato into the basket!"), red = makeButtons(foods[1],"Click here to drop a red apple into the basket!");
    JButton green =makeButtons(foods[2],"Click here to drop a green apple into the basket!"), carrot = makeButtons(foods[3],"Click here to drop a carrot into the basket!");
    JButton potato =makeButtons(foods[4],"Click here to drop a potato into the basket!");
    
    s.putConstraint (s.WEST, tomato, 460, s.WEST, this);
    s.putConstraint(s.NORTH, tomato,110, s.NORTH, this);
    add(tomato);
    tomato.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[0]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, red, 460, s.WEST, this);
    s.putConstraint(s.NORTH, red,300, s.NORTH, this);
    add(red);
    red.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      { 
        foodCount[1]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, green, 610, s.WEST, this);
    s.putConstraint(s.NORTH, green,210, s.NORTH, this);
    add(green);
    green.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[2]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, carrot, 750, s.WEST, this);
    s.putConstraint(s.NORTH, carrot,120, s.NORTH, this);
    add(carrot);
    carrot.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[3]++;
        revalidate();
        repaint();
      }});
    
    s.putConstraint (s.WEST, potato, 760, s.WEST, this);
    s.putConstraint(s.NORTH, potato,300, s.NORTH, this);
    add(potato);
    potato.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        foodCount[4]++;
        revalidate();
        repaint();
      }});
  }
  
  /**This method will draw the level backgrounds depending on the level.
    * @param g This will be used to draw the image of the background onto the panel.
    * The try/catch is to errortrap FileIO.
    */
  public void paintComponent(Graphics g)
  {
    BufferedImage b=null, c=null,b1=null;
    super.paintComponent(g);
    try
    {
      b = ImageIO.read(new File (backName+".jpg"));
      g.drawImage(b,0,0,null);
      b1 = ImageIO.read(new File ("Basket.jpg"));
      g.drawImage(b1,450,90,null);
      if (levelNum==1)
      {
        //for (int i=0;i<chars.length;i++)
        //{
        c=ImageIO.read(new File("Squirrel"+".jpg"));
        g.drawImage(c,400,550,null);
        //}
      }
      else if (levelNum==2)
      {
        c=ImageIO.read(new File("Seal"+".jpg"));
        g.drawImage(c,-10,340,null);
        
      }
      else 
      {
        c=ImageIO.read(new File("Bunny"+".jpg"));
        g.drawImage(c,400,400,null);
      }
    }
    catch(IOException e){
    }
    g.setColor(Color.WHITE);
    g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
    g.drawString(Integer.toString(foodCount[0]), 580, 170); //460 110
    g.drawString(Integer.toString(foodCount[1]), 580, 360); //460 300
    g.drawString(Integer.toString(foodCount[2]), 730, 270); //600 210
    g.drawString(Integer.toString(foodCount[3]), 905, 170); //770 110
    g.drawString(Integer.toString(foodCount[4]), 905, 360); //770 300
    g.setColor(new Color(51,51,51));
    
    g.setColor(Color.WHITE);
    if(levelNum ==1)
      g.drawString("Requests Left: " + (3 - requestNum), 0, 200);
    else
      g.drawString("Requests Left: " + (5 - requestNum), 0, 200);
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
  
  public void pause()
  {
    int result = JOptionPane.showConfirmDialog((Component) null, "Would you like to go back to the main menu, or continue playing the game?","PAUSE", JOptionPane.YES_NO_CANCEL_OPTION);
    if (result==0)
    {
      j.remove(BasketFun.this);
        j.add(new Menus(0,j));
        j.revalidate();
        j.repaint();
    }
    else if (result==1)
    {
      
    }
    else 
    {
      
      
    }

    
    
  }
  
//  public static void main(String[] args) { 
//    String []c={"Squirrel","Monkey","Panda"};
//    String []b={"Seal","Dolphin1","Turtle","Jelly","Narwhal"};
//    String []d={"Pig","Bunny"};
//    BasketFun s= new BasketFun (3,"back3", new Color (37,177,77),d);
//    //forest green and farm grass green;new Color(37,177,77)
//    //ocean 0,126,255
//  }
}
