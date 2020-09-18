package clippy.task;

import clippy.exception.UpdateToDoTimeException;

/**
 * Represents a task without any date/time attached to it.
 */
public class ToDo extends Task {
    /**
     * Constructs a todo with the given description.
     * 
     * @param desc Literal description of the todo.
     */
    public ToDo(String desc) {
        super(desc);
        taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString();
    }

    /**
     * Generates and return a String encapsulating details of the todo to be stored in the save file.
     *
     * @return A String encapsulating details of the todo to be stored in the save file.
     */
    @Override
    public String generateSaveFileData() {
        return "T|" + (isDone ? "1" : "0") + "|" + desc;
    }

    @Override
    public void updateTime(String newTime) throws UpdateToDoTimeException {
        throw new UpdateToDoTimeException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof ToDo) {
            ToDo other = (ToDo) o;
            return this.desc.equals(other.desc) && this.isDone == other.isDone;
        } else {
            return false;
        }
    }
}
