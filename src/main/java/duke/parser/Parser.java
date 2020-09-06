package duke.parser;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import duke.Context;
import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeParseException;

/**
 * Class to parse raw input from user and run commands from the input.
 */
public class Parser {
    private final Context context;
    private final CommandLineParser parser;
    private final HelpFormatter formatter;

    /**
     * Constructor for Parser.
     * @param context Context object.
     */
    public Parser(Context context) {
        assert context != null : "context cannot be null";
        this.context = context;
        this.parser = new DefaultParser();
        this.formatter = new HelpFormatter();
    }

    // TODO test
    private String getHelp(String command, Options options) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        this.formatter.printHelp(
            printWriter,
            this.formatter.getWidth(),
            command,
            "",
            options,
            this.formatter.getLeftPadding(),
            this.formatter.getDescPadding(),
            "");
        return stringWriter.toString();
    }

    /**
     * Parses the String input, then runs the respective Command (if input is valid)
     * using the Parser object's internal TaskList and Ui objects.
     * @param input String raw input from user.
     * @throws DukeException If there are any parse errors, or Command has any errors.
     */
    public void parseAndRun(String input) throws DukeException {
        String[] inputSplitBySpace = input.split("\\s+");
        if (inputSplitBySpace.length == 0) {
            // TODO should this happen ever? change w assertion?
            throw DukeException.Errors.UNKNOWN_COMMAND.create();
        }
        String commandName = inputSplitBySpace[0];
        String[] args = Arrays.copyOfRange(inputSplitBySpace, 1, inputSplitBySpace.length);
        Command command = Command.getCommandByName(commandName);
        Options options = command.getOptions();
        try {
            CommandLine cmd = this.parser.parse(options, args);
            command.dispatch(this.context, cmd);
        } catch (ParseException e) {
            DukeParseException toThrow = new DukeParseException(e.getMessage());
            toThrow.setExtraMessage(getHelp(commandName, options));
            throw toThrow;
        } catch (DukeParseException toThrow) {
            toThrow.setExtraMessage(getHelp(commandName, options));
            throw toThrow;
        }
    }
}
