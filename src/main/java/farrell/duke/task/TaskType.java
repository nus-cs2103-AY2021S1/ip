package main.java.farrell.duke.task;

import main.java.farrell.duke.DukeException;

/**
 * Enumeration of the types of tasks handled by the program.
 */
public enum TaskType {
    TODO {
        @Override
        public Task getTaskFromData(String[] data) throws DukeException {
            return ToDo.fromData(data);
        }
    },
    EVENT {
        @Override
        public Task getTaskFromData(String[] data) throws DukeException {
            return Event.fromData(data);
        }
    },
    DEADLINE {
        @Override
        public Task getTaskFromData(String[] data) throws DukeException {
            return Deadline.fromData(data);
        }
    };

    /**
     * Maps a string to its corresponding TaskType value.
     *
     * @param string The string to be mapped.
     * @return The TaskType value associated with the string.
     */
    public static TaskType enumFromString(String string) {
        return TaskType.valueOf(string.toUpperCase());
    }

    /**
     * Creates the corresponding task object from an array of parameters.
     *
     * @param data Parameters to create the task.
     * @return The created Task object.
     * @throws DukeException If there are problems with the parameters provided.
     */
    public abstract Task getTaskFromData(String[] data) throws DukeException;
}
