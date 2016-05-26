/**
 * The GameRunner class is the driver class that creates GameGUIRunner as well
 * as executes the program. 
 * 
 * @author Top of the Stack (Alice Zhang)
 * @version 1 05.13.16
 */ 

public class GameRunner {
  /** This method calls the GameGUIRunner constructor to
    * create the application.
    * 
    * @param args []  String array that allows command line
    * parameters to be used when executing the program.
    */ 
  public static void main(String[] args) { 
    GameGUIRunner gui = new GameGUIRunner ();
    gui.displayGame();
  }
}