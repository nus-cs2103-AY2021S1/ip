import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a DeadlineCommand command which is a subclass of Command.
 */
public class DeadlineCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    /**
     * Creates a DeadlineCommand object.
     * It generates a message output showing user that a deadline task is created.
     *
     * @param userInput represents the user String input.
     * @param taskManager reference to the same TaskList that manages list of Tasks.
     */
    public DeadlineCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    /**
     * Returns the deadline creation message.
     *
     * @return a String of deadline creation message as Duke response.
     */
    public String getOutput() throws DukeException {
        int length = userInput.trim().length();
        int timePrefix = userInput.indexOf("/by");

        if (length == 9) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.NO_DESCRIPTION);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else if (timePrefix < 0 || timePrefix + 4 >= length) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_DEADLINE);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else {
            LocalDate date;
            String dateString = userInput.substring(timePrefix + 4, length);
            try {
                date = LocalDate.parse(dateString);
            } catch (DateTimeParseException e) {
                throw new DukeException(ExceptionCommand.INVALID_DATE_FORMAT);
            }

            Deadline newDeadline = new Deadline(userInput.substring(9, timePrefix), date);
            this.taskManager.store(newDeadline);
            this.appendDukeMessage("Got it. I've added this task:"
                    + "\n  " + newDeadline
                    + "\nNow you have " + this.taskManager.getTaskNum() + " task(s) in the list.");
        }

        return this.toString();
    }
}
