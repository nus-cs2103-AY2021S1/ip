package duke.command;

/**
 * A command signally the end of program
 */
public class ExitCommand implements Command {

    /**
     * Exit program
     */
    @Override
    public void execute() {
        // Exit program
        System.exit(0);
    }

}
