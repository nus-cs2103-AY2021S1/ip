package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class FileParser {

    private static final String REGEX_SEPARATOR = ",, ";

    /**
     * Reads a String and splits it to create a new Task based on its type and values.
     * Format: Type | Completed | Name | Time
     * Example: "[D],, 1,, deadline,, 23-08-2020"
     *
     * @param line the String from the csv to be parsed
     * @return A duke.Tasks.Task object
     * @throws DukeIoException if the line does not follow the given regex.
     */
    public static Task parseLine(String line) throws DukeIoException {
        String[] values = line.split(REGEX_SEPARATOR);
        switch (values[0]) {
        case "[T]":
            return new Todo(values[2], values[1]);
        case "[E]":
            return new Event(values[2], LocalDate.parse(values[3], DateTimeFormatter.ofPattern("MMM d yyyy")),
                    values[1]);
        case "[D]":
            return new Deadline(values[2], LocalDate.parse(values[3], DateTimeFormatter.ofPattern("MMM d yyyy")),
                    values[1]);
        default:
            throw new DukeIoException(String.format("The line '%s' could not be parsed.", line));
        }
    }

    /**
     * Converts a Task into a String format for writing to disk.
     *
     * @param task the Task to be converted
     * @return the String representation of the Task to be used in the data file.
     */
    public static String convertTaskToSaveFormat(Task task) {
        String[] args = task.toSaveFormatArray();
        assert (args.length >= 3) : "formatArray of Task is not of the correct length";
        String result = "";
        for (String s : args) {
            result = result.concat(String.format("%s%s", s, REGEX_SEPARATOR));
        }
        return result + "\n";
    }
}
