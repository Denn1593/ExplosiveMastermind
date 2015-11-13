import javafx.scene.image.ImageView;

public class MovePiece implements Runnable
{
   private ImageView iv;
   private double position = 0;
   public MovePiece(ImageView iv, double position)
   {
      this.iv = iv;
      this.position = position;
   }
      
   public void run()
   {
      double time = 0;
      int bounces = 3;
      while(bounces > 0)
      {
         iv.setTranslateY(position + Math.sin(time) * bounces * 10);
         if(Math.sin(time) < 0)
         {
            bounces = bounces - 1;
            time = 0;
         }
         time = time + 0.2;
         try
         {
            Thread.sleep(20);
         }
         catch(Exception e)
         {
            System.out.println("shiiiet");
         }         
      }
      iv.setTranslateY(position);
   }
}                  