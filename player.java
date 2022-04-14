import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class player extends gameObjects
{
   int changeX, changeY;
   boolean ground,ceiling,left,right;
   public player(int x, int y, Color c)
   {
      super(x,y,c);
      changeX=centerX;
      changeY=centerY;
   }
   public void draw(Graphics g)
   {
      g.setColor(new Color(255,119,51));
      g.fillRect(centerX,centerY, 25,25);
      
   }
   public void move()
   {
      //centerX = changeX;
      //centerY = changeY;
   }
   public void moveX(int changeX)
   {
      if(centerX <= 36)
      {
         centerX = 37;
      }
      if(centerX >= 755)
      {
         centerX = 754;
      }
      centerX += changeX;
      
   }
   public void moveY(int changeY)
   {
      if(centerY <= 36)
      {
         centerY=37;  
      }
      if(centerY >= 576)
      {
         centerY = 575;
      }
      centerY += changeY;
   }
   
   public boolean isOnGround(ArrayList<ArrayList<block>> b)
   {
      for(int i = 0; b.size()>i; i++)
      {
         for(int j=0; b.get(i).size()>j; j++)
         {
            if(b.get(i).get(j) != null)
            {
               if(this.bottom()+1>=b.get(i).get(j).getY()-12)
               {
                  ground = true;
               }
            }
         }
      }
      return ground;
   }
   
   public void collision(ArrayList<ArrayList<block>> b)
   {
      for(int i = 0; b.size()>i; i++)
      {
         for(int j=0; b.get(i).size()>j; j++)
         {
            if(b.get(i).get(j) != null)
            {
               
            }
         }
      }
   }
   
   public boolean floor(block b)
   {
      ground = false;
      if(b.top() ==this.bottom()+1)
      {
        
         ground = true;
      }
      return ground;
   }
   public boolean isOnCeiling(block b)
   {
      ceiling = false;
      if(b.bottom() ==this.top()+1)
      {
         ceiling = true;
      }
      return ceiling ; 
   }
   public boolean isRight(block b)
   {
      
      right = false;
      if(b.getX() == this.getX()) /*&& this.bottom()>b.top() && this.top()<b.bottom()*/ 
      {
         right = true;
      }
      return right; 
   }
   public boolean isLeft(block b)
   {
      left = false;
      if(b.right() == this.left()+1 && this.bottom()>b.top() && this.top()<b.bottom())
      {
         left = true;
      }
      return left; 
   }
   public int playerX()
   {
      return centerX;
   }
   public int playerY()
   {
      return centerY;
   }
   public int top()
   {
      int top;
      top = centerY-(25/2);
      return top;
   }
   public int bottom()
   {
      int bottom;
      bottom = centerY+(25/2);
      return bottom;
   }
   public int left()
   {
      int left;
      left = centerX-(25/2);
      return left;
   }
   public int right()
   {
      int right;
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