package src.main.java.duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import src.main.java.duke.data.Duke;
import src.main.java.duke.data.exception.IllegalValueException;
import src.main.java.duke.data.task.Deadline;
import src.main.java.duke.data.task.Event;
import src.main.java.duke.data.task.Task;
import src.main.java.duke.data.task.TaskList;
import src.main.java.duke.data.task.Todo;
import src.main.java.duke.storage.StorageFile.StorageOperationException;


/**
 * Represents a decoder that decodes all the {@code Task} in the {@code toSave} into a list of decodable.
 */
public class DukeDecoder {

    public static final String TODO_REGEX = "(T)+\\s+([|])+\\s+([10])+\\s+([|])+\\s+\\w+.+";
    public static final String DEADLINE_REGEX = "([DE])+\\s+([|])+\\s+([10])+\\s+([|])+\\s+\\w+.+\\s+([|])+\\s+\\w+.+";

    /**
     * Decodes {@code encodedDuke} into an {@code Duke} containing the decoded
     * tasks.
     * @param encodedDuke A list of string that is encoded
     * @return Duke duke object which is decoded
     *
     * @throws IllegalValueException     if any of the fields in any encoded task
     *                                   string is invalid.
     * @throws StorageOperationException if the {@code encodedDuke} is in an invalid
     *                                   format.
     */
    public static Duke decodeDuke(List<String> encodedDuke) throws
            IllegalValueException, StorageOperationException {
        final List<Task> decodedTasks = new ArrayList<>();
        for (String encodedTask : encodedDuke) {
            decodedTasks.add(decodeTaskFromString(encodedTask));
        }
        return new Duke(new TaskList(decodedTasks));
    }

    /**
     * Decodes {@code encodedTask} into a {@code Task}.
     *
     * @throws IllegalValueException     if any field in the {@code encodedTask} is
     *                                   invalid.
     * @throws StorageOperationException if {@code encodedTask} is in an invalid
     *                                   format.
     */
    private static Task decodeTaskFromString(String input) {
        Task newTask = null;
        String[] inputList = input.split("\\|");
        if (!(Pattern.matches(TODO_REGEX, input.trim()) | Pattern
                .matches(DEADLINE_REGEX, input.trim()))) {
        }

        String letterCommand = inputList[0].trim();
        switch(letterCommand) {
        case "D":
            newTask = new Deadline(inputList[2].trim(), inputList[3].trim());
            break;
        case "T":
            newTask = new Todo(inputList[2].trim());
            break;
        case "E":
            newTask = new Event(inputList[2].trim(), inputList[3].trim());
            break;
        case "1":
            newTask.markAsDoneWithoutPrint();
            break;
        default:
            System.out.println("There's something wrong with decoding the file");
        }
        return newTask;
    }

}
