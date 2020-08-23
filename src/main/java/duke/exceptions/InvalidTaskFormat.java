package duke.exceptions;

import duke.tasks.TaskType;

/**
 * Represents an Invalid Task format exception.
 */
public class InvalidTaskFormat extends DukeException {

    private TaskType taskType;

    /**
     * Creates an instance of an InvalidTaskFormat exception with the
     * appropriate task type.
     *
     * @param taskType Type of task that has the wrong command format.
     */
    public InvalidTaskFormat(TaskType taskType) {
        this.taskType = taskType;
    }

    /**
     * Returns a string of the appropriate format description for the
     * infringing task.
     *
     * @return Task format description.
     */
    @Override
    public String toString() {
        switch (taskType) {
        case TODO:
            return super.toString() + " The description of a todo cannot be empty.";
            //Fallthrough
        case EVENT:
            return super.toString() + " Please follow the correct format for events\n" +
                           "      event <description> /at <date>";
            //Fallthrough
        case DEADLINE:
            return super.toString() + " Please follow the correct format for deadlines\n" +
                           "      deadline <description> /by <date>";
            //Fallthrough
        default:
            return "What kind of task is that ?!?! :O";
        }
    }
}
