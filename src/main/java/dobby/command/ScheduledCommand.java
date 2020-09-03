package dobby.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dobby.DobbyException;
import dobby.TaskList;

public class ScheduledCommand implements Command {

    protected static final String USAGE = "scheduled dd/mm/yyyy";

    @Override
    public String parseInput(TaskList tasks, String text) throws DobbyException {
        String message;
        try {
            String dt = text.substring(text.indexOf(' ') + 1);
            String day = dt.substring(0, dt.indexOf('/'));
            String month = dt.substring(dt.indexOf('/') + 1, dt.lastIndexOf('/'));
            String year = dt.substring(dt.lastIndexOf('/') + 1);
            LocalDate parsedDate = LocalDate.parse(year + "-" + month + "-" + day);

            message = tasks.getScheduledTasks(parsedDate);
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DobbyException("Incorrect usage of command.\n"
                    + "The format of the date in incorrect. Please try again.\n  "
                    + USAGE);
        }
        return message;
    }
}
