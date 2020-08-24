package duke.command;

import duke.cmd.Parser;

import java.util.Arrays;

/**
 * Display list of available duke.command
 */
public class HelpCommand extends Command {

    @Override
    public boolean hasUndo() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Command list:");
        Arrays.stream(Parser.values())
                .map((p) -> " " + p.toString().toLowerCase())
                .forEach(System.out::print);
        System.out.println();
    }

    @Override
    public void undo() {
        // Operation Unsupported
        System.out.println("Undo: HelpCommand");
    }
}
