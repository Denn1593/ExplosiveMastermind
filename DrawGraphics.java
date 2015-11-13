import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class DrawGraphics
{
   //red, blue, green, yellow, orange, purple
   private static String[] col = {"red", "blue", "green", "yellow", "orange", "purple"};
   private static double[] red = {1, 0, 0, 1, 1, 1};
   private static double[] green = {0, 0, 1, 1, 0.64, 0};
   private static double[] blue = {0, 1, 0, 0, 0, 1};
   
   public static WritableImage boardPiece(String color)
   {
      int colNum = 0;
      double r = 0;
      double g = 0;
      double b = 0;
      
      for(int i = 0; i < 6; i++)
      {
         if(color == col[i])
         {
            colNum = i;
         }
      }      
      
      
      WritableImage wi = new WritableImage(20, 20);
      PixelWriter pw = wi.getPixelWriter();
      
      double cDist = 0;
      double alpha = 0;
      
      for(int x = 0; x < 20; x++)
      {
         for(int y = 0; y < 20; y++)
         {
            cDist = Math.sqrt((x - 10) * (x - 10) + (y - 10) * (y - 10));
            
            if(cDist < 8)
            {
               if(cDist > 6)
               {
                  alpha = -0.5*cDist + 4;
               }
               else
               {
                  alpha = 1;
               }      
               r = 255 * red[colNum] - cDist * 10;
               g = 255 * green[colNum] - cDist * 10;
               b = 255 * blue[colNum] - cDist * 10;
               if(r < 0)
               {
                  r = 0;
               }
               if(g < 0)
               {
                  g = 0;
               }
               if(b < 0)
               {
                  b = 0;
               }         
               pw.setColor(x, y, Color.color(r / 255, g / 255, b / 255, alpha));
            }
         }
      }
      return wi;
   }
   
   public static WritableImage board()
   {
      double r = 0;
      double g = 0;
      double b = 0;
      
      WritableImage wi = new WritableImage(430, 300);
      PixelWriter pw = wi.getPixelWriter();
      
      for(int x = 0; x < 430; x++)
      {
         for(int y = 0; y < 300; y++)
         {
            if(x < 50)
            {
               r = -x + 255;
               g = -x + 255;
               b = -x + 255;
            }
            if(x > 380)
            {
               r = x - 175;
               g = x - 175;
               b = x - 175;
            }
            if(x >= 50 && x <= 380)
            {
               if(y <= 240)
               {
                  r = 255;
                  g = 255;
                  b = 255;
                  if(x == 70 || x == 90 || x == 110 || x == 130)
                  {
                     r = 200;
                     g = 200;
                     b = 200;
                  } 
                  if(y % 20 == 0)
                  {
                     r = 200;
                     g = 200;
                     b = 200;
                  }  
                  else if(x > 130)
                  {
                     r = 235;
                     g = 235;
                     b = 235;
                  }
               }
               if(y > 240)
               {
                  r = 240;
                  g = 240;
                  b = 240;
               }         
            }      
            pw.setColor(x, y, Color.rgb((int) r, (int) g, (int) b));
         }
      }
            
      return wi;     
   }   
   public static WritableImage marker()
   {
      double r = 0;
      double g = 0;
      double b = 0;
      
      WritableImage wi = new WritableImage(20, 20);
      PixelWriter pw = wi.getPixelWriter();
      
      double cDist = 0;
      double alpha = 0;
      
      for(int x = 0; x < 20; x++)
      {
         for(int y = 0; y < 20; y++)
         {
            cDist = Math.sqrt((10 - x)*(10 - x) + (10 - y)*(10 - y));
            r = 255 - cDist * 20;
            g = 0;
            b = 0;  
            
            if(cDist < 4)
            {  
               if(cDist > 2)
               {
                  alpha = -0.5 * cDist + 2;
               }   
               pw.setColor(x, y, Color.color(r / 255, g / 255, b / 255, alpha));
            }   
         }
      }
      return wi;
   }         
}                  