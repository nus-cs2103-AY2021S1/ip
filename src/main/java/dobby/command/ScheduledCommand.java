package dobby.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dobby.DobbyException;
import dobby.TaskList;

public class ScheduledCommand implements Command {

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message;
        assert text.startsWith("scheduled") : "Scheduled command must start with scheduled.";
        try {
            String date = text.substring(text.indexOf(' ') + 1);
            String day = date.substring(0, date.indexOf('/'));
            String month = date.substring(date.indexOf('/') + 1, date.lastIndexOf('/'));
            String year = date.substring(date.lastIndexOf('/') + 1);
            LocalDate parsedDate = LocalDate.parse(year + "-" + month + "-" + day);

            message = tasks.getScheduledTasks(parsedDate);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\n"
                    + "The format of the date in incorrect. Please try again.");
        }
        assert message != null : "Return message to user cannot be empty";
        return message;
    }
}
