public class InvalidTaskFormat extends DukeException {
    TaskType taskType;

    public InvalidTaskFormat(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        switch (taskType) {
            case TODO:
                return super.toString() + " The description of a todo cannot be empty.";
            case EVENT:
                return super.toString() + " Please follow the correct format for events\n" +
                               "      event <description> /at <date>";
            case DEADLINE:
                return super.toString() + " Please follow the correct format for deadlines\n" +
                               "      deadline <description> /by <date>";
            default:
                return "What kind of task is that ?!?! :O";
        }


    }
}
