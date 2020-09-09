package duke.command;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.exception.InvalidCommandException;
import duke.task.Task;
import duke.task.TaskList;

public class RemindCommand {
    private static final String ERROR_INVALID_FORMAT = "Please input a valid number";

    public static String execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            int numberDays = Integer.parseInt(in.replaceFirst("remind ", ""));

            ArrayList<String> filteredTasks = new ArrayList<>();
            int len = taskList.size();
            Stream
                    .iterate(1 , i -> i <= len, i -> i + 1)
                    .forEach(i -> {
                        Task task = taskList.get(i - 1);
                        if (task.isDueInNDays(numberDays)) {
                            String output = i + "." + task.toString();
                            filteredTasks.add(output);
                        }
                    });

            boolean hasTaskToShow = filteredTasks.size() > 0;
            String firstLine = hasTaskToShow
                    ? "These are the tasks that are due within " + numberDays + " days:\n"
                    : "There are no tasks that are due within " + numberDays + " days!";

            String response = firstLine + String.join("\n", filteredTasks);
            return response;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ERROR_INVALID_FORMAT);
        }
    }
}
