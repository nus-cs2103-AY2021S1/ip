package duke.command;

import java.util.ArrayList;
import java.util.stream.Stream;

import duke.exception.InvalidCommandException;
import duke.response.NormalResponse;
import duke.response.Response;
import duke.task.Task;
import duke.task.TaskList;

// Handles all the logic behind any "remind" command from the user.
public class RemindCommand {
    private static final String ERROR_INVALID_FORMAT = "Please input a valid number";

    /**
     * Executes any "due" command issued by the user.
     * Returns the information of the tasks that are due within the time period specified.
     *
     * @param in String "remind" command issued by user.
     * @param taskList TaskList list that contains tasks added by the user.
     * @return Response response message to user including tasks that are due within the specified time period.
     * @throws InvalidCommandException If an invalid number of days is provided.
     */
    public static Response execute(String in, TaskList taskList) throws InvalidCommandException {
        try {
            int numberDays = Integer.parseInt(in.replaceFirst("remind ", ""));
            if (numberDays < 0) {
                throw new NumberFormatException();
            }
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
            return new NormalResponse(response);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException(ERROR_INVALID_FORMAT);
        }
    }
}
