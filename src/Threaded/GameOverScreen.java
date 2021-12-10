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
        this.state = state;
    }

    @Override
    public void draw(Graphics2D g) {

        double winRate = Math.round(((double)(Game.wins)/(Game.wins+Game.losses)*100));



        JButton button1= new JButton("Yes");
        JButton button2= new JButton("No");
        MG.getFrame().getGame().add(button1);
        MG.getFrame().getGame().add(button2);
        button1.addActionListener(e -> {
            MG.getFrame().getGame().removeAll();
            HangmanScreen.state = -1;
            MG.displayScreen(new HangmanScreen(MG));

        });
        button2.addActionListener(e -> System.exit(0));







        if (state == 0){
            MG.getFrame().getGame().setBackground(new Color(243, 212, 68));
            g.setColor(Color.WHITE);
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
            g.drawString("You Won!", getFrameWidth()/2-100,getFrameHeight()/2+50);
            try {
                g.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/Normal/HangmanStateWin.001.png")))
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
                g.drawImage(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/Normal/HangmanState6.001.png")))
                        ,getFrameWidth()/2-150,getFrameHeight()/2-200,300,200,null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        g.drawString("Win rate: " + winRate + "%", getFrameWidth()/2-100,getFrameHeight()/2+80 );
        g.drawString("Play Again?", getFrameWidth()/2-60, getFrameHeight()/2+110);
        button1.setBounds(getFrameWidth()/2-60,getFrameHeight()/2+120,60,30 );
        button2.setBounds(getFrameWidth()/2,getFrameHeight()/2+120,60,30 );
    }
}
