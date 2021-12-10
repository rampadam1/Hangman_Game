package Threaded;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    private final Menager MG;
    private final KeyHandler KH;
    private final Game game;

    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(380,650));
        setLocationRelativeTo(null);
        setFocusable(true);
        requestFocus();
        pack();
        MG = new Menager(this);
        KH = new KeyHandler();
        game = new Game(this);

        add(game);
        addKeyListener(KH);
        new Thread(game).start();


        setVisible(true);
    }

    public Game getGame(){return game;}
    public Menager getMG(){return MG;}
}
