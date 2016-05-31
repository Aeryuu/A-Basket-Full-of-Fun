import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.lang.*;


/** Purpose: The purpose of this class is to animate and draw the 
  * company's intro and the game's intro. The game's logo will 
  * go to the centre of the screen form offscreen, a blue light will 
  * extend from the center of the logo to the ends of the screen.
  * The the game's intro will happen. The game's intro consists 
  * of a series of baskets and the game's title.
  *
  *  @author Top Of The Stack(C Liu)
  *  @version 1 05.13.16
  */
public class SplashScreen extends JPanel{
  
  private BufferedImage logo=null;
  
  /**Purpose: The purpose of this constructor is to construct the
    * JFrame, set the size, and add this panel to the JFrame. 
    * It then allows the screen to be visible.
    */
  public SplashScreen() { 
    JFrame j=new JFrame("A Basket Full Of Fun");
    j.setSize(800,800);
    this.setPreferredSize(new Dimension( 200,200));
    j.add(this);
    j.setVisible (true);
    
  }
  
  /** Purpose: The purpose of this method is to 
    * paint the panel. It draws the sky, the sun, 
    * and a series of animated baskets.
    * @param g Graphics allows use to the Graphics class.
    */
  
  public void paintComponent (Graphics g)
  {
    
    super.paintComponent(g);
    try{
      
      g.setColor (Colours.skyB);
      g.fillRect(0,0,800,800);
      logo = ImageIO.read(new File ("logo.jpg"));
      
      for  (int i=-100;i<=600;i++)
      {
        g.drawImage(logo,i,200,null);
        repaint();
      }
      
    }
    catch(Exception e){
    }
    
  }
  
  public void delay(){
    try{
      Thread.sleep(100);
      
    }
    catch(Exception e){
      
    }
    
    
    
  }
  /**The purpose of this method is to construct 
    * an object of the SplashScreen. 
    * @param args []  String array that allows command line
    * parameters to be used when executing the program.
    */
//  public static void main(String[] args) { 
//    SplashScreen s=new SplashScreen ();
//    
//  }
  
  
  
  
}
