package duke;

/**
 * Encapsulates a Delete Command.
 */
public class DeleteCommand extends Command {
    /**
     * Instantiates a delete command.
     *
     * @param parsedCommand command that has been parsed
     */
    public DeleteCommand(String[] parsedCommand) {
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
        try {
            // if length is not 2, nothing was passed in after 'done'
            if (getParsedCommand().length != 2) {
                throw new DukeException("Which task do you want to delete? Please key in the task number!\n");
            }
            // check if taskNumber is a number
            int taskNumber = Integer.parseInt(getParsedCommand()[1]);
            // check if taskNumber is valid
            if (taskNumber <= 0 || taskNumber > tasks.size()) {
                throw new DukeException("Sorry, no such task to delete!\n");
            }

            Task currentTask = tasks.get(taskNumber - 1);
            ui.appendMessage("Hai! This task has been deleted!");
            tasks.remove(currentTask);
            ui.appendMessage(currentTask.toString());
            printToDoListSize(tasks, ui);

        } catch (NumberFormatException e) {
            throw new DukeException("'" + getParsedCommand()[1] + "'" + " is not an integer!\n");
        }
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
