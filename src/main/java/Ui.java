public class Ui {

    protected Parser parser;

    public Ui() {
        this.parser = new Parser();
    }

    public static String LINE = "___________________________________________________";

    public Command parseCommand(String line) {
        return this.parser.commandHandler(line);
    }

    public void lineBreak() {
        System.out.println(LINE);
    }

    public void intro() {
        this.lineBreak();
        System.out.println("Yo what's up! The name's Juke");
        System.out.println("What do you need?");
        this.lineBreak();
    }

}
