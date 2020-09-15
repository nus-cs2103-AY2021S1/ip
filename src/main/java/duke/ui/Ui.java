package duke.ui;

import duke.main.Statement;
import duke.tools.Format;
import duke.tools.Parser;
import duke.tools.Response;

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
     * @return Parse object in this Ui.
     */
    public Parser getParser() {
        return this.parser;
    }

    /**
     * Responds after the user keyed in the input.
     *
     * @return String of the response from Duke.
     */
    public String getResponse(String input) {
        String extract = conciseInput(input);
        if (parser.isEnd(extract)) {
            return exit();
        }
        return Parser.run(extract);
    }

    /**
     * Eliminates the spaces
     * around the user input.
     *
     * @return A string with spaces around it.
     */
    public String conciseInput(String input) {
        return Format.shorten(input);
    }

    /**
     * Prints the beginning greeting that
     * a user will receive at the start of Duke operation.
     */
    public String greet() {
        return new Format<>(
                new Response(Statement.GREET.toString()))
                .toString();
    }

    /**
     * Prints the ending words that
     * a user will receive after he inputs "bye"
     */
    public String exit() {
        return new Format<>(
                new Response(Statement.BYE.toString()))
                .toString();
    }
}
