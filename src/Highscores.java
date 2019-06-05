import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.io.*;

public class Highscores extends Window {
    /**
     * The choice selected for the game level that the user wishes to play.
     */
    private boolean back;
    final File SCORES_FILE = new File (System.getProperty("user.home") + "/highScoresFile.wtr");

    /**
     * @param stg The JavaFX Stage to display to.
     */
    public Highscores(Stage stg) {
        super(stg, "Highscores");
        back = false;
    }

    /**
     * This method will display all the graphics of the Highscores window
     */
    public void display()
    {
        Image background = (Image)(Resources.get("menuBackground"));
        Image playTitle = (Image)(Resources.get("highscoresTitle"));
        Image menuBackgroundLog = (Image)(Resources.get("backLog"));
        Image highscoresOntario = (Image)(Resources.get("highscoresOntario"));
        Image highscoresErie = (Image)(Resources.get("highscoresErie"));
        Image highscoresSuperior = (Image)(Resources.get("highscoresSuperior"));
        ImageView backButton = (ImageView)(Resources.get("backButton"));
        Sound click = (Sound)(Resources.get("click"));

        String[][]scores = new String[0][0];
        String[][] names = new String[0][0];
        int [] lengths = {0, 0, 0};
        int[] positions = {0, 0, 0};


        backButton.setOnMouseClicked(e -> {
            refresh();
            back = true;
            click.play();
        });
        // Listener for MouseEnter
        backButton.setOnMouseEntered(e -> {
            setCursor(1);
        });
        // Listener for MouseExit
        backButton.setOnMouseExited(e -> {
            setCursor(0);
        });

        drawImage(backButton, -380, -160);
        String line = "";

        try{
            BufferedReader input = new BufferedReader (new FileReader (SCORES_FILE));
            while (line != null)
            {
                line = input.readLine ();
                System.out.println (line);
                if (line == null) //if statement 1
                    break;
                else if (line.equals ("level played:1"))
                    lengths [0]++;
                else if (line.equals ("level played:2"))
                    lengths [1]++;
                else if (line.equals ("level played:3"))
                    lengths [2]++;
            }
            input.close ();

            //set lengths to arrays
            scores = new String [3][0];
            names = new String [3][0];
            for (int i = 0 ; i < 3 ; i++)
            {
                scores [i] = new String [lengths [i]];
                names [i] = new String [lengths [i]];
            }

            System.out.println (lengths[0] + "level 1");
            input = new BufferedReader (new FileReader (SCORES_FILE));
            line = " ";
            while (line != null)
            {
                line = input.readLine ();
                if (line == null)
                    break;
                if (line.equals ("level played:1") && lengths [0] != 0)
                {
                    line = input.readLine ();
                    names [0] [positions [0]] = line;
                    line = input.readLine ();
                    scores [0] [positions [0]] = line;
                    positions [0]++;
                }
                else if (line.equals ("level played:2") && lengths [1] != 0)
                {
                    names [1] [positions [1]] = input.readLine ();
                    scores [1] [positions [1]] = input.readLine ();
                    positions [1]++;
                }
                else if (line.equals ("level played:3") && lengths [2] != 0)
                {
                    names [2] [positions [2]] = input.readLine ();
                    scores [2] [positions [2]] = input.readLine ();
                    positions [2]++;
                }
            }

            String temp;
            String tempStr;
            input.close ();
            for (int y = 0 ; y < 3 ; y++) //for loop 0
            {
                for (int x = 0 ; x < lengths [y] ; x++) //for loop 1
                {
                    for (int i = 0 ; i < lengths [y] - 1 - x ; i++) //for loop 2 //sorts the array
                    {
                        try{
                        if (Integer.parseInt(scores [y][i]) < Integer.parseInt(scores [y] [i + 1])) //if statement 4
                        {
                            temp = scores [y] [i + 1];
                            tempStr = names [y] [i + 1];
                            scores [y] [i + 1] = scores [y] [i];
                            names [y] [i + 1] = names [y] [i];
                            scores [y] [i] = temp;
                            names [y] [i] = tempStr;

                        }}
                        catch (NumberFormatException e){}
                    }
                }
            }

        }
        catch (IOException e){}

        String text = "";
        Text list[][] = {new Text[Math.min(lengths[0], 10)], new Text[Math.min(lengths[1], 10)], new Text[Math.min(lengths[0], 10)]};
        for (int r = 0; r < list.length; r++)
        {
            for (int c = 0; c < list[r].length; c++)
            {
                list [r][c] = new Text();
                if (r < names.length && c < names[r].length)
                {
                    text += names[r][c];
                    for (int i = 0; i < 6 - scores[r][c].length(); i++)
                        text += " ";
                    text += scores[r][c];
                    list[r][c].setText(text);
                    text = "";
                }
                list[r][c].setFont(Font.font("Consolas", 20));
                list[r][c].setFill(Color.WHITE);
                add (list[r][c], 215 * r + -32, 30 + 20 * c);
            }
        }



        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

                double x = 232 + 128 * Math.cos(t);
                double y = 232 + 128 * Math.sin(t);

                // background image clears canvas
                drawImage(background, 0, 0);
                drawImage(menuBackgroundLog, 0, 690);
                drawImage(playTitle, 40, 30);
                drawImage(standing.getFrame(t), -90, 275);
                drawImage(highscoresOntario, 360, 300);
                drawImage(highscoresErie, 575, 300);
                drawImage(highscoresSuperior, 790, 300);

                if (back) {
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
