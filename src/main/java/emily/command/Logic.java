package emily.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import emily.exception.DukeException;
import emily.storage.TaskList;
import emily.task.Task;


/**
 * Receives commands from user.
 * Ensures commands are valid and modifies list of tasks accordingly.
 */
public class Logic {
    private final Parser p;

    Logic() {
        this.p = new Parser();
    }

    /**
     * Valid commands
     */
    enum Command {
        LIST, DELETE, TASK, DONE, FIND, VIEW, EDIT
    }

    /**
     * Read a line of input from the user and modify the task list accordingly
     *
     * @param input command from the user
     * @param ls    to be modified
     * @throws DukeException when there is invalid command
     */
    public ArrayList<String> readsLine(String input, TaskList ls) throws DukeException {

        ArrayList<String> outputLines = new ArrayList<>();
        Task current;
        int index;
        int counter;

        Command instruct = checksValidInput(input, ls.getTaskArrayList().size());
        switch (instruct) {
        case LIST: //list out the task
            int num = 1;
            for (Task c : ls.getTaskArrayList()) {
                String item = "    " + num + ". " + c;
                num++;
                outputLines.add(item);
            }
            break;
        case FIND: //find all tasks with matching keyword
            String keyword = input.substring(5);
            ArrayList<Task> lsSameKeyword = ls.findSameKeyword(keyword);

            outputLines.add("Here are the matching tasks in your list");
            counter = 1;
            for (Task t : lsSameKeyword) {
                outputLines.add(counter + ". " + t);
                counter++;
            }
            break;
        case VIEW: //find all task with given date in format yyyy-mm-dd
            String date = input.substring(5);
            ArrayList<Task> lsSameDate = ls.findSameDate(date);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            String formattedDate = LocalDate.parse(date).format(formatter);


            outputLines.add("Here are the tasks on " + formattedDate + " in your list");
            counter = 1;
            for (Task t : lsSameDate) {
                outputLines.add("- " + t);
                counter++;
            }
            break;
        case DONE:
            index = Character.getNumericValue(input.charAt(5)) - 1;
            current = ls.getTaskArrayList().get(index);
            current.setHasFinished(true);

            outputLines.add("Nice work, I have marked this task as done: "
                    + current);
            break;
        case TASK: //call parser to convert to task
            current = p.parse(input);
            ls.addTask(current);

            outputLines.add("Got it! I have added this task: " + current);
            outputLines.add("Now you have " + (ls.getTaskArrayList().size())
                    + " tasks in the list");
            break;
        case DELETE:
            index = Character.getNumericValue(input.charAt(7)) - 1;
            current = ls.getTaskArrayList().get(index);
            ls.deleteTask(index);

            outputLines.add("I have deleted this task for you: " + current);
            outputLines.add("You have " + ls.getTaskArrayList().size() + " tasks in your list now");
            break;
        case EDIT:
            index = Character.getNumericValue(input.charAt(5)) - 1;
            current = ls.getTaskArrayList().get(index);
            String newDescription = input.substring(7);
            current.modifyDescription(newDescription);

            outputLines.add("I have edited the description for you: " + current);

            break;
        default: //failure of the checkValidInput to check commands
            assert false : "checksValidInput has failed";
            throw new DukeException("Command is not recognised");
        }
        return outputLines;
    }

    /**
     * Checks if the command given is valid and package the information
     * into a Command type
     *
     * @param input               user command
     * @param currentTasklistSize the number of tasks
     * @return a Command type with information extracted from the task
     * @throws DukeException thrown when the input is invalid
     */
    public Command checksValidInput(String input, int currentTasklistSize) throws DukeException {
        Command c;
        String shortened = input.trim();

        if (input.isEmpty() || shortened.isEmpty()) {
            throw new DukeException("Empty Input");
        } else if (input.equals("list")) {
            c = Command.LIST;
        } else if (input.contains("view")) { //users will key in specific date
            if (shortened.equals("view")) {
                throw new DukeException("Missing date");
            }
            c = Command.VIEW;
        } else if (input.contains("delete")) {
            if (shortened.equals("delete")) {
                throw new DukeException("Empty Index");
            }
            int index = Character.getNumericValue(input.charAt(7));
            if (index > currentTasklistSize) {
                throw new DukeException("Index of " + index + " is invalid");
            }
            c = Command.DELETE;
        } else if (input.contains("done")) {
            if (shortened.equals("done")) {
                throw new DukeException("Index cannot be found");
            }
            int index = Character.getNumericValue(input.charAt(5));
            if (index > currentTasklistSize || index <= 0) {
                throw new DukeException("Index is invalid");
            }
            c = Command.DONE;
        } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            if (shortened.equals("todo") || shortened.equals("deadline") || shortened.equals("event")
                    || shortened.equals("deadline/by") || shortened.equals("event/at")) {
                throw new DukeException("The description cannot be empty");
            }
            c = Command.TASK;
        } else if (input.contains("find")) {
            if (shortened.equals("find")) {
                throw new DukeException("Missing keyword");
            }
            c = Command.FIND;
        } else if (input.contains("edit")) {
            if (shortened.equals("edit")) {
                throw new DukeException("Index cannot be found");
            } else if (shortened.length()<7) {
                throw new DukeException("Please add description");
            }
            c = Command.EDIT;
        } else { //Unrecognised command given by the user
            throw new DukeException("I do not understand the command x.x");
        }
        return c;
    }
}
