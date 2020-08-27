/**
 * Class to run the done command.
 */
public class DoneCommand implements Command {

    protected final String COMMAND;

    /**
     * constructor
     */
    public DoneCommand(String command) {
        this.COMMAND = command;
    }

    /**
     * Executes the done command, causing Duke to mark the numbered task as done,
     * provided that the number provided with the done command is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @throws MissingNumberFromCommandException If the done command is missing a number.
     * @throws InvalidNumberFromDoneCommandException If the number provided with the done command is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String[] commandArgs = COMMAND.split(" ");

        if (commandArgs.length < 2) {
            throw new MissingNumberFromCommandException();
        } else {
            int taskLength = taskList.TASKS.size();
            int taskNumber = Integer.parseInt(commandArgs[1]) - 1;
            if (taskNumber < 0 || taskNumber > taskLength) {
                throw new InvalidNumberFromDoneCommandException();
            } else {
                Task t = taskList.TASKS.get(taskNumber);
                taskList.done(taskNumber);
                storage.write(taskList.TASKS);
                ui.showLine();
                ui.doneCommandSuccessMessage(taskNumber + 1, t);
                ui.showLine();
            }
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
