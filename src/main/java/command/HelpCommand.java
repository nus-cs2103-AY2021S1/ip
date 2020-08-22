package command;

import cmd.Parser;

/**
 * Display list of available command
 */
public class HelpCommand extends Command {

    @Override
    public boolean isModifying() {
        return false;
    }

    @Override
    public void execute() {
        System.out.println("Command list:");
        for (Parser p : Parser.values()) {
            System.out.print(" " + p.toString().toLowerCase());
        }
        System.out.println();
    }

    @Override
    public void undo() {
        // Operation Unsupported
        System.out.println("Undo: HelpCommand");
    }
}
