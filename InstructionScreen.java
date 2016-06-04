import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The InstructionScreen class creates the panel and 
 * graphics associated with the instructions of the 
 * Basket Full of Fun game. It creates the panel
 * , the graphics, and it creates the text detailing the instructions
 *  
 * 
 * @author Top Of the Stack (C Liu) on 05.20.16
 * @version 3 05.26.16 Spent 1 hour
 */ 
public class InstructionScreen extends JPanel {
  JPanel i = this;
  /** Purpose: The purpose of this method is to 
    * construct the InstructionScreen class.It sets up the panel, and
    * allows the panel to be visible. It also adds the back button.
    */
  public InstructionScreen(JFrame j) { 
    super();
    this.setPreferredSize(new Dimension( 800,800));
    FlowLayout f=new FlowLayout();
    f.setAlignment (FlowLayout.LEFT);
    setLayout(f);
    JButton exit = new JButton("Back to Menu");
    add(exit);
    exit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e)
      {
        Menus m = new Menus(0,j);
        j.remove(i);
        j.add(m);
        j.revalidate();
        j.repaint();
      }});
    revalidate();
    repaint();
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
    Font f=new Font("Serif", Font.BOLD,40);
    g.setFont(f );
    g.setColor(Colours.lGreen);
    g.drawString("INSTRUCTIONS!", 250,50);
    g.fill3DRect(50,75,700,25,true);
    //clouds
    g.setColor (Color.white);
    g.fillOval(20,40,200,30);
    g.fillOval(600,40,120,30);
    
    g.fillOval(20,40,70,20);
    g.fillOval(60,20,70,50);
    g.fillOval(100,30,70,20);
    g.fillOval(160,40,70,30);
    
    g.fillOval(640,20,70,50);
    g.fillOval(600,30,70,20);
    g.fillOval(660,40,70,30);
    g.fillOval(700,40,80,30);
  }  
}
