package duke;

/**
 * Encapsulates a Add ToDo Command.
 */
public class AddToDoCommand extends Command {
    /**
     * Instantiates an AddToDoCommand.
     *
     * @param parsedCommand the parsed command
     */
    public AddToDoCommand(String[] parsedCommand) {
        super(parsedCommand);
    }

    /**
     * Executes command and write to storage.
     *
     * @param tasks the tasklist containing tasks so far
     * @param ui ui to interact with user
     * @param storage storage to read and write to storage file
     * @throws DukeException if parsedCommand does not meet the requirements
     */
    @Override
    void execute(TaskList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (getParsedCommand().length != 2) {
            throw new DukeException("NANI??! Enter a description for your todo!\n");
        }

        String description = getParsedCommand()[1];
        if (description.isBlank()) {
            throw new DukeException("NANI??! Don't just type spaces as your description!\n");
        }

        assert !description.isEmpty()
                : "the todo description should not be empty"; // assert that the todo description is not empty
        assert !description.isBlank()
                : "the todo description should not be blank"; // assert that the todo description is not blank

        Task taskToAdd = new ToDo(description);
        addTask(tasks, taskToAdd, ui);
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param tasks the tasklist containing tasks so far
     * @param taskToAdd the task to add to tasklist
     */
    void addTask(TaskList<Task> tasks, Task taskToAdd, Ui ui) {
        tasks.add(taskToAdd);
        ui.appendMessage("Hai! I have added this task to your list:\n"
                + taskToAdd + "\n");
        printToDoListSize(tasks, ui);
    }

    /**
     * Prints list size.
     *
     * @param tasks the tasklists
     */
    void printToDoListSize(TaskList<Task> tasks, Ui ui) {
        ui.appendMessage("You now have "
                + tasks.size()
                + " tasks in your list. Gambatte!\n");
    }
}
