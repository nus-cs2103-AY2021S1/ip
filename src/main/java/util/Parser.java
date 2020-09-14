package util;

import java.time.DateTimeException;
import java.time.LocalDate;

import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Processor and executor for user inputs.
 */
public class Parser {

    /**
     * Runs the given input string if valid.
     * Prints any output or error messages.
     *
     * @param input String input read from user.
     * @param taskList List of task stored by driver.
     * @param ui UI printer containing standard print methods.
     * @return String output corresponding to input.
     * @throws DukeException If input is invalid.
     */
    public static String parse(String input, TaskList taskList, Ui ui) throws DukeException {

        assert taskList != null && ui != null;

        String output = "";

        if (input.equals("list")) {

            output += taskList.printList();

        } else if (input.startsWith("done ")) {

            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]) - 1;
            Task task = taskList.completeTask(index);
            output += "Nice! I've marked this task as done:\n";
            output += task + "\n";

        } else if (input.startsWith("delete ")) {

            String[] arr = input.split(" ");
            int index = Integer.parseInt(arr[1]) - 1;
            Task task = taskList.removeTask(index);
            output += "Noted. I've removed this task:\n";
            output += task + "\n";
            output += "Now you have " + taskList.getSize() + " tasks in the list.\n";

        } else if (input.startsWith("find ")) {
            output = find(input, output, taskList, ui);
        } else {
            output = addToList(input, output, taskList, ui);
        }

        output += ui.getLine();

        return output;
    }

    private static String find(String input, String output, TaskList taskList, Ui ui) throws DukeException {

        if (input.contains("/on ")) {

            int index = input.indexOf("/on ");

            String dateString = input.substring(index + 4);

            try {
                LocalDate date = LocalDate.parse(dateString);

                output += taskList.printList(task -> (task instanceof Event
                        && ((Event) task).getDate().equals(date))
                        || (task instanceof Deadline && ((Deadline) task).getDate().equals(date)));
            } catch (DateTimeException e) {
                throw new DukeException("Enter date in the following format: YYYY-MM-DD");
            }

        } else if (input.length() > 5) {

            String query = input.substring(5);
            output += taskList.printList(task -> task.contains(query));

        } else {
            throw new DukeException("Enter a valid find command");
        }

        return output;
    }

    private static String addToList(String input, String output, TaskList taskList, Ui ui) throws DukeException {

        Task task = null;

        if (input.startsWith("todo ") && input.length() > 5) {

            task = new ToDo(input.substring(5));

        } else if (input.startsWith("deadline ")) {

            int deadlineIndex = input.indexOf("/by ");

            if (deadlineIndex != -1 && input.length() > deadlineIndex + 4) {

                String datetime = input.substring(deadlineIndex + 4);

                try {
                    if (datetime.contains(" ")) {
                        String[] datetimeArr = datetime.split(" ");
                        task = new Deadline(
                                input.substring(9, deadlineIndex - 1),
                                datetimeArr[0],
                                datetimeArr[1]);
                    } else {
                        task = new Deadline(
                                input.substring(9, deadlineIndex - 1),
                                datetime,
                                "");
                    }
                } catch (DateTimeException e) {
                    throw new DukeException("Enter date in the following format: YYYY-MM-DD HH:mm(optional) "
                            + "(e.g. 2020-06-18 or 2020-07-20 18:00)");
                }
            }

        } else if (input.startsWith("event ")) {

            int timeIndex = input.indexOf("/at ");

            if (timeIndex != -1 && input.length() > timeIndex + 4) {

                String datetime = input.substring(timeIndex + 4);

                try {
                    if (datetime.contains(" ")) {
                        String[] datetimeArr = datetime.split(" ");
                        task = new Event(
                                input.substring(6, timeIndex - 1),
                                datetimeArr[0],
                                datetimeArr[1]);
                    } else {
                        task = new Event(
                                input.substring(6, timeIndex - 1),
                                datetime,
                                "");
                    }
                } catch (DateTimeException e) {
                    throw new DukeException("Enter date in the following format: YYYY-MM-DD HH:mm(optional) "
                            + "(e.g. 2020-06-18 or 2020-07-20 18:00)");
                }
            }
        }

        if (task != null) {
            int index = taskList.addTask(task);

            if (index == -1) {
                output += "Got it. I've added this task:\n";
                output += task + "\n";
                output += "Now you have " + taskList.getSize() + " tasks in the list.\n";
            } else {
                output += "You already have the same task stored:\n";
                output += taskList.printTask(index);
            }
        } else {
            throw new DukeException("Invalid command, please try again");
        }

        return output;
    }
}
