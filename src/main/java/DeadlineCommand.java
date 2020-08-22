import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String command) {
        super(command);
    }

    // Method to get the date of a deadline task
    protected LocalDate getDateForTask() throws InvalidTaskDescriptionException, WrongDateFormatException {
        try {
            String[] taskDetails = this.command.split("/");
            return LocalDate.parse(taskDetails[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException();
        }
    }

    // Method to obtain the description of a event or deadline task
    protected String getDescriptionForTask() throws InvalidTaskDescriptionException {
        try {
            String[] taskDetails = this.command.split("/");
            String[] taskDetailsWithoutDate = taskDetails[0].split(" ", 2);
            return taskDetailsWithoutDate[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskDescriptionException();
        }
    }

}