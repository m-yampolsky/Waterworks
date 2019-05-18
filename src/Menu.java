import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.swing.*;

public class Menu
{
    public Menu(GraphicsContext gc, StackPane root) {

        Image menuBackground = new Image( "elements/menus/background.png" );
        Image menuBackgroundLog = new Image( "elements/menus/backgroundLog.png" );
        Image menuTitle = new Image( "elements/menus/menuLogo.png" );
        ImageView menuLearnBtn = new ImageView("elements/menus/menuLearnBtn.png");
        ImageView menuQuizBtn = new ImageView("elements/menus/menuQuizBtn.png");
        ImageView menuPlayBtn = new ImageView("elements/menus/menuPlayBtn.png");
        Image menuInstructionsBtn = new Image( "elements/menus/menuInstructionsBtn.png" );
        Image menuHighscoresBtn = new Image( "elements/menus/menuHighscoresBtn.png" );


        // Listener for MouseClick
        menuLearnBtn.setOnMouseClicked(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Learn");
        });
        menuQuizBtn.setOnMouseClicked(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Quiz");
        });
        menuPlayBtn.setOnMouseClicked(e -> {
            JOptionPane.showMessageDialog(new JFrame(), "Play");
        });

        menuLearnBtn.setTranslateX(40);
        root.getChildren().addAll(menuLearnBtn);

        menuQuizBtn.setTranslateY(105);
        root.getChildren().addAll(menuQuizBtn);

        menuPlayBtn.setTranslateY(210);
        menuPlayBtn.setTranslateX(20);
        root.getChildren().addAll(menuPlayBtn);

        AnimatedImage standing = new AnimatedImage();
        Image[] imageArray = new Image[180];
        for (int i = 1; i <= 180; i++)
            imageArray[i-1] = new Image( "elements/standing/standing (" + i + ").png" );
        standing.frames = imageArray;
        standing.duration = 0.100;

        final long startNanoTime = System.nanoTime();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                gc.drawImage( menuBackground, 0, 0 );
                gc.drawImage( menuBackgroundLog, 0, 690 );
                gc.drawImage( menuTitle, 50, 50 );
                gc.drawImage( menuInstructionsBtn, 60, 200 );
                gc.drawImage( menuHighscoresBtn, 700, 700 );
                gc.drawImage( standing.getFrame(t), -90, 275 );
            }
        }.start();
    }
}