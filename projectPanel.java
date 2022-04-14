import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
public class projectPanel extends JPanel
{
   player p;
   int gravity; 
   int currentGravity=1;
   int jumpSpeed;
   int currentJumpSpeed = 0;
   boolean topCollision, bottomCollision, rightCollision, leftCollision; //in respect to player
   boolean canJump = false;
   int x, y,row, col;
   Color color;
   ArrayList<ArrayList<block>> blocks = new ArrayList<ArrayList<block>>();
   victoryBlock v;
   int [][] map; 
   boolean collision,falling;
   //String fileName = "projectFile.txt";
   public projectPanel(String fileName)
   {
      super();
      read(fileName);
      addKeyListener(new KeyEventDemo());
      Timer t = new Timer(10, new TimeListener());
      t.start();
      setBackground(Color.BLACK);
      setSize(800,600);
      setFocusable(true);
      setOpaque(true);
   }
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      for(int i = 0; blocks.size()>i; i++)
      {
         for(int j=0; blocks.get(i).size()>j; j++)
         {
            if(blocks.get(i).get(j) != null)
            {
               blocks.get(i).get(j).draw(g);
            }
         }
      }
      v.draw(g);
      p.draw(g);
      //p.move();
      if(p.getX()/25==v.getX()/25 && p.getY()/25 == v.getY()/25)
      {
         JOptionPane.showMessageDialog(null, "You Have WON!!", "Exit", JOptionPane.QUESTION_MESSAGE);
         System.exit(1);
         p.moveX(5);
         p.moveY(5);
      }
   }
   public void read(String fileName)
   {
      try
      {
         Scanner scan = new Scanner(new File(fileName));
         x=scan.nextInt();
         y=scan.nextInt();
         row=scan.nextInt();
         col=scan.nextInt();
         map= new int [row][col];
         for(int i=0;i<row;i++)
         {
            for(int j=0;j<col;j++)
            {
               map[i][j] = scan.nextInt();
            }
         }
         
      }
      catch(FileNotFoundException e)
      {
         System.out.println("Unable to load file");
      }
      p= new player((25/2+x*25),(25/2+y*25),new Color(255,119,51));
      for(int i=0; i<row; i++)
      {
         ArrayList<block> platforms = new ArrayList<block>();
         blocks.add(platforms);
      }
      
      for(int i=0; i<row; i++)
      {
         for(int j=0; j<col; j++)
         {
            if(map[i][j]==0)
            {
            
            }
            else if(map[i][j]==1)
            {
               Color bColor = new Color(179,255,204);
               color = bColor;
            }
            else if(map[i][j]==2)
            {
               Color vColor = new Color(217,25,255);
               v = new victoryBlock((25/2+j*25),(25/2+i*25),vColor);
            }
            int blockX=(25/2)+(j*25);
            int blockY=(25/2)+(i*25);
            block b = new block(blockX,blockY,color);
            if(map[i][j]==0)
            {
               blocks.get(i).add(null);
            }
            if(map[i][j]==1)
            {
               blocks.get(i).add(b);
            }
         }
      }
   }
   public boolean ceiling()
   {
      topCollision = false;
      int blockXIndex = p.getX()/25;
      int blockYIndex = p.getY()/25 - 1;
      int rightTest = p.right();
      int leftTest = p.left();
      block victoryTest=null;
      try
      {
      if(blocks.size()>0)
      {
            if(blocks.get(blockXIndex).get(blockYIndex) != null)
            {
               if(p.left() <= blocks.get(blockXIndex).get(blockYIndex).right() && p.right() >= blocks.get(blockXIndex).get(blockYIndex).left() )
               {
                  topCollision=p.isOnCeiling(blocks.get(blockXIndex).get(blockYIndex));
               }
               victoryTest = blocks.get(blockXIndex).get(blockYIndex);
            }
            if(blocks.get(blockXIndex).get(blockYIndex + 1) != null)//left and right side checks
            {
               if(p.left() <= blocks.get(blockXIndex).get(blockYIndex+1).right() && p.right() >= blocks.get(blockXIndex).get(blockYIndex+1).left() )
               {
                  topCollision=p.isOnCeiling(blocks.get(blockXIndex).get(blockYIndex+1));
               }
               victoryTest = blocks.get(blockXIndex).get(blockYIndex+1);
            }
            if(blocks.get(blockXIndex).get(blockYIndex - 1) != null)
            {
               if(p.left() <= blocks.get(blockXIndex).get(blockYIndex-1).right() && p.right() >= blocks.get(blockXIndex).get(blockYIndex-1).left() )
               {
                  topCollision=p.isOnCeiling(blocks.get(blockXIndex).get(blockYIndex-1));
               }
               victoryTest = blocks.get(blockXIndex).get(blockYIndex-1);
            }
         }
      }
         catch(IndexOutOfBoundsException iooe)
         {
            
         }
      return topCollision;
   }
   
   
  public boolean floor()
   {
      bottomCollision = false;
      try{
      int blockXIndex = p.getX()/25;
      int blockYIndex = p.getY()/25 + 1;
      int rightTest = p.right();
      int leftTest = p.left();
      block victoryTest=null;
      if(blocks.size()>0)
      {
         
         if(blocks.get(blockYIndex).get(blockXIndex) != null)
         {
        // System.out.println("X: " +blockXIndex+ " Y: " +blockYIndex);
         //System.out.println("enemy x: " + blocks.get(blockYIndex).get(blockXIndex).getX() + "enemy y: " +blocks.get(blockYIndex).get(blockXIndex).getY());
            if(p.left() <= blocks.get(blockYIndex).get(blockXIndex).right() && p.right() >= blocks.get(blockYIndex).get(blockXIndex).left() )
            {
               bottomCollision=p.floor(blocks.get(blockYIndex).get(blockXIndex));
            }
            victoryTest = blocks.get(blockYIndex).get(blockXIndex);
         }
         else
         {
            try
            {
            if(blocks.get(blockYIndex).get(blockXIndex + 1) != null)//left and right side checks
            {
               if(p.left() <= blocks.get(blockYIndex).get(blockXIndex+1).right() && p.right() >= blocks.get(blockYIndex).get(blockXIndex+1).left() && blocks.get(blockXIndex).get(blockYIndex+1).top() == p.bottom())
               {
                  bottomCollision=p.floor(blocks.get(blockXIndex).get(blockYIndex+1));
               }
               victoryTest = blocks.get(blockXIndex).get(blockYIndex+1);
            }
            
            if(blocks.get(blockXIndex).get(blockYIndex - 1) != null)//test what x and y are here and try to fix instead of try/catch
            {
               if(p.left() <= blocks.get(blockYIndex).get(blockXIndex-1).right() && p.right() >= blocks.get(blockYIndex).get(blockXIndex-1).left() && blocks.get(blockXIndex).get(blockYIndex-1).top() == p.bottom())
               {
                  bottomCollision=p.floor(blocks.get(blockYIndex).get(blockXIndex-1));
               }
               victoryTest = blocks.get(blockYIndex).get(blockXIndex-1);
            }
            }
            catch(IndexOutOfBoundsException ioobe)
            {
            }
         }
        
      }
      }
      catch(NullPointerException npe)
      {
         
      }
      return bottomCollision;
   }
   
   
   public boolean leftWall()
   {
      leftCollision = false;
      int blockXIndex = p.getY()/25;
      int bottomBlock = p.bottom();
      int leftCheck = p.getX()/25-1;
      block victoryTest=null;
      if(blocks.size()>0)
      {
         if(blocks.get(blockXIndex).get(leftCheck) != null)
         {
            leftCollision=p.isLeft(blocks.get(blockXIndex).get(leftCheck));
            victoryTest = blocks.get(blockXIndex).get(leftCheck);
         }
         block leftTest = blocks.get(blockXIndex +1).get(leftCheck);
         if(bottomCollision==false && leftTest != null)
         {
            if(p.bottom() < leftTest.bottom())
            {
               leftCollision=p.isLeft(leftTest);
            }
            victoryTest=leftTest;
         }
         
         }
         return leftCollision;      
   }
   public boolean rightWall()
   {
      rightCollision = false;
      int blockYIndex = p.getY()/25;
      int bottomBlock = p.bottom();
      int rightCheck = p.getX()/25+1;
      block victoryTest=null;
      try{
      if(blocks.size()>0)
      {
         if(blocks.get(blockYIndex).get(rightCheck) != null)
         {
            //System.out.println("me x: " +(rightCheck-1) + " me y: " +blockYIndex);
            //System.out.println("block x: " +blocks.get(blockYIndex +1).get(rightCheck).getX()/25 + " block y: " +blocks.get(blockYIndex +1).get(rightCheck).getY()/25);
            rightCollision=p.isRight(blocks.get(blockYIndex).get(rightCheck));
            victoryTest = blocks.get(blockYIndex).get(rightCheck);
         }
         block rightTest = blocks.get(blockYIndex +1).get(rightCheck);
         if(bottomCollision==false && rightTest != null)
         {
            if(p.bottom() < rightTest.bottom())
            {
               rightCollision=p.isLeft(rightTest);
            }
            victoryTest=rightTest;
         }
         
      }
      }
      catch(IndexOutOfBoundsException iooe)
      {
         
      }
         return rightCollision;  
   }
   public class KeyEventDemo implements KeyListener 
   {
      public void keyTyped(KeyEvent e) {}
      public void keyReleased(KeyEvent e) 
    {
     if(e.getKeyCode() == KeyEvent.VK_W)
      {
         
      }
      
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         left = false;
         
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         right = false;
         
      }
    }
    public void keyPressed(KeyEvent e) 
    {
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         if(floor()==true)
         {
         canJump=true;
         jumpSpeed=7;
         currentJumpSpeed=0;
         }
         
      }
      
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         left = true;
         
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         right = true;
         
      }
      
      repaint(); //calls the paint component
    }
   }
   boolean up;
   boolean left;
   boolean right;
   public class TimeListener implements ActionListener
   {
      
      int timeValue;
      /*if(p == null)
      {
         System.out.println("player null");
         return;
      }*/
      public void actionPerformed(ActionEvent e)
      {
         timeValue++;
         //if(!p.isOnGround(blocks))
         //{
           // up=false;
         //}
         /*if(floor()==true)
         {
            p.moveY(-1);
            currentGravity=0;
         }
         if(ceiling()==true)
         {
            p.moveY(1);
         }*/
         if(canJump)//&&collision==true && p.isOnGround(blocks)==true
         {
            if(timeValue % 10 == 0)
            {
               if(currentJumpSpeed<7)
               {
                  currentJumpSpeed++;
               }
            }
            for(int x=0; x<jumpSpeed; x++)
            {
               if(ceiling()==false)
               {
               p.moveY(-1);
               jumpSpeed = 7 - currentJumpSpeed;
               }
               else
               {
                  jumpSpeed=0;
               }
            }  
            if(jumpSpeed == 0)
            {
               canJump=false;
            }
         }
         if(floor()==false)
         {
            if(timeValue % 20 == 0)
            {
               if(currentGravity<7)
               {
                  currentGravity++;
               }
            }
            for(int i=0; i<currentGravity; i++)
            {
               if(floor()==false)
               {
               p.moveY(1);
               }
            } 
            } 
            else 
            {
               currentGravity = 1;
            }
         if(left)
         {
            if(leftWall()==false)
            {
               p.moveX(-2);
            }
            if(leftWall()==true)
            {
               p.moveX(2);
            }
          
         }
         if(right)
         {
            if(rightWall()==false)
            {
            p.moveX(2);
            }
            if(rightWall()==true)
            {
            p.moveX(-2);
            }
         }
      repaint(); //calls the paint component
      }
   }
}
