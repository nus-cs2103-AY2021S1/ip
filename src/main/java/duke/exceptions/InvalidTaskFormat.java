package duke.exceptions;

import duke.tasks.TaskType;

public class InvalidTaskFormat extends DukeException {

    private TaskType taskType;

    public InvalidTaskFormat(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        switch (taskType) {
        case TODO:
            return super.toString() + " The description of a todo cannot be empty.";
        //Fallthrough
        case EVENT:
            return super.toString() + " Please follow the correct format for events\n" + "      event <description> /at <date>";
        //Fallthrough
        case DEADLINE:
            return super.toString() + " Please follow the correct format for deadlines\n" + "      deadline <description> /by <date>";
        //Fallthrough
        default:
            return "What kind of task is that ?!?! :O";
        }
    }
}
