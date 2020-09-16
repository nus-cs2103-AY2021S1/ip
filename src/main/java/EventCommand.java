import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String userInput;
    private TaskList taskManager;

    public EventCommand(String userInput, TaskList taskManager) {
        super("");
        this.userInput = userInput;
        this.taskManager = taskManager;
    }

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
