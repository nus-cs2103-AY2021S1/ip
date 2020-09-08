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
            String date = text.substring(text.indexOf(' ') + 1);
            String day = date.substring(0, date.indexOf('/'));
            String month = date.substring(date.indexOf('/') + 1, date.lastIndexOf('/'));
            String year = date.substring(date.lastIndexOf('/') + 1);
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
