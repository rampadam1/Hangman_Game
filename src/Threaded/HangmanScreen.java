package Threaded;

import Normal.RestartEndPage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HangmanScreen extends Screen{

    protected static int state = -1;

    protected static int counter = 0;
    protected static int looseCond = 6;//How many tries the user has


    protected static String letterUsed = "Letters Used: ";
    protected static String wordLabel;
    protected static String notif = " ";

    protected static HangmanLogicAlgorithm alg;
    protected static String word ;

    protected static int[] number; // Array of ones and zeros with the length of the word. When the array is full of ones the game is won
    protected static final ArrayList<String> used = new ArrayList<>();//Holds all used characters

    protected static String filepathStarter;


    protected static BufferedImage img;

    public HangmanScreen(Menager mg) {
        super(mg);
        KeyHandler.gameIsStopped = false;
        checkDifficulty();

        MG.getFrame().getGame().setBackground(new Color(86, 56, 217));

        alg = new HangmanLogicAlgorithm("resources/words/hangman.txt");
        word = alg.getWord();
        number = new int[word.length()];
        wordLabel = alg.hiddenBuilder(number);

        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filepathStarter+"0.001.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        MG.getFrame().requestFocus();
    }


    @Override
    public void draw(Graphics2D g) {
        if(state ==-1) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            g.drawString(notif, getFrameWidth() / 2 - 170, getFrameHeight() / 2);
            g.drawString(wordLabel, getFrameWidth() / 2 - 170, getFrameHeight() / 2 + 50);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
            g.drawString(letterUsed, getFrameWidth() / 2 - 170, getFrameHeight() / 2 + 100);
            g.drawImage(img, getFrameWidth() / 2 - 170, getFrameHeight() / 2 - 200, 300, 200, null);
        }else{
            MG.displayScreen(new GameOverScreen(MG,state));
        }
    }


    public void checkDifficulty(){
      if(Game.difficult == 0){
            looseCond = 12;
            filepathStarter = "images/Easy/HangmanState";
        }else if(Game.difficult == 1){
            looseCond = 6;
            filepathStarter = "images/Normal/HangmanState";
        }else if(Game.difficult == 2){
            looseCond = 4;
            filepathStarter = "images/Hard/HangmanState";
        }
    }
}
