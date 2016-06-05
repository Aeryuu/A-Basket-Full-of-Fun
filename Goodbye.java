import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import javax.imageio.*;
import java.lang.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
/**
 * The Goodbye class creates the panel and 
 * graphics associated with the goodbye screen of the 
 * Basket Full of Fun game. It creates the panel, 
 * the graphics, and it creates text.
 * 
 * <b>Instance variables: </b>
 * <p>
 * <b> t </b> This creates the timer used for the delay.
 * 
 * Modified by Top Of The Stack(C Liu) on 06.02.16.
 * added Timer delay, as Thread.sleep causes issues. Added Timer var
 * in order to add delay, 
 * added actionPerformed method in order to create delay.
 * 
 * @author Top Of the Stack (C Liu) on 05.20.16
 * @version 3 05.26.16 Spent 1 hour
 */ 
public class Goodbye extends JPanel implements ActionListener {
  Timer t=new Timer(3000,this);
  
  /**Purpose: The purpose of this method is to close the screen
    * after 3 seconds.
    * 
    * @param a ActionEvent passes in an action event.
    */ 
  public void actionPerformed(ActionEvent a) {
    t.stop();
    System.exit(0);
  }
  /** Purpose: The purpose of this method is to 
    * construct the Goodbye class.It sets up the panel, and
    * allows the panel to be visible. It also sets 
    * the size of the panels.
    */
  public Goodbye() { 
    super();
    this.setPreferredSize(new Dimension( 800,800));
    t.start();
  }
  /** Purpose: The purpose of this method is to 
    * paint the panel. It draws the sky, some clouds, and adds the text.
    * It also loads the logo image into the screen.
    * The try catch errortraps the importing of the image.
    * @param g Graphics allows use to the Graphics class.
    */
  
  public void paintComponent (Graphics g)
  {
    
    BufferedImage logo=null;
    super.paintComponent(g);
    g.setColor (Colours.skyB);
    g.fillRect(0,0,800,800);
    Font f=new Font("Serif", Font.BOLD,40);
    g.setFont(f );
    g.setColor(Colours.lGreen);
    g.drawString("THANKS!", 320,50);
    Font f1=new Font("Serif", Font.BOLD,20);
    g.setFont(f1 );
    g.drawString("We hope you had a great time playing this game!", 200,400);
    g.drawString("Created by Top Of The Stack: Alice Zhang and Caroline Liu!", 140,500);
    g.drawString("Thanks for feeding the animals!", 270,350);
    
    try{
      logo = ImageIO.read(new File ("logo.jpg"));
      g.drawImage(logo,275,100,null);
    }
    catch(Exception e){
    }
    g.fill3DRect(50,75,700,25,true);
    //clouds
    g.setColor (Color.white);
    g.fillOval(20,640,300,50);
    g.fillOval(600,640,200,30);
    
    
    g.fillOval(10,640,100,40);
    g.fillOval(20,640,100,40);
    g.fillOval(60,620,100,70);
    g.fillOval(90,620,150,100);
    g.fillOval(-30,620,150,100);
    g.fillOval(100,630,100,50);
    g.fillOval(160,640,100,60);
    
    g.fillOval(550,640,300,70);
    g.fillOval(640,620,100,60);
    g.fillOval(600,630,100,60);
    g.fillOval(660,640,100,50);
    g.fillOval(500,640,100,30);
    g.fillOval(450,650,150,60);
  }
}
