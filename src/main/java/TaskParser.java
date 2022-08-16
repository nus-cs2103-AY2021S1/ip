import java.io.BufferedReader;
import java.io.IOException;

/**
 * Utility class to handle parsing tasks from local file
 */
public class TaskParser {
    /**
     * Parse a task from buffered reader
     * @param bufferedReader - input stream
     * @return the parsed task
     * @throws DukeException if unable to interpret the input
     * @throws IOException if unable to read from buffered reader
     */
    public static Task parseTask(BufferedReader bufferedReader) throws Exception {
        String input = bufferedReader.readLine();
        String taskType = input.substring(0, 3);
        String status = input.substring(3, 6);
        String rest = input.substring(7);
        boolean isDone = (status.equals("[O]"));
        Task result;

        switch (taskType) {
        case "[T]": {
            result = new Todo(rest, isDone);
            break;
        }
        case "[D]": {
            int position = rest.indexOf("(by: ");
            String description = rest.substring(0, position - 1);
            String by = rest.substring(position + 5, rest.length() - 1);
            result = new Deadline(description, isDone, by);
            break;
        }
        case "[E]": {
            int position = rest.indexOf("(at: ");
            String description = rest.substring(0, position - 1);
            String at = rest.substring(position + 5, rest.length() - 1);
            result = new Event(description, isDone, at);
            break;
        }
        default: {
            throw new DukeException("Error while loading data.");
        }
        }

        return result;
    }
}
