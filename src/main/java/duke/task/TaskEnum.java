package duke.task;

/**
 * An enumeration of all tasks
 * Provides a method to recover the Task from a csv representation
 */
public enum TaskEnum {

    TODO {
        @Override
        public Task fromCsv(String csv) throws Exception {
            return ToDo.fromCsv(csv);
        }
    },

    DEADLINE {
        @Override
        public Task fromCsv(String csv) throws Exception {
            return Deadline.fromCsv(csv);
        }
    },

    EVENT {
        @Override
        public Task fromCsv(String csv) throws Exception {
            return Event.fromCsv(csv);
        }
    };

    /**
     * Factory method to obtain a Task from its csv representation
     * @param csv The csv representation of a task
     * @return The task represented by the csv
     * @throws Exception If the csv cannot be parsed
     */
    public abstract Task fromCsv(String csv) throws Exception;
}
