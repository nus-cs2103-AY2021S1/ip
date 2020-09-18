package duke;

/**
 * Command encapsulates what the user intends to do based on the user's input.
 */
public abstract class Command {
    /**
     * Types of Commands supported by Duke.
     */
    public static final int LIST = 1;
    public static final int DONE = 2;
    public static final int DELETE = 3;
    public static final int FIND = 4;
    public static final int INVALID = 99;
    public static final int CREATE_TODO = 11;
    public static final int CREATE_DEADLINE = 12;
    public static final int CREATE_EVENT = 13;

    private int commandType;
    protected Storage storage;
    protected TaskList taskList;
    protected String userInput;

    public Command(int commandType) {
        this.commandType = commandType;
    }

    public Command(int commandType, Storage storage, TaskList taskList) {
        this.commandType = commandType;
        this.storage = storage;
        this.taskList = taskList;
    }

    public Command(int commandType, TaskList taskList, String userInput) {
        this.commandType = commandType;
        this.taskList = taskList;
        this.userInput = userInput;
    }

    public Command(int commandType, TaskList taskList) {
        this.commandType = commandType;
        this.taskList = taskList;
    }

    /**
     * Getter method for commandType.
     *
     * @return Type of command in integer form.
     */
    public int getCommandType() {
        return this.commandType;
    }

    /**
     * An abstract method implemented by the subclasses.
     *
     * @return String describing the outcome after executing the specific command.
     * @throws DukeException If an error occurs during the execution of command.
     */
    public abstract String execute() throws DukeException;
}
