import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an EventCommand command which is a subclass of Command.
 */
public class EventCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    /**
     * Creates an EventCommand object.
     * It generates a message output showing user that an event task is created.
     *
     * @param userInput represents the user String input.
     * @param taskManager reference to the same TaskList that manages list of Tasks.
     */
    public EventCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

    /**
     * Returns the event creation message.
     *
     * @return a String of event creation message as Duke response.
     */
    public String getOutput() throws DukeException {
        int length = userInput.trim().length();
        int timePrefix = userInput.indexOf("/at");

        if (length == 5) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.NO_DESCRIPTION);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else if (timePrefix < 0 || timePrefix + 4 >= length) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_EVENT);
            this.appendDukeMessage(exceptionCommand.getOutput());

        } else {
            LocalDate date;
            String dateString = userInput.substring(timePrefix + 4, length);
            try {
                date = LocalDate.parse(dateString);

            } catch (DateTimeParseException e) {
                throw new DukeException(ExceptionCommand.INVALID_DATE_FORMAT);
            }

            Event newEvent = new Event(userInput.substring(6, timePrefix), date);
            this.taskManager.store(newEvent);
            this.appendDukeMessage("Got it. I've added this task:"
                    + "\n  " + newEvent
                    + "\nNow you have " + this.taskManager.getTaskNum() + " task(s) in the list.");
        }

        return this.toString();
    }
}
