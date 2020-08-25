package src.main.java.duke.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import src.main.java.duke.data.Duke;
import src.main.java.duke.data.task.*;
import src.main.java.duke.storage.StorageFile.StorageOperationException;
import src.main.java.duke.data.exception.IllegalValueException;

public class DukeDecoder {

    /**
     * Decodes {@code encodedDuke} into an {@code Duke} containing the decoded
     * tasks.
     *
     * @throws IllegalValueException     if any of the fields in any encoded task
     *                                   string is invalid.
     * @throws StorageOperationException if the {@code encodedDuke} is in an invalid
     *                                   format.
     */
    public static Duke decodeDuke(List<String> encodedDuke) throws IllegalValueException, StorageOperationException {
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
        if (!(Pattern.matches("(T)+\\s+([|])+\\s+([10])+\\s+([|])+\\s+\\w+.+", input.trim()) | Pattern
                .matches("([DE])+\\s+([|])+\\s+([10])+\\s+([|])+\\s+\\w+.+\\s+([|])+\\s+\\w+.+", input.trim()))) {
            System.out.println("I'm sorry, but I don't know what that means :-(");
        }

        if (inputList[0].trim().equals("D")) {
            newTask = new Deadline(inputList[2].trim(), inputList[3].trim());
        }

        if (inputList[0].trim().equals("T")) {
            newTask = new Todo(inputList[2].trim());
        }

        if (inputList[0].trim().equals("E")) {
            newTask = new Event(inputList[2].trim(), inputList[3].trim());
        }

        if (inputList[1].trim().equals("1")) {
            newTask.markAsDoneWithoutPrint();
        }
        return newTask;
    }

}
