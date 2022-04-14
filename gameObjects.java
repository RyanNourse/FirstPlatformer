import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class gameObjects
{
   protected int centerX, centerY, top, bottom, left, right;
   protected Color blockColor;
   protected boolean collision;
   public gameObjects(int x, int y, Color c)
   {
      centerX = x;
      centerY = y;
      blockColor=c;
      
   }
   
   public void draw(Graphics g)
   {
      g.setColor(blockColor);
      g.fillRect(centerX, centerY, 25,25);
   }
   public boolean collides(block b)
   {
      if(this == b)
      {
         collision = false;
      }
      else
      {
      int topthis = this.top();
		int bottomthis = this.bottom();
		int leftthis = this.left();
		int rightthis = this.right();
		int topother = b.top();
		int bottomother = b.bottom();
		int leftother = b.left();
		int rightother = b.right();
		
		return ((bottomthis < topother) || (topthis > bottomother) || (leftthis > rightother) || (rightthis < leftother));

      }
      return collision;
   }
   public int getX()
   {
      return centerX;
   }
   public int getY()
   {
      return centerY;
   }

   public Color getColor()
   {
      return blockColor;
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