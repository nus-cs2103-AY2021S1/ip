/**
 * The Ui class holds commonly used phrases used by the bot to
 * communicate with the user.
 */
public class Ui {

    protected Parser parser;

    public Ui() {
        this.parser = new Parser();
    }

    public static String LINE = "___________________________________________________";

    /**
     * Utilises the parser to parse user commands.
     * @param line User input text.
     * @return Command understood from user text.
     */
    public Command parseCommand(String line) {
        return this.parser.commandHandler(line);
    }

    /**
     * Returns a line to the user.
     */
    public void lineBreak() {
        System.out.println(LINE);
    }

    /**
     * Outputs the introduction messages to the user.
     */
    public void intro() {
        this.lineBreak();
        System.out.println("Yo what's up! The name's Juke");
        System.out.println("What do you need?");
        this.lineBreak();
    }

}
