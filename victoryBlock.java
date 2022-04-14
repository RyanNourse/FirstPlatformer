import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class victoryBlock extends gameObjects
{
   public victoryBlock(int x, int y, Color c)
   {
      super(x,y,c);
   }
   public int getVictoryBlockX()
   {
      return centerX;
   }
   public int getVictoryBlockY()
   {
      return centerY;
   }
}