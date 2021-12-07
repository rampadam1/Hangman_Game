import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
//Everytime the game ends the player sees this. It tells him if he won or lost and lets him chose to play again or stop playing
public class RestartEndPage extends JFrame {
    protected  static int difficult = 2;
    protected static int wins = 0;
    protected static int looses = 0;
    double average = Math.round(((double) (wins) / (double) (looses + wins) * 100));
    private final int condition;//0 it's won and 1 its lost

    public RestartEndPage(int condition, String s) {
        this.condition = condition;
        Color col;
        int height = 500;
        int width = 250;

        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JButton Yes = new JButton("Yes");
        JButton No = new JButton("No");
        JLabel winLoose = new JLabel();
        JLabel reaction = new JLabel();
        JLabel word = new JLabel();
        JLabel label = new JLabel("Do you want to play again?");
        JLabel stats = new JLabel("Wins :" + wins + "; Looses: " + looses + "; Winrate: " + average + "%");
        Action c = new Action();
        ActionClose d = new ActionClose();

        Yes.setBackground(new Color(71, 128, 38));
        Yes.setOpaque(true);
        Yes.setBorderPainted(false);
        No.setBackground(new Color(204, 38, 38));
        No.setOpaque(true);
        No.setBorderPainted(false);

        Yes.addActionListener(c);
        No.addActionListener(d);


        if (condition == 0) {
            col = new Color(243, 212, 68);
            panel2.setBackground(new Color(243, 212, 68));
            panel3.setBackground(new Color(243, 212, 68));
            winLoose.setIcon(icon(setAccordingly(), width, (height + 70) / 3));
            reaction.setText("Good Job");
            word.setText(" ");
        } else {
            col = new Color(82, 10, 10);
            panel2.setBackground(new Color(82, 10, 10));
            panel3.setBackground(new Color(82, 10, 10));
            winLoose.setIcon(icon(setAccordingly(), width, (height + 100) / 3));
            reaction.setText("Better luck next time");
            reaction.setForeground(Color.WHITE);
            word.setText("The word was: " + s);
            word.setForeground(Color.WHITE);
            stats.setForeground(Color.WHITE);
            label.setForeground(Color.WHITE);
        }


        panel.setBackground(new Color(12, 43, 55));
        panel.setLayout(new GridLayout(1, 2));
        panel.add(No);
        panel.add(Yes);


        panel2.setLayout(new GridLayout(6, 1));
        panel2.add(reaction);
        panel2.add(word);
        panel2.add(stats);
        panel2.add(StartPage.dropDown(col));
        panel2.add(label);
        panel2.add(panel, -1);


        panel3.add(winLoose);

        setSize(new Dimension(width, height));
        setLayout(new GridLayout(2, 1));
        add(panel3);
        validate();
        add(panel2);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            difficult = StartPage.drop.getSelectedIndex();
            dispose();
            new Hangman();
        }
    }

    public class ActionClose implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    /*
     * Uses a BufferedImage to get the requested image from the file, formats it with the requested width and height
     * and turns it into an ImageIcon for JLabel to display.
     * */
    public ImageIcon icon(String file, int width, int height) {
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

    public String setAccordingly() {
        if (condition == 0) {
            if (difficult == 0) {
                return "resources/images/Normal/HangmanStateWin.001.png";
            } else if (difficult == 1) {
                return "resources/images/Easy/HangmanStateWin.001.png";
            } else if (difficult == 2) {
                return "resources/images/Normal/HangmanStateWin.001.png";
            } else if (difficult == 3) {
                return "resources/images/Hard/HangmanStateWin.001.png";
            } else if (difficult == 4) {
                return "resources/images/Impossible/HangmanStateWin.001.png";
            } else if (difficult == 5) {
                return "resources/images/Impossible/HangmanStateWin.001.png";
            }
        } else{

            if (difficult == 0) {
                return "resources/images/Normal/HangmanStateWin.001.png";
            } else if (difficult == 1) {
                return "resources/images/Easy/HangmanState12.001.png";
            } else if (difficult == 2) {
                return "resources/images/Normal/HangmanState6.001.png";
            } else if (difficult == 3) {
                return "resources/images/Hard/HangmanState4.001.png";
            } else if (difficult == 4) {
                return "resources/images/Impossible/HangmanState3.001.png";
            } else if (difficult == 5) {
                return "resources/images/Impossible/HangmanState3.001.png";
            }

        }
        return "resources/images/ErrorLoadingImageException.001.png";

    }//Sets the win/ lose image according to the game's difficulty. Training always gives a win




}
