import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.awt.geom.*;
import javax.swing.*;
/**
 * The HighScores class can create the JPanel for the High Scores screen and it has a method that updates the 
 * high scores file with a passed in String. The new score will be sorted into the high scores if it is high enough.
 * There is a button on the High Scores screen that can print the high scores. There is another button that is used to
 * go back to the Menu screen.
 *  
 * 
 * @author Top Of the Stack (Alice Z) 
 * @version 1 05.16.16 Spent 3.5 hours
 * 
 * @author of modification C Liu on 05.26.16
 * @version 2 05.26.16 Spent 3.5 hours
 * 
 * @author Top Of the Stack (Alice Z) on 05.16.16  modified by C Liu on 05.26.16
 * @version 3 05.18.16 Spent 3.5 hours
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
  JFrame j;
  JPanel b = this;
  /**
   * The class constructor will read in the high scores from the file and store them in the class's String array.
   */ 
  public HighScores(JFrame jf)
  {
    j = jf;
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
   * The purpose of this method is to create the graphics and text used
   * on the high scores screen. This method draws some clouds and
   * adds some green text and shapes.
   * 
   * @param g Graphics allows use of the Graphics class
   */ 
  public void paintComponent (Graphics g)
  {
    
    super.paintComponent(g);
    g.setColor (Colours.skyB);
    g.fillRect(0,0,800,800);
    Font f=new Font("Serif", Font.BOLD,40);
    g.setFont(f );
    g.setColor(Colours.lGreen);
    g.drawString("HIGH SCORES!", 250,50);
    g.fill3DRect(50,75,675,25,true);
    g.fill3DRect(50,675,675,25,true);
    //clouds
    g.setColor (Color.white);
    g.fillOval(20,40,200,30);
    g.fillOval(550,40,120,30);
    
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
  public void setUpHighScoresPanel()
  {
    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    JLabel name= new JLabel("Name"),score= new JLabel("Score"),level= new JLabel("Level");
    JButton exit = new JButton("Back To Menu"), print = new JButton("Print High Scores");
    
    setLayout(gbl);
    gbc.insets = new Insets(15,15,15,15);
    gbc.gridx=0;
    
    gbc.anchor = gbc.LINE_START;
    gbc.gridy=1;
    add(name,gbc);
    
    gbc.gridx=1;
    add(score,gbc);gbc.gridx=0;
    
    gbc.gridx=2;
    add(level,gbc);
    
    for(int x = 2; x<=11;x++)
    {
      gbc.gridx=0;
      gbc.gridy=x;
      name = new JLabel((x-1) + ". " + highScores[x-2][0]);
      add(name,gbc);
      
      gbc.gridx=1;
      score = new JLabel(highScores[x-2][1]);
      add(score,gbc);
      
      gbc.gridx=2;
      level = new JLabel(highScores[x-2][2]);
      add(level,gbc);
    }
    gbc.gridx=4;
    gbc.gridy=7;
    
    gbc.insets = new Insets(15,250,15,15);
    add(print,gbc);
    print.addActionListener (new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          PrinterJob job = PrinterJob.getPrinterJob();
          job.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            {
              if (pageIndex != 0)
                return NO_SUCH_PAGE;
              BufferedImage image = (BufferedImage)j.createImage(j.getContentPane().getSize().width,j.getContentPane().getSize().height);
              j.getContentPane().paint(image.getGraphics());
              Graphics2D graphics2 = (Graphics2D)graphics;
              graphics2.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
              //graphics.drawImage(image, 0, 0, j.getContentPane().getSize().width, j.getContentPane().getSize().height, null); //draws the image
              exit.setVisible(false);
              print.setVisible(false);
              graphics.drawImage(image, 0, 0, 500, 700, null);
              return PAGE_EXISTS;
            }});     
          job.print();
          exit.setVisible(true);
          print.setVisible(true);
        }
        catch (PrinterException p)
        {
        }
      }});
    
    gbc.gridy=9;
    add(exit,gbc);
    exit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        Menus m = new Menus(1,j);
        m.j.remove(b);
        m.add(m);
        m.j.revalidate();
        m.j.repaint();
      }});
    revalidate();
    repaint();
  }
//  public static void main(String[] args) { 
//    HighScores s=new HighScores (0);
//    s.j.add(s);
//  }
  
}
