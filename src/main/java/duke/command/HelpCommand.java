package duke.command;

import duke.cmd.Parser;

import java.util.Arrays;

/**
 * Display list of available duke.command
 */
public class HelpCommand implements Command {

    /**
     * Display all available commands
     */
    @Override
    public void execute() {
        System.out.println("Command list:");
        Arrays.stream(CommandEnum.values())
                .map((p) -> " " + p.toString().toLowerCase())
                .forEach(System.out::print);
        System.out.println();
    }

}
