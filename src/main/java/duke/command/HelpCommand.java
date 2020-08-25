package duke.command;

import java.util.Arrays;

/**
 * List all available Commands
 */
public class HelpCommand implements Command {

    /**
     * List all available Commands
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
