package Threaded;

public class Compiler {
    public Compiler(){
        Frame frame = new Frame();
        frame.getMG().displayScreen(new HangmanScreen(frame.getMG()));

    }
    public static void main(String[] args) {
        new Compiler();
    }
}
