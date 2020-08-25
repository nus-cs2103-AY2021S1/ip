package duke;

/**
 * Encapsulates a Add ToDo Command.
 */
public class AddToDoCommand extends Command {
    /**
     * Instantiates an AddToDoCommand.
     * @param parsedCommand the parsed command
     */
    public AddToDoCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes command and write to storage.
     * @param tasks the tasklist containing tasks so far
     * @param ui ui to interact with user
     * @param storage storage to read and write to storage file
     * @throws DukeException if parsedCommand does not meet the requirements
     */
    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (parsedCommand.length != 2) {
            throw new DukeException("NANI??! Enter a description for your todo!\n");
        }

        String description = parsedCommand[1];
        Task taskToAdd = new ToDo(description);
        addTask(tasks, taskToAdd);
    }

    /**
     * Adds a task to the tasklist.
     * @param tasks the tasklist containing tasks so far
     * @param taskToAdd the task to add to tasklist
     */
    void addTask(TaskList<Task> tasks, Task taskToAdd) {
        tasks.add(taskToAdd);
        System.out.println("Hai! I have added this task to your list:\n"
                + taskToAdd);
        printToDoListSize(tasks);
    }

    /**
     * Prints list size.
     * @param tasks the tasklists
     */
    void printToDoListSize(TaskList<Task> tasks) {
        System.out.println("You now have "
                + tasks.size()
                + " tasks in your list. Gambatte!\n");
    }
}
