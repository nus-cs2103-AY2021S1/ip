/**
 * Class to run the delete command.
 */
public class DeleteCommand implements Command {

    protected final int TASK_NUMBER;

    /**
     * constructor
     */
    public DeleteCommand(int taskNumber) {
        this.TASK_NUMBER = taskNumber;
    }

    /**
     * Executes the delete command, causing Duke to delete a numbered task from the taskList,
     * provided that the number provided is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @throws MissingNumberFromCommandException If the delete command is missing a number.
     * @throws InvalidNumberFromCommandException If the delete command does not have a valid task number.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        String[] commandArgs = COMMAND.split(" ");

        if (commandArgs.length < 2) {
            throw new MissingNumberFromCommandException();
        } else {
            int taskLength = taskList.TASKS.size();
            int taskNumber = Integer.parseInt(commandArgs[1]) - 1;
            if (taskNumber < 0 || taskNumber > taskLength) {
                throw new InvalidNumberFromCommandException();
            } else {
                Task t = taskList.TASKS.get(taskNumber);
                taskList.deleteTask(taskNumber);
                storage.write(taskList.TASKS);
                ui.showLine();
                ui.deleteCommandSuccessMessage(taskNumber + 1, t);
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
