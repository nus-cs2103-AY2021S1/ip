package duke.core.storage;

import duke.core.util.DukeDateTime;
import duke.core.task.Deadline;
import duke.core.task.Event;
import duke.core.task.Task;
import duke.core.task.ToDo;

import java.util.Scanner;

/**
 * Recover the Task from a csv representation
 */
public enum CsvToTask {

    TODO {
        @Override
        public Task parse(String csv) {
            // Parse input
            Scanner scanner = new Scanner(csv);
            scanner.useDelimiter(",");
            scanner.next(); // Discard first match

            // Construct task from csv
            return new ToDo(
                    Boolean.parseBoolean(scanner.next()),
                    scanner.next()
            );
        }
    },

    DEADLINE {
        @Override
        public Task parse(String csv) {
            // Parse input
            Scanner scanner = new Scanner(csv);
            scanner.useDelimiter(",");
            scanner.next(); // Discard first match

            // Construct deadline from csv
            return new Deadline(
                    Boolean.parseBoolean(scanner.next()),
                    scanner.next(),
                    new DukeDateTime(scanner.next())
            );
        }
    },

    EVENT {
        @Override
        public Task parse(String csv) {
            // Parse input
            Scanner scanner = new Scanner(csv);
            scanner.useDelimiter(",");
            scanner.next(); // Discard first match

            // Construct task from csv
            return new Event(
                    Boolean.parseBoolean(scanner.next()),
                    scanner.next(),
                    new DukeDateTime(scanner.next()),
                    new DukeDateTime(scanner.next())
            );
        }
    };

    /**
     * Factory method to obtain a Task from its csv representation
     * @param csv The csv representation of a task
     * @return The task represented by the csv
     * @throws Exception If the csv cannot be parsed
     */
    public abstract Task parse(String csv) throws Exception;

}
