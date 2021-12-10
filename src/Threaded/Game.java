package Threaded;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Game extends JPanel implements Runnable{
    private final Frame frame;
    protected static int losses =0;
    protected static int wins = 0;
    protected static int difficult = 1;

    private final static int fps = 30;


    public Game(Frame frame){
        this.frame = frame;
        setFocusable(true);
        setLayout(null);
        requestFocus();


    }    @Override
    public void run() {
        long startTime;
        long TimeMills;
        long waitTime;
        long targetTime = 1000/fps;
        while(true){
            startTime = System.nanoTime();




            TimeMills = (System.nanoTime() - startTime)/1_000_000;
            waitTime = targetTime - TimeMills;

            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            repaint();
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(Map.of(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        if(frame.getMG().getScreen() != null)
            frame.getMG().getScreen().draw(g2);

    }
}
