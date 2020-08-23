import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class to handle commands to add a deadline to the bot's list
 * @author vanGoghhh
 */

public class DeadlineCommand extends Command {

    private String command;

    /**
     * Constructor for a deadlinecommand object
     * @param command Complete line of the deadline command entered by user
     */
    public DeadlineCommand(String command) {
        this.command = command;
    }

    /**
     * Method to execute entirely when a deadline command is entered by the user
     * @param tasks Tasklist containing all the tasks
     * @param dukeUI UI to print string responses by the bot
     * @throws InvalidTaskDescriptionException when an inaccurate task description is entered
     * @throws WrongDateFormatException when the date is not typed in the correct format
     */
    @Override
    protected void execute(TaskList tasks, UI dukeUI) throws InvalidTaskDescriptionException, WrongDateFormatException {
        try {
            String[] taskDetails = this.command.split("/");
            LocalDate taskDate = LocalDate.parse(taskDetails[1]);
            String[] taskDetailsWithoutDate = taskDetails[0].split(" ", 2);
            Deadline newDeadline = new Deadline(taskDetailsWithoutDate[1], taskDate);
            tasks.addTask(newDeadline);
            dukeUI.addTask(tasks, newDeadline);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException();
        }
    }

    /**
     * Method to tell bot whether to end the current session
     * @return false to not exit the system
     */
    protected boolean isExit() {
        return false;
    }
}