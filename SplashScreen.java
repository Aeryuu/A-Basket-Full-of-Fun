import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** Purpose: The purpose of this class is to animate and draw the 
  * company's intro and the game's intro. The game's logo will 
  * go to the centre of the screen form offscreen, a blue light will 
  * extend from the center of the logo to the ends of the screen.
  * The the game's intro will happen. The game's intro consists 
  * of a series of baskets and the game's title.
  * * <b>Instance variables: </b>
  * <p>
  * <b> logo </b> This creates our logo.
  * <p>
  * <b> t </b> This creates the timer used for the delay.
  * <p>
  * <b> count </b> This moves the images used in the splashscreen.
  *  @author Top Of The Stack(C Liu)
  *  @version 1 05.13.16
  * 
  * Modified by Top Of The Stack(C Liu) on 06.02.16.
  * added Timer delay, as Thread.sleep causes issues. Added 
  * count variable in order to use Timer, added Timer var
  * in order to add delay, added logo in order to import logo.
  * added actionPerformed method in order to create delay.
  * 
  * @author of modification Top of the Stack (Alice Zhang)
  * @version 2 06.04.16 Spent 0.5 hours
  * After the intro is done, a dialog box now pops up prompting the user to enter their name. If the name is acceptable,
  * the dialog box would close and the main menu screen would appear. If the name is not acceptable (for example, it
  * is blank) an error message would pop up.
  */
//
public class SplashScreen extends JPanel implements ActionListener {
  private BufferedImage logo=null;
  Timer t=new Timer(10,this);
  int count=-100;
  JFrame j;
  JPanel s = this;
  /**Purpose: The purpose of this method is to stop the timer
    * once the image reaches a certain x-value.
    * 
    * @param a ActionEvent passes in an action event.
    */
  public void actionPerformed(ActionEvent a) {
    count++;
    if (count==1000)
    {
      t.stop();
      JDialog myDialog = new JDialog(j,"Text");
      myDialog.setSize(100,100);
      JButton button = new JButton ("Close");
      myDialog.setResizable(false);
      myDialog.setLayout(new FlowLayout());
      button.addActionListener (new ActionListener()
                                  {
        public void actionPerformed(ActionEvent e)
        {
          j.remove(s);
          j.add(new Menus(0,j));
          j.revalidate();
          j.repaint();
          myDialog.dispose();
        }
      });
      myDialog.add(button);
      myDialog.setLocationRelativeTo(this);
      myDialog.setVisible(true);
    }
    repaint();
  }
  
  /**Purpose: The purpose of this constructor is to construct the
    * JFrame, set the size, and add this panel to the JFrame. 
    * It then allows the screen to be visible.
    */
  public SplashScreen(JFrame j) { 
    this.j = j;
    this.setPreferredSize(new Dimension( 200,200));
    j.add(this);
    t.start();
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
      BufferedImage c = ImageIO.read(new File ("cloud.jpg"));
      logo = ImageIO.read(new File ("logo.jpg"));
      
      g.drawImage(logo,count,200,null);
      g.drawImage(c,count+100,500,null);
      g.drawImage(c,count-200,25,null);
      
      
      g.setColor (Colours.skyB);
      g.fillRect(count-1,200,1,200);
      
    }
    catch(Exception e){
    }
    
  }
}




