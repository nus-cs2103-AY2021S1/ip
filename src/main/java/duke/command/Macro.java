package duke.command;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import duke.Context;
import duke.exception.DukeException;
import duke.parser.Parser;

public class Macro {
    private final String name;
    private final List<String> commands;
    private final Options options;

    private Macro(String name, List<String> commands, Options options) {
        this.name = name;
        this.commands = commands;
        this.options = options;
    }

    public static Macro newMacro(String input) {
        // TODO error catching and throwing
        // note: following also trims whitespace.
        String[] inputSplitBySemicolon = input.split(" *; *");
        String[] macroNameAndArgs = inputSplitBySemicolon[0].split("\\s+");
        String[] commands = Arrays.copyOfRange(inputSplitBySemicolon, 1, inputSplitBySemicolon.length);
        Options options = new Options();
        for (int i = 1; i < macroNameAndArgs.length; i++) {
            String name = macroNameAndArgs[i];
            String description = "macro argument " + name;
            Option option = new Option(name, true, description);
            option.setRequired(true);
            options.addOption(option);
        }
        return new Macro(macroNameAndArgs[0], Arrays.asList(commands), options);
    }

    public String getName() {
        return this.name;
    }

    public Options getOptions() {
        return this.options;
    }

    public void execute(Context context, CommandLine args) throws DukeException {
        Parser parser = new Parser(context);
        try {
            for (String command : this.substituteAll(args)) {
                parser.parseAndRun(command);
            }
        } catch (DukeException e) {
            throw e; // TODO
        }
    }

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
