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
            for (int y = 0 ; y < 3 ; y++)
            {
                int x;
                for (int i = 1; i < lengths[y]; i++)
                {
                    temp = scores[y][i];
                    tempStr = names[y][i];
                    x = i - 1;
                    try{
                    while (x >= 0 && Integer.parseInt(scores[y][x]) < Integer.parseInt(temp))
                    {
                        scores[y][x + 1] = scores[y][x];
                        names[y][x + 1] = names[y][x];
                        x--;
                    }}catch (NumberFormatException e){}
                    scores[y][x + 1] = temp;
                    names[y][x+1] = tempStr;
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

        try{
            PrintWriter output = new PrintWriter (new BufferedWriter (new FileWriter (SCORES_FILE, false)));
            for (int i = 0; i < Math.min(lengths[0], 10); i++)
            {
                output.println ("level played:" + 1);
                output.println (names[0][i]);
                output.println (scores[0][i]);
            }
            for (int i = 0; i < Math.min(lengths[1], 10); i++)
            {
                output.println ("level played:" + 2);
                output.println (names[1][i]);
                output.println (scores[1][i]);
            }
            for (int i = 0; i < Math.min(lengths[2], 10); i++)
            {
                output.println ("level played:" + 3);
                output.println (names[2][i]);
                output.println (scores[2][i]);
            }
            output.close ();}
        catch (IOException e){}

        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        final long startNanoTime = System.nanoTime();
        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                double t = (currentNanoTime - startNanoTime) / 300000000.0;

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
