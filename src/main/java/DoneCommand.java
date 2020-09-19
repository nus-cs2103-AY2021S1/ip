/**
 * Class to run the done command.
 */
public class DoneCommand implements Command {

    protected final int TASK_NUMBER;

    /**
     * constructor
     */
    public DoneCommand(int taskNumber) {
        this.TASK_NUMBER = taskNumber;
    }

    /**
     * Executes the done command, causing Duke to mark the numbered task as done,
     * provided that the number provided with the done command is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @throws MissingNumberFromCommandException If the done command is missing a number.
     * @throws InvalidNumberFromCommandException If the number provided with the done command is invalid.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int taskLength = taskList.TASKS.size();
        if (TASK_NUMBER < 0 || TASK_NUMBER >= taskLength) {
            throw new InvalidNumberFromCommandException();
        } else {
            Task t = taskList.TASKS.get(TASK_NUMBER);
            taskList.markAsDone(TASK_NUMBER);
            storage.write(taskList.TASKS);
            String result = ui.showLine() + "\n" + ui.doneCommandSuccessMessage(TASK_NUMBER + 1, t)
                    + ui.showLine();
            return result;
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
