/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    private String afterCommand;

    /**
     * Constructor for the deadline command
     * @param afterCommand details and time for deadline to be completed.
     */
    public DeadlineCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, DukeStorage storage) {
        // needs an after command
        if (afterCommand == null) {
            return ui.throwDukeException(new DukeException(
                    "Please do not leave the deadline description empty!"));
        }
        // first chunk is the deadline details, second chunk is by when
        String[] splittedDeadline = afterCommand.split("/");

        // teach the user the format for the deadline
        if (splittedDeadline.length == 1) {
            return ui.throwDukeException(new DukeException("Format of deadline recording: deadline keyword"
                + ", deadline instructions, forward slash, by keyword with a colon, specific date + time)"
                    + "\n e.g. deadline return book /by 2020-01-01 12:30"));
        }

        String details = splittedDeadline[0].trim();
        String by = splittedDeadline[1].split("by", 2)[1].trim();

        try {
            Task newDeadline = new Deadline(details, by);
            taskList.addTask(newDeadline);
            return ui.addTask(newDeadline, taskList.getTasksSize(), false);
        } catch (DukeException ex) {
            return ui.throwDukeException(ex);
        }
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
