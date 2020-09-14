package duke.task;

import duke.command.InvalidCommandException;
import duke.component.Parser;

/**
 * Represents a todo task that contains a description.
 */
public class ToDo extends Task {
    /**
     * Creates a ToDo task.
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Creates a todo task using the resource file.
     * @param taskInfo the full line of the task
     * @throws InvalidCommandException if the resource file format is invalid
     */
    public ToDo(String[] taskInfo) throws InvalidCommandException {
        super("");
        assert taskInfo[0].equals("T") : "Wrong read of file";
        if (taskInfo.length != 3) {
            throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
        }
        try {
            isDone = Integer.parseInt(taskInfo[1]) == 1;
            description = taskInfo[2];
        } catch (Exception e) {
            throw new InvalidCommandException(Parser.INVALID_FILE_EXCEPTION);
        }
    }

    @Override
    public String outputToFile() {
        return "T" + super.outputToFile() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Checks whether the given object equals this ToDo task.
     * @param obj the given object to compare
     * @return true if the object is a ToDo and the description equals
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo) {
            ToDo o = (ToDo) obj;
            return description.equals(o.description);
        } else {
            return false;
        }
    }
}
