package Normal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//The start menu. The player chooses the difficulty. Only runs once and is never seen until the program
//is started again
public class StartPage extends JFrame {


    public static JComboBox<String> drop;

    public StartPage(){
        JPanel panel = new JPanel();
        JButton button = new JButton("Start");
        JLabel label = new JLabel("Welcome to hangman. Guess the word to win");
        Action b = new Action();
        button.addActionListener(b);

        int height = 150;
        int width = 300;

        panel.setPreferredSize(new Dimension(width, height));
        panel.setLayout(new GridLayout(3,1));
        panel.setBackground(new Color(98, 199, 152));
        panel.add(dropDown(new Color(98, 199, 152)),0);
        panel.add(label, 1);
        panel.add(button,2);

        setSize(new Dimension(width,height));
        add(panel);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     *
     * Creates a JPanel with a dropdown menu that contains the difficulties.
     * */
    public static JPanel dropDown(Color col){
        JPanel panel = new JPanel();
        JButton button = new JButton("V");
        JLabel label = new JLabel("Difficulty");
        String[] difficulties = {"Training","Easy", "Normal", "Hard", "Impossible"};
        drop = new JComboBox<>(difficulties);
        drop.setSelectedIndex(RestartEndPage.difficult);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBackground(col);
        panel.add(label);
        panel.add(drop);

        return panel;
    }

    public class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            RestartEndPage.difficult = drop.getSelectedIndex();

            dispose();
            new Hangman();
        }
    }



    public static void main(String[] args) {
        new StartPage();
    }
}
