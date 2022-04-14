import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class block extends gameObjects
{
   int top,bottom,left,right;
   public block(int x, int y, Color c)
   {
      super(x,y,c);
   }
   
   public int getBlockX()
   {
      return centerX;
   }
   public int getBlockY()
   {
      return centerY;
   }
   public int top()
   {
      top = centerY-(25/2);
      return top;
   }
   public int bottom()
   {
      bottom = centerY+(25/2);
      return bottom;
   }
   public int left()
   {
      left = centerX-(25/2);
      return left;
   }
   public int right()
   {
      right = centerX+(25/2);
      return right;
   }
   public void setTop(int newTop)
   {
      centerY = newTop + (25/2);
   }
   public void setBottom(int newBottom)
   {
      centerY = newBottom - (25/2);
   }
   public void setLeft(int newLeft)
   {
      centerX = newLeft + (25/2);
   }
      public void setRight(int newRight)
   {
      centerX = newRight - (25/2);
   }
}