package duke.command;

import duke.task.TaskList;
import duke.util.DateTimeParsing;
import duke.exception.InvalidCommandException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

// Handles all the logic behind any "due" command from the user
public class DueCommand {
    /**
     * Executes any "due" command issued by the user.
     * Returns the information of the tasks due on the date specified by the user.
     *
     * @param in String "due" command issued by user
     * @param taskList TaskList list that contains tasks added by the user
     * @return String response message to user including tasks due on the specified date
     * @throws InvalidCommandException If an invalid date format is provided
     */
    public static String execute(String in, TaskList taskList) throws InvalidCommandException {
        String dateStr = in.replaceFirst("due ", "");
        try {
            LocalDate date = DateTimeParsing.parseDate(dateStr);
            String formattedDate = DateTimeParsing.localDateToFormattedString(date);
            ArrayList<String> filteredTasks = new ArrayList<>();

            int len = taskList.size();
            for (int i = 1; i <= len; i++) {
                Task task = taskList.get(i - 1);
                if (task.isDueOn(date)) {
                    String output = i + "." + task.toString();
                    filteredTasks.add(output);
                }
            }

            if (filteredTasks.size() == 0) {
                return "There are no tasks due on " + formattedDate + "!";
            }

            String firstLine = "These are the tasks due on " + formattedDate + ":";

            return firstLine + "\n" + String.join("\n", filteredTasks);
        } catch (DateTimeParseException | NumberFormatException e) {
            String errMsg =
                    "Please key in a valid date format.\n" +
                    "due *yyyy-mm-dd*";
            throw new InvalidCommandException(errMsg);
        }
    }
}
