/**
 * Class to run the event command.
 */
public class EventCommand implements Command {

    protected final String COMMAND;

    /**
     * constructor
     */
    public EventCommand(String command) {
        this.COMMAND = command;
    }

    /**
     * Executes the event command, causing add a task of type Event to the taskList,
     * provided that the command input is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @throws MissingDescriptionException If the event command is missing a description.
     * @throws MissingTagException If the event command is missing a "/at" tag.
     * @throws MissingDateTimeException If the event command is missing a valid Date and Time.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        String[] commandArgs = COMMAND.split(" ");

        if (commandArgs.length == 1 || commandArgs[1].equals("/at")) {
            throw new MissingDescriptionException();
        } else if (commandArgs.length == 2 || !commandArgs[2].equals("/at")) {
            throw new MissingTagException();
        } else if (commandArgs.length != 5) {
            throw new MissingDateTimeException();
        } else {
            String subCommand = COMMAND.substring(9);
            String[] subCommandArgs = subCommand.split("/at");
            Event e = new Event(subCommandArgs[0], subCommandArgs[1], false);
            taskList.addTask(e);
            storage.write(taskList.TASKS);
            ui.showLine();
            ui.createEventSuccessMessage(e, taskList.TASKS.size());
            ui.showLine();
        }
    }

    /**
     * Returns true if a bye command is called.
     * Returns False otherwise.
     *
     * @return boolean indicating whether Duke is to stop running.
     */
    public boolean isExit() {
        return false;
    }
}
