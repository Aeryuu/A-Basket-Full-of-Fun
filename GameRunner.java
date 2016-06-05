import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * The GameRunner class is the driver class that creates a GameRunner object and a SplashScreen object as well
 * as executes the program. 
 * 
 * @author Top of the Stack (Alice Zhang) on 05.11.16
 * @version 1 05.13.16
 * 
 * @author of modification Top of the Stack (Alice Zhang)
 * @version 2 06.01.16
 * This class now has a constructor that creates the central JFrame that the game will be played on. It will also create
 * a SplashScreen object and add it to the JFrame to start the game off. The main method now creates a GameRunner
 * object.
 */ 

public class GameRunner extends JFrame {
  /** The class constructor creates the JFrame that the other classes' JPanels are going to be added on. It also creates
    * a SplashScreen object to add it to the JFrame to start the game.
    */ 
  public GameRunner()
  {
    super("A Basket Full of Fun");
     //add(new SplashScreen (this));
    add(new Menus (0,this));
    setSize (800, 800);
    setVisible (true);
  }
  /** This method calls the GameGUIRunner constructor to
    * create the application.
    * 
    * @param args []  String array that allows command line
    * parameters to be used when executing the program.
    */ 
  public static void main(String[] args) { 
   new GameRunner();
  }
}