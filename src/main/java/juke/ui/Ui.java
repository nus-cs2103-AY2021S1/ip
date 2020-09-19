package juke.ui;

import juke.Parser;
import juke.command.Command;

/**
 * Represents a Ui class that handles messages to be output to user.
 */
public class Ui {

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
     * Outputs the introduction messages to the user.
     */
    public static String introMessage() {
        return "Yo what's up! The name's Juke\nWhat do you need?\n";
    }
}
