import java.io.IOException;

/**
 * Parses commands as well as provide differnet type of subclasses
 * to handle different types of commands
 */
abstract class Command {
    protected boolean exit;

    protected final TaskList tasks;
    protected final Ui ui;
    protected final Storage storage;

    Command(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Takes a String command and parses it to return appropriate
     * Command object
     * @param command String command
     * @return appropriate Command object
     * @throws InvalidInputException if String command not available
     */
    static Command parse(String command, TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, EmptyTodoException, InvalidDeadlineException {
        if (command.equals("bye")) {
            return new ExitCommand(tasks, ui, storage);
        } else if (command.equals("list")) {
            return new ListCommand(tasks, ui, storage);
        } else if (command.contains("done ")) {
            int task = Integer.parseInt(command.replace("done ", "")) - 1;
            return new DoneCommand(task, tasks, ui, storage);
        } else if (command.contains("delete ")) {
            int task = Integer.parseInt(command.replace("delete ", "")) - 1;
            return new DeleteCommand(task, tasks, ui, storage);
        } else if (command.contains("find ")) {
            String key = command.replace("find ", "");
            return new FindCommand(key, tasks, ui, storage);
        } else if (command.equals("load")) {
            return new LoadFileCommand(tasks, ui, storage);
        } else {
            //actual entry
            try {
                String postFix = command.split(" ", 2)[1];
                String preFix = command.split(" ", 2)[0];
                switch (preFix) {
                case "todo":
                    return new TodoCommand(postFix, tasks, ui, storage);
                case "deadline":
                    return new DeadlineCommand(postFix, tasks, ui, storage);
                case "event":
                    return new EventCommand(postFix, tasks, ui, storage);
                default:
                    throw new InvalidInputException();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidInputException();
            }
        }
    }

    /**
     * Executes the current command
     * @throws IOException may be thrown while saving to storage
     */
    public abstract String execute() throws IOException;

    /**
     * Returns if the command is supposed to cause an exit from UI
     * @return True/False to exit
     */
    public boolean isExit() {
        return exit;
    }
}

