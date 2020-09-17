package duke.misc;

import static duke.misc.Commands.BYE;
import static duke.misc.Commands.DEADLINE;
import static duke.misc.Commands.DELETE;
import static duke.misc.Commands.DONE;
import static duke.misc.Commands.EVENT;
import static duke.misc.Commands.FIND;
import static duke.misc.Commands.HELP;
import static duke.misc.Commands.LIST;
import static duke.misc.Commands.TODO;

import java.io.IOException;
import java.util.Arrays;

import duke.exception.InvalidDateException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidIndexException;
import duke.exception.InvalidTypeException;
import duke.task.DateTime;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parser class to handle user inputs.
 */
public class Parser {

    /**
     * Categorise user input into different types of tasks to add.
     *
     * @param input User input.
     * @return A task to be added by the TaskList.
     * @throws InvalidDescriptionException In case the task description is empty or has erroneous spacing.
     * @throws InvalidTypeException        In case the task type is not one of Event, Deadline, Todo.
     * @throws InvalidDateException        In case the date is not of the yyyy-MM-dd format.
     */
    public static Task handleInput(String input) throws InvalidDescriptionException,
            InvalidTypeException, InvalidDateException {
        String[] tags = extractTags(input);
        String cleanInput = removeHashTags(input);
        String type = cleanInput.split("\\s+")[0];
        switch (type) {
        case TODO:
            if (!cleanInput.matches(Todo.FORMAT)) {
                throw new InvalidDescriptionException();
            }
            return new Todo(cleanInput.substring(5), tags);
        case DEADLINE:
            if (!cleanInput.matches(Deadline.FORMAT)) {
                throw new InvalidDescriptionException();
            }
            String[] dl = cleanInput.split(" /by ");
            if (!DateTime.isValidFormat(dl[1])) {
                throw new InvalidDateException();
            }
            return new Deadline(dl[0].substring(9), dl[1], tags);
        case EVENT:
            if (!cleanInput.matches(Event.FORMAT)) {
                throw new InvalidDescriptionException();
            }
            String[] e = cleanInput.split(" /at ");
            if (!DateTime.isValidFormat(e[1])) {
                throw new InvalidDateException();
            }
            return new Event(e[0].substring(6), e[1], tags);
        default:
            throw new InvalidTypeException();
        }
    }

    private static String[] extractTags(String input) {
        return Arrays.stream(input.split("\\s+")).filter(str -> str.matches("#.+")).toArray(String[]::new);
    }

    private static String removeHashTags(String input) {
        return input.replace("#", "");
    }


    /**
     * Maps user input to actions the TaskList must carry out.
     *
     * @param input User input.
     * @param tl    TaskList to be used.
     */
    public static String allocate(String input, TaskList tl) {
        String[] arr = input.split(" ");
        int idx;
        String result = "";
        switch (arr[0]) {
        case BYE:
            result = Ui.GOODBYE;
            break;
        case LIST:
            result = tl.display();
            break;
        case HELP:
            result = Ui.HELP_MESSAGE;
            break;
        case FIND:
            try {
                result = tl.find(input);
            } catch (InvalidDescriptionException e) {
                result = e.toString();
            }
            break;
        case DONE:
            idx = Integer.parseInt(arr[1]) - 1;
            try {
                result = tl.completeTask(idx);
            } catch (InvalidIndexException e) {
                result = e.toString();
            }
            break;
        case DELETE:
            idx = Integer.parseInt(arr[1]) - 1;
            try {
                result = tl.deleteTask(idx);
            } catch (InvalidIndexException e) {
                result = e.toString();
            }
            break;
        default:
            try {
                Task toAdd = handleInput(input);
                result = tl.add(toAdd);
            } catch (InvalidDescriptionException e) {
                result = e.toString();
            } catch (InvalidTypeException e) {
                result = e.toString();
            } catch (InvalidDateException e) {
                result = e.toString();
            }
            break;
        }
        try {
            tl.saveData();
        } catch (IOException e) {
            result = e.toString();
        }
        return result;
    }
}
