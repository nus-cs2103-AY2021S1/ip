package duke.command;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import duke.Context;
import duke.exception.DukeCustomException;
import duke.exception.DukeException;
import duke.parser.Parser;

/**
 * Macro class that deals with user-created macros. Possibly turing complete (this
 * is a bad thing and should be addressed).
 */
public class Macro {
    private final String name;
    private final List<String> commands;
    private final Options options;

    private Macro(String name, List<String> commands, Options options) {
        this.name = name;
        this.commands = commands;
        this.options = options;
    }

    /**
     * Creates a new macro using the String declaration.
     * @param declaration Macro declaration string. Format in the user guide.
     * @return Macro that was created
     * @throws DukeException if declaration has the wrong format.
     */
    public static Macro newMacro(String declaration) throws DukeException {
        // note: following also trims whitespace.
        String[] inputSplitBySemicolon = declaration.split(" *; *");
        String[] macroNameAndArgs = inputSplitBySemicolon[0].split("\\s+");
        String[] commands = Arrays.copyOfRange(inputSplitBySemicolon, 1, inputSplitBySemicolon.length);
        Options options = new Options();
        try {
            for (int i = 1; i < macroNameAndArgs.length; i++) {
                String name = macroNameAndArgs[i];
                String description = "macro argument " + name;
                Option option = new Option(name, true, description);
                option.setRequired(true);
                options.addOption(option);
            }
        } catch (IllegalArgumentException e) {
            throw DukeException.Errors.MACRO_DEFINITION_ERROR.create();
        }
        return new Macro(macroNameAndArgs[0], Arrays.asList(commands), options);
    }

    public String getName() {
        return this.name;
    }

    public Options getOptions() {
        return this.options;
    }

    /**
     * Executes the macro. Can be used as a CommandExecutable.
     * @param context context for execution.
     * @param args command line arguments parsed using commons-cli.
     * @throws DukeException if any command in the macro encounters an error.
     */
    public void execute(Context context, CommandLine args) throws DukeException {
        Parser parser = new Parser(context);
        String[] commands = this.substituteAll(args);
        int lastCommandIndex = 0;
        try {
            for (lastCommandIndex = 0; lastCommandIndex < commands.length; lastCommandIndex++) {
                parser.parseAndRun(commands[lastCommandIndex]);
            }
        } catch (DukeException e) {
            DukeCustomException toThrow = new DukeCustomException(e.getMessage());

            String extraMessage = "An error occurred when executing this command:\n"
                + commands[lastCommandIndex]
                + "\n\nThe following commands executed successfully:\n"
                + String.join("\n", Arrays.copyOfRange(commands, 0, lastCommandIndex))
                + "\n\n The following commands were not executed:\n"
                + String.join("\n", Arrays.copyOfRange(commands, lastCommandIndex + 1, commands.length));

            toThrow.setExtraMessage(extraMessage);
            throw toThrow;
        }
    }

    /**
     * Substitutes all the arguments into the macro, and returns the
     * substituted commands as a list. Exposed mainly for testing.
     * @param args
     * @return
     */
    public String[] substituteAll(CommandLine args) {
        String[] output = new String[this.commands.size()];
        for (int i = 0; i < this.commands.size(); i++) {
            output[i] = substitute(args, this.commands.get(i));
        }
        return output;
    }

    private String substitute(CommandLine args, String line) {
        // TODO possibly use a StringBuilder here instead.
        for (Iterator<Option> i = args.iterator(); i.hasNext(); ) {
            Option option = i.next();
            String from = option.getOpt();
            String to = args.getOptionValue(from);
            line = line.replaceAll("\\\\" + from, to);
        }

        String unusedArgs = String.join(" ", args.getArgList());
        // regex is matching for "\$".
        return line.replaceAll("\\\\\\$", unusedArgs).trim();
    }
}
