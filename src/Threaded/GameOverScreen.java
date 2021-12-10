package Threaded;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static Threaded.HangmanScreen.*;

public class GameOverScreen extends Screen{
    private final int state;



    public GameOverScreen(Menager mg, int state) {
        super(mg);
        MG.getFrame().requestFocus();
        this.state = state;
    }

    @Override
    public void draw(Graphics2D g) {
        String[] difficulties = {"Easy", "Normal", "Hard"};
        JComboBox<String> drop = new JComboBox<>(difficulties);
        MG.getFrame().getGame().add(drop);
        drop.setSelectedIndex(Game.difficult);

        double winRate = Math.round(((double)(Game.wins)/(Game.wins+Game.losses)*100));



        JButton Yes= new JButton("Yes");
        JButton No= new JButton("No");
        MG.getFrame().getGame().add(Yes);
        MG.getFrame().getGame().add(No);
        Yes.addActionListener(e -> {
            counter = 0;
            Game.difficult = drop.getSelectedIndex();
            MG.getFrame().getGame().removeAll();
            HangmanScreen.state = -1;
            MG.displayScreen(new HangmanScreen(MG));

        });
        No.addActionListener(e -> System.exit(0));

        Yes.setBackground(new Color(71, 128, 38));
        Yes.setOpaque(true);
        Yes.setBorderPainted(false);
        No.setBackground(new Color(204, 38, 38));
        No.setOpaque(true);
        No.setBorderPainted(false);







        if (state == 0){
            MG.getFrame().getGame().setBackground(new Color(243, 212, 68));
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            g.drawString("You Won!", getFrameWidth()/2-100,getFrameHeight()/2+50);
            try {
                g.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filepathStarter+"Win.001.png")))
                        ,getFrameWidth()/2-150,getFrameHeight()/2-200,300,200,null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            MG.getFrame().getGame().setBackground(new Color(82, 10, 10));
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            g.drawString("You Lost", getFrameWidth()/2-100,getFrameHeight()/2+50);
            g.drawString("The word was: "+word, getFrameWidth()/2-100,getFrameHeight()/2);
            try {
                g.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filepathStarter+counter+".001.png")))
                        ,getFrameWidth()/2-150,getFrameHeight()/2-200,300,200,null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        g.drawString("Win rate: " + winRate + "%", getFrameWidth()/2-100,getFrameHeight()/2+80 );
        g.drawString("Play Again?", getFrameWidth()/2-60, getFrameHeight()/2+110);
        Yes.setBounds(getFrameWidth()/2-70,getFrameHeight()/2+120,70,30 );
        No.setBounds(getFrameWidth()/2,getFrameHeight()/2+120,70,30 );


        drop.setBounds(getFrameWidth()/2-100, getFrameHeight()/2+150,200,50);
        drop.setVisible(true);
    }


}
