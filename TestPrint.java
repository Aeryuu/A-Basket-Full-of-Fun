import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;
public class TestPrint {
  BufferedImage img,img2;
  /** Main window used as the frame */
  JFrame frame;
  
  /** ImageIcon used to display the picture in the label*/
  ImageIcon imageIcon = new ImageIcon();JLabel label = new JLabel(imageIcon);
  public TestPrint()
  {
    frame = new JFrame();
    JLabel lab = new JLabel ("I love Chicken <3");
    frame.add(lab);
    frame.setSize(500,500);
    frame.setVisible(true);
    
    //grabScreenShot();
//    try{
//      img=ImageIO.read(new File ("testImage.png"));
//      //img2=ImageIO.read(new File("testImage2.png"));
//    }
//    catch(IOException e){System.out.println("whoops");}
    printHighScores(img);
    //show(img);
  }
//  public void show(Image image)
//  {
//    imageIcon.setImage(image);
//    frame.getContentPane().add(label);
//    //imageIcon.setImage(image2);
//    //label = new JLabel(imageIcon);
//    frame.getContentPane().add(label);
//    frame.setSize(500,500);
//    // make the frame visible
//    frame.repaint();
//    frame.setVisible(true);
//  }
  public void printHighScores(Image image)
  {
    try
    {
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(new Printable() {
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
        {
          if (pageIndex != 0)
            return NO_SUCH_PAGE;
          //BufferedImage image = (BufferedImage)frame.createImage(frame.getContentPane().getSize().width,frame.getContentPane().getSize().height);
          
          BufferedImage image = (BufferedImage)frame.createImage(475,500);
          frame.getContentPane().paint(image.getGraphics());
          try
          {
            ImageIO.write(image, "png", new File("testImage.png"));
            System.out.println("Image was created");
          }
          catch (IOException e)
          {
          }
          Graphics2D graphics2 = (Graphics2D)graphics;
          graphics2.translate(pageFormat.getImageableX(),pageFormat.getImageableY());
          graphics.drawImage(image, 0, 0, frame.getContentPane().getSize().width, frame.getContentPane().getSize().height, null);
          return PAGE_EXISTS;
        }});     
      job.print();
    }
    catch (PrinterException p)
    {
    }
  }
  
//  private void grabScreenShot()
//  {
//    BufferedImage image = (BufferedImage)frame.createImage(frame.getContentPane().getSize().width,frame.getContentPane().getSize().height);
//    frame.getContentPane().paint(image.getGraphics());
//    try
//    {
//      ImageIO.write(image, "png", new File("testImage.png"));
//      System.out.println("Image was created");
//    }
//    catch (IOException e)
//    {
//    }
//  }
}
