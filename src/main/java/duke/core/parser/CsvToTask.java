package duke.core.parser;

import java.util.Scanner;
import java.util.logging.Logger;

import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;
import duke.core.util.DukeDateTime;

/**
 * Recover the Task from a csv representation
 */
public enum CsvToTask {

    TODO {
        @Override
        protected Task parse(Scanner scanner) {
            return new ToDo(
                    Boolean.parseBoolean(scanner.next()),
                    scanner.next()
            );
        }
    },

    DEADLINE {
        @Override
        protected Task parse(Scanner scanner) {
            return new Deadline(
                    Boolean.parseBoolean(scanner.next()),
                    scanner.next(),
                    new DukeDateTime(scanner.next())
            );
        }
    },

    EVENT {
        @Override
        protected Task parse(Scanner scanner) {
            return new Event(
                    Boolean.parseBoolean(scanner.next()),
                    scanner.next(),
                    new DukeDateTime(scanner.next()),
                    new DukeDateTime(scanner.next())
            );
        }
    };

    private static final Logger logger = Logger.getLogger(CsvToTask.class.getName());

    /**
     * Task specific parser (Helper method).
     * Parses the csv into its String representation
     * @param scanner A scanner initialized with the csv.
     *                The first token should be discarded
     * @return The Task represented by the csv
     */
    protected abstract Task parse(Scanner scanner) throws Exception;

    /**
     * Factory method to obtain a Task from its csv representation
     * @param csv The csv representation of a task.
     * @return The task represented by the csv
     */
    public static Task parse(String csv) {
        assert csv != null;
        try (Scanner scanner = new Scanner(csv)) {
            scanner.useDelimiter(",");
            Task task = CsvToTask.valueOf(scanner.next()).parse(scanner);
            logger.fine("Recovered: " + task.toString());
            return task;
        } catch (Exception e) { // Many errors can occur when parsing
            System.err.println("Corrupt entry: " + csv);
            logger.warning("Corrupt entry: " + csv);
            return null;
        }
    }

}
