package luoyi.duke.commands;

import luoyi.duke.data.IDuke;

/**
 * Parent of all command classes.
 */
public class Command {
    protected final int targetIndex;
    protected final IDuke duke;

    protected Command(int targetIndex, IDuke duke) {
        this.targetIndex = targetIndex;
        this.duke = duke;
    }

    /**
     * Execute a command and return the resultant Duke object.
     * Command must be initiated with a Duke object first.
     *
     * @return Resultant Duke object after execution.
     */
    public IDuke execute() {
        throw new UnsupportedOperationException("This method is to"
                + " be implemented by child classes.");
    }

    /**
     * Initiate command with Duke object.
     *
     * @param duke Duke object.
     * @return Command object after initiation.
     */
    public Command setDuke(IDuke duke) {
        return new Command(targetIndex, duke);
    }

}
