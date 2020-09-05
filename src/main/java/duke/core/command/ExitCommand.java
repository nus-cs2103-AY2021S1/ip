package duke.core.command;

import duke.designpattern.command.Executable;

/**
 * A command signally the end of program
 */
public class ExitCommand implements Executable {

    /**
     * Exit program
     */
    @Override
    public void execute() {
        // Exit program
        System.exit(0);
    }

}
