package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Stream;

import duke.exception.InvalidCommandException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.DukeDateTime;

// Handles all the logic behind any "due" command from the user.
public class DueCommand extends Command {
    private static final String ERROR_INVALID_FORMAT = "Please key in a valid date format.\n" + "due *yyyy-mm-dd*";

    /**
     * Executes any "due" command issued by the user.
     * Returns the information of the tasks due on the date specified by the user.
     *
     * @param in String "due" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user including tasks due on the specified date.
     * @throws InvalidCommandException If an invalid date format is provided.
     */
    public static Response execute(String in, TaskList taskList) throws InvalidCommandException {
        String dateStr = in.replaceFirst("due ", "");
        try {
            LocalDate date = DukeDateTime.parseDate(dateStr);
            String formattedDate = DukeDateTime.localDateToFormattedString(date);

            ArrayList<String> filteredTasks = new ArrayList<>();
            int len = taskList.size();
            Stream
                    .iterate(1 , i -> i <= len, i -> i + 1)
                    .forEach(i -> {
                        Task task = taskList.get(i - 1);
                        if (task.isDueOn(date)) {
                            String output = i + "." + task.toString();
                            filteredTasks.add(output);
                        }
                    });

            boolean hasTaskToShow = filteredTasks.size() > 0;
            String firstLine = hasTaskToShow
                    ? "These are the tasks due on " + formattedDate + ":\n"
                    : "There are no tasks due on " + formattedDate + "!";

            String response = firstLine + String.join("\n", filteredTasks);
            return new NormalResponse(response);
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new InvalidCommandException(ERROR_INVALID_FORMAT);
        }
    }
}
