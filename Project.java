import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
public class Project extends JFrame
{
   public Project()
   {
      super("Project");
      Container contents = getContentPane();
      projectPanel pp = new projectPanel("projectFile.txt");
      contents.add(pp);
      setSize(850,650);
      setVisible(true);
   } 
   public static void main(String args[]) throws IOException     
   {
      Project p = new Project();
      p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}