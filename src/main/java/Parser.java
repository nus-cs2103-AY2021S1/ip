import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a parser to make sense of user input and saved data.
 */
public class Parser {

    private TaskList list;

    protected Parser() {}

    protected Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Parses a String and creates a task based on the String.
     * Used to recreate tasks from previously saved data upon start up.
     *
     * @param line The line which is to be parsed.
     * @return Task object as described by the line.
     */
    protected Task parseFileData(String line) {
        assert isValidFileLine(line) : "line is not valid input";
        char taskType = line.charAt(3);
        boolean isDone = line.charAt(6) == '\u2713';
        Task task = null;
        if (taskType == 'T') {
            task = new ToDo(line.substring(9));
        } else if (taskType == 'E') {
            int index = line.indexOf(" (at: ");
            String time = line.substring(index + 6, line.length() - 1);
            task = new Event(line.substring(9, index), time);
        } else if (taskType == 'D') {
            int index = line.indexOf(" (by: ");
            String date = line.substring(index + 6, line.length() - 1);
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMM yyyy"));
            task = new Deadline(line.substring(9, index), localDate);
        }

        if (isDone) {
            task.markDone();
        }
        return task;
    }

    protected boolean isValidFileLine(String line) {
        boolean isNotEmpty = !line.isEmpty();
        ArrayList<Character> validTaskType = new ArrayList<>(Arrays.asList('T','E', 'D'));
        boolean isValidTaskType = validTaskType.contains(line.charAt(3));
        boolean isValidDoneType = line.charAt(6 ) == '\u2713'
                || line.charAt(6 ) == '\u2717';
        return isNotEmpty && isValidTaskType && isValidDoneType;
    }

    /**
     * Parses a String and performs actions based on the String.
     * Used to process user input and commands.
     *
     * @param input The user input which is to be parsed.
     */
    protected Ui parseUserInput(String input) throws DukeException, IOException {
        String uiMessage = "\n";

            if (input.equals("list")) {
                uiMessage += "Here are your tasks:\n" + list;
            } else if (input.startsWith("done")) {
                try {
                    int listIndex = Integer.parseInt(input.substring(5));
                    uiMessage += list.markTaskDone(listIndex);
                } catch (Exception error) {
                    throw new DukeException("OOPS!!! Please choose a valid task index to mark as done.\n");
                }
            } else if (input.startsWith("todo")) {
                if (input.equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                }
                ToDo toDoTask = new ToDo(input.substring(5));
                uiMessage += list.addTask(toDoTask);
            } else if (input.startsWith("deadline")) {
                if (input.equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
                }
                int index = input.indexOf("/by");
                String task = input.substring(9, index - 1);
                LocalDate deadline = LocalDate.parse(input.substring(index + 4),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Deadline deadlineTask = new Deadline(task, deadline);
                uiMessage += list.addTask(deadlineTask);
            } else if (input.startsWith("event")) {
                if (input.equals("event")) {
                    throw new DukeException("OOPS!!! The description of an event cannot be empty.\n");
                }
                int index = input.indexOf("/at");
                String task = input.substring(6, index - 1);
                String time = input.substring(index + 4);
                Event eventTask = new Event(task, time);
                uiMessage += list.addTask(eventTask);
            } else if (input.startsWith("delete")) {
                try {
                    int listIndex = Integer.parseInt(input.substring(7));
                    uiMessage += list.deleteTask(listIndex);
                } catch (Exception error) {
                    throw new DukeException("OOPS!!! Please choose a valid task index to delete.\n");
                }
            } else if (input.startsWith("find")) {
                if (input.equals("find")) {
                    throw new DukeException("OOPS!!! Please give me a keyword to search.\n");
                }
                uiMessage += list.findTask(input.substring(5));
            } else {
                //unrecognised command
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.\n");
            }

            return Ui.makeUi(uiMessage);
    }
}
