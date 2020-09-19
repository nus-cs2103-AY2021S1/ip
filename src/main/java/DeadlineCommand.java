/**
 * Class to run the deadline command.
 */
public class DeadlineCommand implements Command {

    protected final String COMMAND;

    /**
     * constructor
     */
    public DeadlineCommand(String command) {
        this.COMMAND = command;
    }

    /**
     * Executes the deadline command, causing add a task of type Deadline to the taskList,
     * provided that the command input is valid.
     *
     * @param taskList Used by Duke to keep track of tasks.
     * @param ui Responsible for printing to console after execution.
     * @param storage Stores tasks in a text format.
     * @throws MissingDescriptionException If the deadline command is missing a description.
     * @throws MissingTagException If the deadline command is missing a "/by" tag.
     * @throws MissingDateTimeException If the deadline command is missing a valid Date and Time.
     * @return
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        String[] commandArgs = COMMAND.split(" ");

        if (commandArgs.length == 1 || commandArgs[1].equals("/by")) {
            throw new MissingDescriptionException();
        } else if (commandArgs.length == 2 || !commandArgs[2].equals("/by")) {
            throw new MissingTagException();
        } else if (commandArgs.length != 5) {
            throw new MissingDateTimeException();
        } else {
            String subCommand = COMMAND;
            if (COMMAND.split(" ")[0].length() == 8) {
                subCommand = subCommand.substring(8).trim();
            } else {
                subCommand = subCommand.substring(1).trim();
            }
            String[] subCommandArgs = subCommand.split("/by");
            Deadline d = new Deadline(subCommandArgs[0], subCommandArgs[1], false);
            taskList.addTask(d);
            storage.write(taskList.TASKS);
            String result = ui.showLine() + "\n" + ui.createDeadlineSuccessMessage(d, taskList.TASKS.size())
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
