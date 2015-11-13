import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Random;

public class Run extends Application
{
   private boolean won = false;
   private int pieceNumber = 0;
   private int pieceIndex = 0;
   private int[] xPosition = {50, 70, 90, 110};
   private int[] yPosition = {0, 20, 40, 60, 80, 100, 120, 140, 160, 180, 200, 220};
   private static ImageView[] pieces = new ImageView[48];
   private Label[] response = new Label[12];
   private Label instructions = new Label("Press a key corresponding to the first letter of the color, you want to insert. Press N to start a new game. \nAvailable colors: Red, Green, Blue, Orange, Purple, Yellow");
   private ImageView ib;
   private ImageView im;
   
   private Board board = new Board();
   
   public static void main(String[] args)
   {
      launch(args);
      
   }
   
   public void start(Stage window) throws Exception
   {
      Random rGen = new Random();
      window.setTitle("MasterMind");
      Pane p = new Pane();
      Pane p2 = new Pane();
      DropShadow d = new DropShadow();
      d.setOffsetY(1.0f);
      d.setOffsetX(1.0f);
      d.setColor(Color.color(0.4f, 0.4f, 0.4f));
      instructions.setEffect(d);
      instructions.setMaxWidth(330);
      instructions.setLayoutX(55);
      instructions.setLayoutY(245);
      
      //Load audio clips
      
      AudioClip beep = new AudioClip(Run.class.getResource("/small.wav").toString());
      AudioClip win = new AudioClip(Run.class.getResource("/big.wav").toString());
      AudioClip newGame = new AudioClip(Run.class.getResource("/new.wav").toString());
      AudioClip lose = new AudioClip(Run.class.getResource("/lose.wav").toString());
      
      //create board and marker
      ib = new ImageView();
      im = new ImageView();
      ib.setImage(DrawGraphics.board());
      im.setEffect(d);
      im.setImage(DrawGraphics.marker());
      im.setTranslateX(xPosition[pieceNumber]);
      im.setTranslateY(yPosition[board.getTurn()]);
      
      ib.setLayoutX(0);
      ib.setLayoutY(0);
      
      instructions.setWrapText(true);
      instructions.setFont(new Font("arial", 10));
      
      board.generateAnswer();
      
      p2.getChildren().add(instructions);
      p.getChildren().addAll(ib, im, instructions, p2);
      
      //create labels
      for(int i = 0; i < 12; i++)
      {
         response[i] = new Label("...");
         response[i].setLayoutX(130);
         response[i].setLayoutY(20 * i);
         response[i].setEffect(d);
         response[i].setFont(new Font("arial", 12));
         p.getChildren().add(response[i]);
      } 
      
      Scene s = new Scene(p, 430, 290);
      
      s.setOnKeyPressed(new EventHandler<KeyEvent>()
      {
         public void handle(KeyEvent ke)
         {
            boolean isAKey = false;
            if(board.getTurn() < 12)
            {
               if(ke.getCode() == KeyCode.R && won == false)
               {
                  pieces[pieceIndex] = new ImageView();
                  pieces[pieceIndex].setImage(board.addPiece(pieceNumber, "red"));
                  isAKey = true;   
               }
               if(ke.getCode() == KeyCode.G && won == false)
               {
                  pieces[pieceIndex] = new ImageView();
                  pieces[pieceIndex].setImage(board.addPiece(pieceNumber, "green")); 
                  isAKey = true; 
               }
               if(ke.getCode() == KeyCode.B && won == false)
               {
                  pieces[pieceIndex] = new ImageView();
                  pieces[pieceIndex].setImage(board.addPiece(pieceNumber, "blue")); 
                  isAKey = true;  
               }
               if(ke.getCode() == KeyCode.P && won == false)
               {
                  pieces[pieceIndex] = new ImageView();
                  pieces[pieceIndex].setImage(board.addPiece(pieceNumber, "purple"));
                  isAKey = true;    
               }
               if(ke.getCode() == KeyCode.O && won == false)
               {
                  pieces[pieceIndex] = new ImageView();
                  pieces[pieceIndex].setImage(board.addPiece(pieceNumber, "orange"));
                  isAKey = true;    
               }
               if(ke.getCode() == KeyCode.Y && won == false)
               {
                  pieces[pieceIndex] = new ImageView();
                  pieces[pieceIndex].setImage(board.addPiece(pieceNumber, "yellow"));  
                  isAKey = true;  
               }
            }   
            if(ke.getCode() == KeyCode.N)
            {
               pieceNumber = 0;
               board.generateAnswer();
               newGame.play();
               for(int i = 0; i < pieces.length; i++)
               {
                  if(pieces[i] != null)
                  {
                     p.getChildren().remove(pieces[i]);
                  }   
                  if(i < 12)
                  {
                     response[i].setText("...");
                  }   
                  pieces[i] = null;
               }
               final Timeline tl2Marker = new Timeline();
               tl2Marker.setCycleCount(1);
               tl2Marker.setAutoReverse(false);
               double xPos2 = xPosition[pieceNumber];
               double yPos2 = yPosition[board.getTurn()];
               final KeyValue kv2m01 = new KeyValue(im.translateXProperty(), im.getTranslateX());
               final KeyValue kv2m02 = new KeyValue(im.translateYProperty(), im.getTranslateY());
               final KeyFrame kf2m = new KeyFrame(Duration.millis(0), kv2m01, kv2m02);
               final KeyValue kv2m12 = new KeyValue(im.translateXProperty(), xPos2);
               final KeyValue kv2m11 = new KeyValue(im.translateYProperty(), yPos2);
               final KeyFrame kf2m1 = new KeyFrame(Duration.millis(200), kv2m11, kv2m12);
               tl2Marker.getKeyFrames().addAll(kf2m, kf2m1);
               tl2Marker.play();
               pieceIndex = 0;
               isAKey = true;
               won = false;
            }    
            if(ke.getCode() != KeyCode.N && isAKey == true && won == false)
            {  
               //Thread t = new Thread(new MovePiece(pieces[pieceIndex], yPosition[board.getTurn()]));
               //t.start();
               
               final Timeline tl = new Timeline();
               tl.setCycleCount(1);
               tl.setAutoReverse(false);
               double position = pieces[pieceIndex].getTranslateY();
               final KeyValue kv01 = new KeyValue(pieces[pieceIndex].opacityProperty(), 0);
               final KeyValue kv02 = new KeyValue(pieces[pieceIndex].translateYProperty(), position - 10);
               final KeyFrame kf = new KeyFrame(Duration.millis(0), kv01, kv02);
               final KeyValue kv12 = new KeyValue(pieces[pieceIndex].translateYProperty(), position);
               final KeyValue kv11 = new KeyValue(pieces[pieceIndex].opacityProperty(), 1);
               final KeyFrame kf1 = new KeyFrame(Duration.millis(200), kv11, kv12);
               tl.getKeyFrames().addAll(kf, kf1);
               tl.play();
               pieces[pieceIndex].setEffect(d);
               pieces[pieceNumber + 4 * board.getTurn()].setLayoutX(xPosition[pieceNumber]);
               pieces[pieceNumber + 4 * board.getTurn()].setLayoutY(yPosition[board.getTurn()]);
               p.getChildren().add(pieces[pieceNumber + 4 * board.getTurn()]);
               pieceNumber = pieceNumber + 1;
               if(pieceNumber < 4)
               {
                  beep.play(1, 0, rGen.nextDouble() + 0.5, 0, 0);
               }   
               if(pieceNumber >= 4 && board.getTurn() < 12)
               {
                  String s = board.checkAnswer();
                  if(s == "win")
                  {
                     win.play();
                     response[board.getTurn()-1].setText("You won! You are a freakin genious!");
                     won = true;
                  }
                  else
                  {
                     response[board.getTurn()-1].setText(s);
                     beep.play(1, 0, rGen.nextDouble() + 0.5, 0, 0);
                  }
                  pieceNumber = 0;
               }
               if(board.getTurn() < 12)
               {
                  final Timeline tlMarker = new Timeline();
                  tlMarker.setCycleCount(1);
                  tlMarker.setAutoReverse(false);
                  double xPos = xPosition[pieceNumber];
                  double yPos = yPosition[board.getTurn()];
                  final KeyValue kvm01 = new KeyValue(im.translateXProperty(), im.getTranslateX());
                  final KeyValue kvm02 = new KeyValue(im.translateYProperty(), im.getTranslateY());
                  final KeyFrame kfm = new KeyFrame(Duration.millis(0), kvm01, kvm02);
                  final KeyValue kvm12 = new KeyValue(im.translateXProperty(), xPos);
                  final KeyValue kvm11 = new KeyValue(im.translateYProperty(), yPos);
                  final KeyFrame kfm1 = new KeyFrame(Duration.millis(200), kvm11, kvm12);
                  tlMarker.getKeyFrames().addAll(kfm, kfm1);
                  tlMarker.play();
               }   
               else
               {
                  lose.play();
               }   
               pieceIndex = pieceIndex + 1;
            }   
         }
      });    
      
      window.setScene(s);
      window.setResizable(false);
      window.show();
   }
}      
      