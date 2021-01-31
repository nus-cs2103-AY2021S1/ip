package duke.storage;

import java.io.IOException;
import java.time.LocalDate;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class StorageParser {
    /**
     * Creates a <code>Task</code> from storage
     *
     * @param line the string representation of the task in a file
     * @throws IOException if the task cannot be read from the file correctly
     */
    public static Task parseTask(String line) throws IOException {
        String[] input = line.split("\\s\\|\\s");
        assert input.length >= 3;
        boolean isDone = input[1].equals("1");
        switch (input[0]) {
        case "T":
            return new ToDo(input[2], isDone);
        case "D":
            assert input.length == 4;
            return new Deadline(input[2], isDone, LocalDate.parse(input[3]));
        case "E":
            assert input.length == 4;
            return new Event(input[2], isDone, LocalDate.parse(input[3]));
        default:
            throw new IOException("Error reading tasks from file.");
        }
    }
}
