public class Ui {
    private Parser parser;

    public Ui(){
        parser=new Parser();
    }

    public void run(){
        parser.processInput();
    }
}
