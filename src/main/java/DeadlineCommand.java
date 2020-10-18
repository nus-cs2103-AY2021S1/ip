import java.io.IOException;

/**
 * A Deadline command to add a Deadline Task to the TaskList
 */
class DeadlineCommand extends Command {
    private String task;
    private String deadline;

    /**
     * Constructor for DeadlineCommand
     * @param toParse Partial user input to be parsed
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     * @throws InvalidDeadlineException Exception thrown when an empty or incorrectly formatted date is provided
     */
    DeadlineCommand(String toParse, TaskList tasks, Ui ui, Storage storage) throws InvalidDeadlineException {
        super(tasks, ui, storage);
        String[] split = toParse.split(" /by ");
        if (split.length < 2) {
            throw new InvalidDeadlineException("no deadline");
        }
        this.task = split[0];
        this.deadline = split[1];
        if (!isValidDate(this.deadline)) {
            throw new InvalidDeadlineException(this.deadline);
        }
    }

    /**
     * Executes the DeadlineCommand
     * @return Returns String confirmation of added task
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        Task task = tasks.addDeadline(this.task, this.deadline);
        storage.saveFile(tasks);
        return ui.printf("Got it. I've added this task:\n" + task + "\n" + tasks.taskCount());
    }

    /**
     * Verifies date matches yyyy-mm-dd format
     * @param date String of date to be checked
     * @return True if date is valid
     */
    public boolean isValidDate(String date) {
        String validation = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        return date.matches(validation);
    }
}
