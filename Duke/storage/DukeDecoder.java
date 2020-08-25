package Duke.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Duke.data.Duke;
import Duke.data.task.*;
import Duke.storage.StorageFile.StorageOperationException;
import Duke.storage.StorageFile.InvalidStorageFilePathException;
import Duke.data.exception.IllegalValueException;

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
