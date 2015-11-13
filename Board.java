import javafx.scene.image.WritableImage;
import java.util.Random;

public class Board
{
   private int turn = 0;
   
   private Random rGen = new Random();
   private String[] col = {"red", "blue", "green", "yellow", "orange", "purple"};
   
   private String[][] pieceID = new String[4][12];
   
   private String[] answer = new String[4];
   
   public int getTurn()
   {
      return turn;
   }   
   
   public void generateAnswer()
   {
      //generate a new answer
      for(int i = 0; i < 4; i++)
      {
         answer[i] = col[rGen.nextInt(6)];
      }
      turn = 0;
      
      //clear the board
      for (int i = 0; i < 12; i++)
      {
         for (int j = 0; j < 4; j++)
         {
            pieceID[j][i] = null;
         }
      }      
      System.out.println("Answer generated is: " +answer[0] +" " +answer[1] +" " +answer[2] +" " +answer[3]);         
   }
      
   
   public WritableImage addPiece(int place, String color)
   {       
      pieceID[place][turn] = color;
      return DrawGraphics.boardPiece(color);
   }
   
   public String checkAnswer()
   {
      int onPlace = 0;
      int offPlace = 0;
      boolean[] checked = new boolean[4];
      boolean[] checked2 = new boolean[4];
      for(int i = 0; i < 4; i++)
      {
         checked[i] = false;
         checked2[i] = false;
      }
      
      for(int i = 0; i < 4; i++)
      {
         if(checked[i] == false)
         {
            if(answer[i] == pieceID[i][turn])
            {
               checked[i] = true;
               checked2[i] = true;
               onPlace = onPlace + 1;
            }
         }
      }
      for(int i = 0; i < 4; i++)
      {
         for(int j = 0; j < 4; j++)
         {
            if(checked[i] == false && checked2[j] == false)
            {
               if(pieceID[j][turn] == answer[i])
               {
                  checked[i] = true;
                  checked2[j] = true;
                  offPlace = offPlace + 1;
               }
            }
         }
      }
      turn = turn + 1;
      if(onPlace == 4)
      {
         return "win";
      }   
      if(turn > 11)
      {
         return "You lose. Answer: " +answer[0] +" " +answer[1] +" " +answer[2] +" " +answer[3];
      }   
      return "There are " +onPlace +" correct and " +offPlace +" incorrect";
   }                  
}   