package duke.command;

import java.util.Arrays;

/**
 * Display list of available Command
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
