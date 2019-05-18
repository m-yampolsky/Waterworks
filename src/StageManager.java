import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StageManager extends Application{

    public void start(Stage stage)
    {
        stage.setTitle( "Waterworks" );

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 750, Color.WHITE);
        stage.show();

        Canvas canvas = new Canvas( 1000, 750 );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();

        stage.setScene( scene);
        SplashScreen s = new SplashScreen(gc, root);
    }
}
