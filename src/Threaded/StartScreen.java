package Threaded;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends Screen{


    public StartScreen(Menager mg) {
        super(mg);
        MG.getFrame().getGame().setBackground(new Color(86, 56, 217));
    }

    @Override
    public void draw(Graphics2D g) {

        String[] difficulties = {"Easy", "Normal", "Hard"};
        JComboBox<String> drop = new JComboBox<>(difficulties);

        JButton start = new JButton("Start");
        drop.setSelectedIndex(Game.difficult);

        MG.getFrame().getGame().add(drop);
        MG.getFrame().getGame().add(start);

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
        g.drawString("Difficulties", getFrameWidth()/2-30, getFrameHeight()/2-80);
        drop.setBounds(getFrameWidth()/2-100, getFrameHeight()/2-60,200,50);
        start.setBounds(getFrameWidth()/2-25, getFrameHeight()/2+20, 50,20);
        drop.setVisible(true);

        start.addActionListener(e ->{
                KeyHandler.gameIsStopped = false;
                Game.difficult = drop.getSelectedIndex();
                MG.getFrame().getGame().removeAll();
                MG.displayScreen(new HangmanScreen(MG));

    });

    }




}
