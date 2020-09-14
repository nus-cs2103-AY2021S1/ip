package juke;

import juke.command.Command;

/**
 * Represents a Ui class that holds commonly used phrases used by the bot to
 * communicate with the user.
 */
public class Ui {

    private static final String LINE = "___________________________________________________";

    private Parser parser;

    public Ui() {
        this.parser = new Parser();
    }

    /**
     * Utilises the parser to parse user commands.
     *
     * @param line User input text.
     * @return Command understood from user text.
     */
    public Command parseCommand(String line) {
        return this.parser.commandHandler(line);
    }

    /**
     * Returns a line to the user.
     */
    public String lineBreak() {
        return LINE + "\n";
    }

    /**
     * Outputs the introduction messages to the user.
     */
    public String intro() {
        return this.lineBreak()
                + "Yo what's up! The name's Juke\n"
                + "What do you need?\n"
                + this.lineBreak();
    }

}
