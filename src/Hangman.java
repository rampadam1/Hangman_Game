import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class Hangman extends JFrame{

    private int counter = 0;
    private int looseCond;//How many tries the user has
    private String filepathStarter;
    private String fileThingy;

    private final JLabel lettersUsed;// Shows all used characters
    private final JLabel wordLabel;//Contains the word
    private final JLabel notif;//A label to give a notice to the user about his input
    private final JLabel hanger; // The image stays here
    private final JTextArea txt;

    private final HangmanLogicAlgorithm alg;
    private final String word ;

    protected int[] number; // Array of ones and zeros with the length of the word. When the array is full of ones the game is won
    protected ArrayList<String> used = new ArrayList<>();//Holds all used characters



    public Hangman(){


        checkDifficulty();

        alg = new HangmanLogicAlgorithm("resources/words/"+fileThingy);
        word = alg.getWord();
        number = new int[word.length()];
        final int rows = 5;
        final int cols = 1;

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        hanger = new JLabel();

        hanger.setIcon(icon(filepathStarter+"0.001.png", 380, 650/2));

        lettersUsed = new JLabel("Used Letters: ");
        wordLabel = new JLabel(alg.hiddenBuilder(number));
        notif = new JLabel(" ");


        JButton button = new JButton("Go");
        Action a = new Action();
        button.addActionListener(a);

        txt = new JTextArea(1,5);
        JScrollPane scroll = new JScrollPane(txt);
        txt.setLineWrap(true);


        panel2.setBackground(new Color(98, 199, 152));
        panel2.add(hanger);


        panel.setLayout(new GridLayout(rows,cols));
        panel.setBackground(new Color(98, 199, 152));

        panel3.setBackground(new Color(98, 199, 152));
        panel3.add(scroll,BorderLayout.CENTER);

        panel.add(wordLabel,0);
        panel.add(lettersUsed,1);
        panel.add(notif,2);
        panel.add(panel3, 3);
        panel.add(button, -1);


        add(panel2, BorderLayout.NORTH);
        validate();
        add(panel);


        setLayout(new GridLayout(2,1));
        setPreferredSize(new Dimension(380, 650));
        setTitle("Hangman");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setFocusable(true);
        setLocationRelativeTo(null);



    }

    //Gets the user input, runs checks, send information to the HangmanLogicAlgorithm to check on the state of the game and the word
    public class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            String input = txt.getText();
            txt.setText(null);

            if(input.length() > 1){
                input = String.valueOf(input.charAt(0));
            }
            int t;

            try{
             t = input.charAt(0);
            }catch(Exception y){
               t = 0;
            }

            if(fileThingy.equals("hangman.txt")) {
                if (t<65||t>90 && t < 97 || t > 122 ) {
                    notif.setText("That's an illegal character!");
                    return;
                }

                for(String i : used){
                    if(input.equalsIgnoreCase(i)){
                        notif.setText(input.toUpperCase() + " has already been used!");
                        return;
                    }

                }

                if(word.contains(input)){//The character is in the word
                    number = alg.checkAllIndexes(number, input, false);
                    notif.setText("You guessed correctly!");
                    wordLabel.setText(alg.hiddenBuilder(number));

                }else{ //The character is not in the word

                    notif.setText("Looks like you guessed incorrectly!");
                    counter++;
                    hanger.setIcon(icon(filepathStarter + counter +".001.png", 380, 650/2));

                }

            }else if(fileThingy.equals("moreWords.txt")){
                if (t < 32 || t > 176) {
                    notif.setText("That's an illegal character!");
                    return;
                }

                for(String i : used){
                    if(input.equals(i)){
                        notif.setText(input + " has already been used!");
                        return;
                    }

                }

                if(word.contains(input)){//The character is in the word
                    number = alg.checkAllIndexes(number, input,true);
                    notif.setText("You guessed correctly!");
                    wordLabel.setText(alg.hiddenBuilder(number));

                }else{ //The character is not in the word

                    notif.setText("Looks like you guessed incorrectly!");
                    counter++;
                    hanger.setIcon(icon(filepathStarter + counter +".001.png", 380, 650/2));

                }
            }





            used.add(input);
            StringBuilder br = new StringBuilder("Words Used: ");
            for(int i = 0; i<used.size()-1; i++){
                br.append(used.get(i)).append(", ");
            }
            br.append(used.get(used.size()-1));
            lettersUsed.setText(br.toString());//StringBuilder creates a string that displays all used characters.
                                                //The string is than added to the JLabel



            if(alg.isWon(number)){ // Game is won
                RestartEndPage.wins++;

                new RestartEndPage(0,word);

                dispose();

            } else if(counter == looseCond){ //Game is lost
                RestartEndPage.looses++;

                wordLabel.setText(word);
                new RestartEndPage(1,word);

                dispose();
            }




        }
    }

    public void checkDifficulty(){
        if(RestartEndPage.difficult == 0){
            looseCond = 100;
            fileThingy = "hangman.txt";
        }else if(RestartEndPage.difficult == 1){
            looseCond = 12;
            fileThingy = "hangman.txt";
            filepathStarter = "resources/images/Easy/HangmanState";
        }else if(RestartEndPage.difficult == 2){
            looseCond = 6;
            fileThingy = "hangman.txt";
            filepathStarter = "resources/images/Normal/HangmanState";
        }else if(RestartEndPage.difficult == 3){
            looseCond = 4;
            fileThingy = "hangman.txt";
            filepathStarter = "resources/images/Hard/HangmanState";
        }else if(RestartEndPage.difficult == 4){
            looseCond = 3;
            fileThingy = "moreWords.txt";
            filepathStarter = "resources/images/Impossible/HangmanState";
        }
    }

    /*
     * Uses a BufferedImage to get the requested image from the file, formats it with the requested width and height
     * and turns it into an ImageIcon for JLabel to display.
     * */
    public ImageIcon icon(String file, int width, int height){
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(file));
        } catch (Exception e) {
            try {
                img = ImageIO.read(new File("src/ErrorLoadingImageException.001.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Image OImg = Objects.requireNonNull(img).getScaledInstance(width, height,
                Image.SCALE_SMOOTH);

        return new ImageIcon(OImg);
    }

}
