import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

public class DueCommand {
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
