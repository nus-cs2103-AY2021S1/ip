package duke.core.command;

import java.util.logging.Logger;

import duke.designpattern.command.Executable;

/**
 * A command signally the end of program
 */
public class ExitCommand implements Executable {

    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    /**
     * Exit program
     */
    @Override
    public void execute() {
        // Exit program
        logger.info(ExitCommand.class.getSimpleName() + ": Exiting");
        System.exit(0);
    }

}
