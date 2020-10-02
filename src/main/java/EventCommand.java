import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class to handle event commands entered by the user.
 * @author vanGoghhh
 */

public class EventCommand extends Command{

    private String command;

    /**
     * Constructor for the event command object.
     * @param command Complete line of command entered by the user.
     */
    public EventCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute entirely when a done command is entered by the user.
     * @param tasks Tasklist containing all the tasks.
     * @param dukeUI UI to print string responses by the bot.
     * @return the String response for this command.
     * @throws InvalidTaskDescriptionException when an invalid task description is entered.
     * @throws WrongDateFormatException when an invalid date format is entered.
     */
    @Override
    protected String execute(TaskList tasks, UI dukeUI) throws InvalidTaskDescriptionException, WrongDateFormatException {
        try {
            if (this.command.contains("//")) {
                throw new InvalidTaskDescriptionException();
            }
            String[] taskDetails = this.command.split("/");
            LocalDate taskDate = LocalDate.parse(taskDetails[1]);
            String[] taskDetailsWithoutDate = taskDetails[0].split(" ", 2);
            Event newEvent = new Event(taskDetailsWithoutDate[1], taskDate);
            tasks.addTask(newEvent);
            String dukeResponse = dukeUI.addTask(tasks, newEvent);
            return dukeResponse;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException();
        }
    }
}
