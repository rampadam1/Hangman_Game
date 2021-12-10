package Threaded;

public class Compiler {
    public Compiler(){
        Frame frame = new Frame();
        frame.getMG().displayScreen(new StartScreen(frame.getMG()));

    }
    public static void main(String[] args) {
        new Compiler();
    }
}
