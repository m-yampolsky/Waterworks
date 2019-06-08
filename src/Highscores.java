import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.io.*;

/**
 * The Highscores class
 * This class represents the window that has all the graphics for the high scores page of the game. It extends the abstract Window class to use its basic mechanics methods for displaying, hiding and clearing the screen.
 * This class writes to the high scores storage file to update it. It reads from the file to get the high scores with their names to then output them. It sorts the scores and organises them based off of which lake
 * the score is from. It then outputs them in three different columns. The names are on the left of each column, and the scores are on the right. Only the top ten scores are displayed, unless there are less than ten scores
 * saved, in that case, all the scores are displayed. There will be a Menu button on screen that the user will be able to press at any point to return to the Main Menu.
 * @author Maria Yampolsky and Vansh Juneja
 * @version 5 06.05.2019
 *
 * <pre>
 * Version History:
 * May 21:
 * Maria created the Highscores class and set up the graphics components. She added the boxes for the high scores lists, the background, the title, the Menu button, and the character animation.
 * May 23:
 * Vansh added the cursor changes when the mouse hovers over the menu button. He also added the clicking sound when the button is clicked.
 * June 2:
 * Maria added the SCORES_FILE variable to store the path location of the file where high scores will be stored.
 * June 3:
 * Maria added the portion of the display() method that uses a FileReader object to read from the high scores file. She added the scores[][], names[][], positions[] and lengths[] local variables in the display method
 * to store the information read from the file. She also added the portion of the code that outputs the high scores in the display() method by adding the list[][] array, which stores the Text objects.
 * June 4:
 * Maria added the portion of the display() method that sorts the high scores, using an insertion sort. She also added the portion of the code that uses PrintWriter to output the list of high scores
 * into the high score file.
 * June 5:
 * Maria modified the display() method and added the SCORES_FILE_BACKUP to write to another file in case writing to the first file fails. This gives the program the opportunity to write to another directory, in case
 * the user has blocked access to the first directory they will try writing to.
 * </pre>
 */

public class Highscores extends Window {
    /**
     * Stores if the user wants to return to the Main Menu.
     */
    private boolean back;

    /**
     * This stores a file location of the highscores file.
     */
    private final File SCORES_FILE;

    /**
     * This stores a backup file location of the highscorse storing file, in case the directory of SCORES_FILE is inaccessible.
     */
    private final File SCORES_FILE_BACKUP;

    /**
     * @param stg The JavaFX Stage to display to.
     */
    public Highscores(Stage stg) {
        super(stg, "Highscores");
        back = false;
        SCORES_FILE = new File (System.getProperty("user.home") + "/Desktop/highScoresFile.wtr");
        SCORES_FILE_BACKUP = new File (System.getProperty("user.home") + "/highScoresFile.wtr");
    }

    /**
     * This method will display all the graphics of the Highscores window. It reads the file, sorts the scores, outputs them properly, and then rewrites the file to not have to store
     * more than the top ten scores, and to store them in an almost sorted order. The sort used is Insertion sort, because it works well for short lists, especially those that are already partially
     * sorted. This will be the case with the high scores file, as it gets updated with a sorted list every time this method is called.
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

        //Listener for MouseClick
        backButton.setOnMouseClicked(e -> {
            refresh();
            back = true;
            click.play();
        });
        // Listener for MouseEnter
        backButton.setOnMouseEntered(e -> setCursor(1));
        // Listener for MouseExit
        backButton.setOnMouseExited(e -> setCursor(0));

        drawImage(backButton, -410, -195); //button to return to menu
        String line = "";

        //read the High Scores file
        try{
            BufferedReader input = new BufferedReader (new FileReader (SCORES_FILE));
            while (line != null) //count how many entries there are for each lake level
            {
                line = input.readLine ();
                if (line == null)
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

            input = new BufferedReader (new FileReader (SCORES_FILE)); //read high scores file again
            line = " ";
            while (line != null)
            {
                line = input.readLine ();
                if (line == null)
                    break;
                if (line.equals ("level played:1") && lengths [0] != 0) //add entry to the first level's list
                {
                    names [0] [positions [0]] = input.readLine ();
                    scores [0] [positions [0]] = input.readLine ();
                    positions [0]++;
                }
                else if (line.equals ("level played:2") && lengths [1] != 0) //add entry to the second level's list
                {
                    names [1] [positions [1]] = input.readLine ();
                    scores [1] [positions [1]] = input.readLine ();
                    positions [1]++;
                }
                else if (line.equals ("level played:3") && lengths [2] != 0) //add entry to the third level's list
                {
                    names [2] [positions [2]] = input.readLine ();
                    scores [2] [positions [2]] = input.readLine ();
                    positions [2]++;
                }
            }

            String temp;
            String tempStr;
            input.close ();
            for (int y = 0 ; y < 3 ; y++) //insertion sort for the high scores
            {
                int x;
                for (int i = 1; i < lengths[y]; i++)
                {
                    temp = scores[y][i];
                    tempStr = names[y][i];
                    x = i - 1;
                    try{
                    while (x >= 0 && Integer.parseInt(scores[y][x]) < Integer.parseInt(temp)) //loop to shift items over
                    {
                        scores[y][x + 1] = scores[y][x];
                        names[y][x + 1] = names[y][x];
                        x--;
                    }}catch (NumberFormatException ignored){}
                    scores[y][x + 1] = temp;
                    names[y][x+1] = tempStr;
                }
            }

        }
        catch (IOException ignored){
            try{
                BufferedReader input = new BufferedReader (new FileReader (SCORES_FILE_BACKUP)); //if first file fails, second file will be read
                while (line != null)
                {
                    line = input.readLine ();
                    if (line == null) //count how many entries there are for each lake level
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

                input = new BufferedReader (new FileReader (SCORES_FILE_BACKUP)); //read high scores file again
                line = " ";
                while (line != null)
                {
                    line = input.readLine ();
                    if (line == null)
                        break;
                    if (line.equals ("level played:1") && lengths [0] != 0) //add entry to the first level's array
                    {
                        names [0] [positions [0]] = input.readLine ();
                        scores [0] [positions [0]] = input.readLine ();
                        positions [0]++;
                    }
                    else if (line.equals ("level played:2") && lengths [1] != 0) //add entry to the second level's array
                    {
                        names [1] [positions [1]] = input.readLine ();
                        scores [1] [positions [1]] = input.readLine ();
                        positions [1]++;
                    }
                    else if (line.equals ("level played:3") && lengths [2] != 0) //add entry to the third level's array
                    {
                        names [2] [positions [2]] = input.readLine ();
                        scores [2] [positions [2]] = input.readLine ();
                        positions [2]++;
                    }
                }

                String temp;
                String tempStr;
                input.close ();
                for (int y = 0 ; y < 3 ; y++) //insertion sort to sort the high scores
                {
                    int x;
                    for (int i = 1; i < lengths[y]; i++)
                    {
                        temp = scores[y][i];
                        tempStr = names[y][i];
                        x = i - 1;
                        try{
                            while (x >= 0 && Integer.parseInt(scores[y][x]) < Integer.parseInt(temp)) //loop to shift items over
                            {
                                scores[y][x + 1] = scores[y][x];
                                names[y][x + 1] = names[y][x];
                                x--;
                            }}catch (NumberFormatException ignored2){}
                        scores[y][x + 1] = temp;
                        names[y][x+1] = tempStr;
                    }
                }

            }
            catch (IOException ignored2){}
        }

        StringBuilder text = new StringBuilder();
        Text[][] list = {new Text[Math.min(lengths[0], 10)], new Text[Math.min(lengths[1], 10)], new Text[Math.min(lengths[0], 10)]}; // 2 dimensional array to store output Text objects for the high score lists
        for (int r = 0; r < list.length; r++)
        {
            for (int c = 0; c < list[r].length; c++)
            {
                list [r][c] = new Text();
                if (r < names.length && c < names[r].length)
                {
                    text.append(names[r][c]);
                    for (int i = 0; i < 6 - scores[r][c].length() + 10 - names[r][c].length(); i++)
                        text.append(" ");
                    text.append(scores[r][c]); //combine name and score to form output
                    list[r][c].setText(text.toString());
                    text = new StringBuilder();
                }
                list[r][c].setFont(Font.font("Consolas", 20));
                list[r][c].setFill(Color.WHITE);
                add (list[r][c], 215 * r + -32, 30 + 20 * c); //display output text item
            }
        }

        try{
            PrintWriter output = new PrintWriter (new BufferedWriter (new FileWriter (SCORES_FILE, false))); //rewrite information back to the file
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
        catch (IOException ignored){
            try{
                PrintWriter output = new PrintWriter (new BufferedWriter (new FileWriter (SCORES_FILE_BACKUP, false))); //rewrite to the backup file if the first file fails
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
            catch (IOException ignored2){}
        }

        AnimatedImage standing = (AnimatedImage)(Resources.get("standing"));

        //display an animation of the character standing on the screen
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

                if (back) { //user has clicked Menu Button
                    stop();
                    hideStage();
                }
            }
        }.start();
    }

}
