package Threaded;

import java.awt.*;

public abstract class Screen {
    protected final Menager MG;

    public Screen(Menager mg) {
        MG = mg;
    }

    public abstract void draw(Graphics2D g);

    public int getFrameWidth(){return MG.getFrame().getWidth();}
    public int getFrameHeight(){return MG.getFrame().getHeight();}
}
