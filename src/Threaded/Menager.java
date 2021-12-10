package Threaded;

public class Menager {

    private final Frame frame;
    private Screen screen;

    public Menager(Frame frame){this.frame = frame;}

    public void displayScreen(Screen screen){
        this.screen = screen;
    }

    public Screen getScreen(){return screen;}

    public Frame getFrame(){return frame;}

}
