package duke;

/**
 * Encapsulates a Add Deadline Command.
 */
public class AddEventCommand extends Command {

    /**
     * Instantiates an AddEventCommand.
     * @param parsedCommand the parsed command
     */
    public AddEventCommand(String[] parsedCommand) {
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
        // if length is not 2, nothing was passed in after 'makeEvent'
        if (parsedCommand.length != 2) {
            throw new DukeException("NANI??! Enter a description for your event!\n");
        }

        // if event is lacking a /at keyword
        String description = parsedCommand[1];
        if (description.indexOf("/at") < 0) {
            throw new DukeException("Please enter a valid event! Remember to add '/at'\n");
        }

        String[] descriptionArray = description.split("/at");
        if (descriptionArray.length != 2) {
            throw new DukeException("NANI??! Enter your event name & an event timing!\n");
        }

        String eventName = descriptionArray[0];
        String eventTiming = descriptionArray[1];

        Task taskToAdd = new Event(eventName, eventTiming);
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
