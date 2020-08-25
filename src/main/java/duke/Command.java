package duke;

/**
 * Encapsulates an Abstract Command class
 */
public abstract class Command {
    String[] parsedCommand;

    /**
     * Instantiates a Command.
     * @param parsedCommand
     */
    public Command(String[] parsedCommand) {
        this.parsedCommand = parsedCommand;
    }

    /**
     * Abstract method that executes a command.
     * @param tasks tasklist containing tasks
     * @param ui to interact with user
     * @param storage to read and write date to file
     * @throws DukeException if commands are not valid
     */
    abstract void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines if it is the end of a program.
     * @return boolean to indicate if end of program
     */
    boolean isExitProgram() {
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for (String str : parsedCommand) {
            result += str;
        }
        return result;
    }
}
