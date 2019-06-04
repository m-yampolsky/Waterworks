import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.text.Text;

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

        int[][]scores = new int[10][3]; //rows represent the scores, each column is a level
        String[][] names = new String[10][3]; //rows represent the names, each column is a level
        int [] lengths = new int[3];
        int[] positions = new int[3];


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
                if (line == null) //if statement 1
                    break;
                else if (line.equals ("level of difficulty:1"))
                    lengths [0]++;
                else if (line.equals ("level of difficulty:2"))
                    lengths [1]++;
                else if (line.equals ("level of difficulty:3"))
                    lengths [2]++;
            }
            input.close ();

            //set lengths to arrays
            for (int i = 0 ; i < 3 ; i++)
            {
                scores [i] = new int [lengths [i]];
                names [i] = new String [lengths [i]];
            }

            input = new BufferedReader (new FileReader (SCORES_FILE));
            line = " ";
            while (line != null)
            {
                line = input.readLine ();
                if (line == null)
                    break;
                if (line.equals ("level of difficulty:1") && lengths [0] != 0)
                {
                    line = input.readLine ();
                    names [0] [positions [0]] = line;
                    line = input.readLine ();
                    try
                    {
                        scores [0] [positions [0]] = Integer.parseInt (line);
                    }
                    catch (NumberFormatException e)
                    {
                    }
                    positions [0]++;
                }
                else if (line.equals ("level of difficulty:2") && lengths [1] != 0)
                {
                    names [1] [positions [1]] = input.readLine ();
                    try
                    {
                        scores [1] [positions [1]] = Integer.parseInt (input.readLine ());
                    }
                    catch (NumberFormatException e)
                    {
                    }
                    positions [1]++;
                }
                else if (line.equals ("level of difficulty:3") && lengths [2] != 0)
                {
                    names [2] [positions [2]] = input.readLine ();
                    try //try catch statement 4
                    {
                        scores [2] [positions [2]] = Integer.parseInt (input.readLine ());
                    }
                    catch (NumberFormatException e)
                    {
                    }
                    positions [2]++;
                }
            }

            int temp;
            String tempStr;
            input.close ();
            for (int y = 0 ; y < 3 ; y++) //for loop 0
            {
                for (int x = 0 ; x < lengths [y] ; x++) //for loop 1
                {
                    for (int i = 0 ; i < lengths [y] - 1 - x ; i++) //for loop 2 //sorts the array
                    {
                        if (scores [y] [i] < scores [y] [i + 1]) //if statement 4
                        {
                            temp = scores [y] [i + 1];
                            tempStr = names [y] [i + 1];
                            scores [y] [i + 1] = scores [y] [i];
                            names [y] [i + 1] = names [y] [i];
                            scores [y] [i] = temp;
                            names [y] [i] = tempStr;

                        }
                    }
                }
            }

        }
        catch (IOException e){}

        Text list[][] = new Text [10][3];
        for (int i = 0; i < 3; i++)
        {
            for (int x = 0; x < 10; x++)
            {
                list [x][i] = new Text();
                list[x][i].setText(names[x][i] + scores[x][i]);
                add (list[x][i], 20 * i, 20 * x);
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
