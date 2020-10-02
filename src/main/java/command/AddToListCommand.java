package command;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import storage.CommandStorage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * An AddToListCommand object adds a task to the existing list.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-09-01
 */
public class AddToListCommand extends Command {
    /**
     * Processes the input date by user.
     *
     * @param inputDeadline Date related to task.Task.
     * @return LocalDateTime object representing the date related to task.Task.
     * @throws DukeException If input date format is invalid.
     */
    public LocalDateTime processDate(String inputDeadline) throws DukeException {
        assert inputDeadline.length() > 0 : "Date and time cannot be empty";

        String dateFormat;
        if (inputDeadline.contains("/")) {
            dateFormat = "/";
        } else if (inputDeadline.contains(".")) {
            dateFormat = "\\.";
        } else if (inputDeadline.contains("-")) {
            dateFormat = "-";
        } else {
            throw new DukeException("Please input valid date format!");
        }
        String[] inputData = inputDeadline.split(dateFormat);
        if (inputData.length != 3 || inputData[2].length() != 9) {
            throw new DukeException("Please input valid date format!");
        }
        int date = Integer.parseInt(inputData[0]);
        int month = Integer.parseInt(inputData[1]);
        int year = Integer.parseInt(inputData[2].substring(0, 4));
        int hour = Integer.parseInt(inputData[2].substring(5, 7));
        int min = Integer.parseInt(inputData[2].substring(7));
        try {
            return LocalDateTime.of(year, month, date, hour, min);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date or time inputs!");
        }
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param inputMsg User's input message to the chat bot.
     * @param currList Current list of tasks.
     * @param ui Ui object relevant to the chat bot.
     * @param commandStorage CommandStorage object to store user commands.
     * @return String message indicating task has been added.
     * @throws DukeException If user does not give a task description.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui, CommandStorage commandStorage)
            throws DukeException {
        assert currList != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";

        // user specified action, to identify type of action
        String actionType = inputMsg.split(" ")[0];
        Task newTask;
        int numOfWords = inputMsg.split(" ").length;
        switch (actionType) {
        case "todo": {
            if (numOfWords <= 1) {
                throw new DukeException("Description of task cannot be empty!");
            }
            String taskName = inputMsg.substring(5);
            newTask = new Todo(taskName, false);
            break;
        }
        case "deadline": {
            if (numOfWords <= 1) {
                throw new DukeException("Description of task cannot be empty!");
            }

            String[] splitInput = inputMsg.split("/by");
            if (splitInput.length <= 1) {
                throw new DukeException("Please use /by to specify deadline!");
            }
            String task = splitInput[0];
            if (task.length() <= 9) {
                throw new DukeException("Task name cannot be empty!");
            }

            try {
                String taskName = task.substring(9, task.length() - 1);
                String inputDeadline = splitInput[1].substring(1);
                LocalDateTime deadline = processDate(inputDeadline);
                newTask = new Deadline(taskName, false, deadline);
                break;
            } catch (Exception e) {
                throw new DukeException("Please enter event details!");
            }
        }
        case "event": {
            if (numOfWords <= 1) {
                throw new DukeException("Description of task cannot be empty!");
            }

            String[] splitInput = inputMsg.split("/at");
            if (splitInput.length <= 1) {
                throw new DukeException("Please use /at to specify deadline!");
            }
            String task = splitInput[0];
            if (task.length() <= 6) {
                throw new DukeException("Task name cannot be empty!");
            }

            try {
                String taskName = task.substring(6, task.length() - 1);
                String inputDeadline = splitInput[1].substring(1);
                LocalDateTime deadline = processDate(inputDeadline);
                newTask = new Event(taskName, false, deadline);
                break;
            } catch (Exception e) {
                throw new DukeException("Please enter event details!");
            }
        }
        default:
            // when user keys in unregistered action
            throw new DukeException("Specified action is not recognised.");
        }
        return ui.addTask(newTask, currList);
    }
}
