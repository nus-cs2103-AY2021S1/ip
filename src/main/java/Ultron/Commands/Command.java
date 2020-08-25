package ultron.commands;

import ultron.Storage;
import ultron.UI;
import ultron.TaskList;
import ultron.exceptions.UltronException;

public abstract class Command {
    /**
     * Boolean denoting if Ultron should exit after execution.
     */
    private final boolean isExit;

    /**
     * String stroing arguments for the command.
     */
    private final String arguments;

    /**
     * Abstract class Command which all Commands inherit from.
     * @param isExit    boolean denoting if Ultron should exit after executing
     * @param arguments Argument for the command
     */
    public Command(boolean isExit, String arguments){
        this.isExit = isExit;
        this.arguments = arguments;
    }

    /**
     * Execution for the command which inherits the class.
     * @param taskList  List of tasks
     * @param ui        UI for Ultron
     * @param storage   Storage for Ultron
     * @throws UltronException
     */
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws UltronException;

    /**
     * Checks if Ultron should exit after the command.
     * @return boolean isExit
     */
    public boolean isExit(){
        return isExit;
    }

    /**
     * Gets the arguments for the command.
     * @return String arguments
     */
    protected String getArguments(){
        return this.arguments;
    }

}
