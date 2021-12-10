package Threaded;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import static Threaded.HangmanScreen.*;

public class KeyHandler implements KeyListener {

    protected final boolean[] keyBP = new boolean[256];
    protected static boolean gameIsStopped = false;
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!gameIsStopped){
        if(e.getKeyCode()<65||e.getKeyCode()>90 && e.getKeyCode() < 97 || e.getKeyCode() > 122 ) {
           HangmanScreen.notif = "That's an illegal character!";
        }else{
            if (!keyBP[e.getKeyCode()]) {
                keyBP[e.getKeyCode()] = true;
                char input = (char)e.getKeyCode();

                if(word.contains(String.valueOf(input).toLowerCase())){//The character is in the word
                    number = alg.checkAllIndexes(number, String.valueOf(input));
                    notif = "You guessed correctly!";
                    wordLabel = (alg.hiddenBuilder(number));

                }else{ //The character is not in the word

                    notif = "Looks like you guessed incorrectly!";
                    counter++;
                    try {
                        img = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/Normal/HangmanState"+counter+".001.png")));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }

                used.add(String.valueOf(input));
                StringBuilder br = new StringBuilder("Letters Used: ");
                for(int i = 0; i<used.size()-1; i++){
                    br.append(used.get(i)).append(", ");
                }
                br.append(used.get(used.size()-1));
                letterUsed = (br.toString());


                if(alg.isWon(number)){ // Game is won
                    gameIsStopped = true;
                    Arrays.fill(keyBP, false);
                    used.clear();
                    notif = " ";
                    wordLabel = null;
                    counter = 0;
                    letterUsed = "Letters Used: ";
                    Arrays.fill(number,0);
                    Game.wins++;
                    state = 0;


                } else if(counter == looseCond){ //Game is lost
                    gameIsStopped = true;
                    Arrays.fill(keyBP, false);
                    used.clear();
                    notif = " ";
                    wordLabel = null;
                    counter = 0;
                    letterUsed = "Letters Used: ";
                    Arrays.fill(number,0);
                    Game.losses++;
                    state = 1;

                }

            }else{
                HangmanScreen.notif = ((char) e.getKeyCode() + " has already been used!");
            }
         }
        }else{

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
