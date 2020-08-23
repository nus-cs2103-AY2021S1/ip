import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String command;

    public DeadlineCommand(String command) {

        this.command = command;
    }

    @Override
    protected void execute(TaskList tasks, UI dukeUI)
            throws InvalidTaskDescriptionException, WrongDateFormatException {
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

    protected boolean isExit() {
        return false;
    }
}