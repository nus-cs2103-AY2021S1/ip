package ultron.commands;

import ultron.Storage;
import ultron.TaskList;
import ultron.exceptions.UltronException;
import ultron.ui.UI;

public abstract class Command {
    /** Boolean denoting if Ultron should exit after execution. */
    private final boolean isExit;

    /** String string arguments for the command. */
    private final String arguments;

    /**
     * Abstract class Command which all Commands inherit from.
     *
     * @param isExit    boolean denoting if Ultron should exit after executing.
     * @param arguments Argument for the command.
     */
    public Command(final boolean isExit, final String arguments) {
        this.isExit = isExit;
        this.arguments = arguments;
    }

    /**
     * Executes the command which inherits the class.
     *
     * @param taskList List of tasks.
     * @param ui       UI for Ultron.
     * @param storage  Storage for Ultron.
     * @throws UltronException when there are errors when executing.
     */
    public abstract void execute(TaskList taskList,
                                 UI ui,
                                 Storage storage) throws UltronException;

    /**
     * Checks if Ultron should exit after the command.
     *
     * @return boolean isExit.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Gets the arguments for the command.
     *
     * @return String arguments.
     */
    protected String getArgument() {
        return this.arguments;
    }

}
