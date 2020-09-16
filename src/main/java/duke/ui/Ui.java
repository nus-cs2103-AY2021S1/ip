package duke.ui;

import duke.command.Command;
import duke.command.Response;
import duke.exception.DukeException;
import duke.main.Statement;
import duke.tools.Format;
import duke.tools.Parser;

/**
 * Interacts with the user.
 */
public class Ui {
    private Parser parser;

    /**
     * Initializes a Ui.
     */
    public Ui() {
        parser = new Parser();
    }

    /**
     * Returns the Parser object in this Ui.
     *
     * @return parse object in this Ui.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Responds after the user keyed in the input.
     *
     * @return string of the response from Duke.
     */
    public Command getCommand(String input) throws DukeException {
        String extract = conciseInput(input);
        return Parser.run(extract);
    }

    /**
     * Eliminates the spaces
     * around the user input.
     *
     * @return a string with spaces around it.
     */
    public String conciseInput(String input) {
        return Format.shorten(input);
    }

    /**
     * Prints the beginning greeting that
     * a user will receive at the start of Duke operation.
     */
    public String greet() {
        return new Response(Statement.GREET.toString()).toString();
    }
}
