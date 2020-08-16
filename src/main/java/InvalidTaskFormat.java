public class InvalidTaskFormat extends DukeException {
    String taskType;

    public InvalidTaskFormat(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        if (taskType.equals("todo")) {
            return super.toString() + " The description of a todo cannot be empty.";
        } else if (taskType.equals("deadline")){
            return super.toString() + " Please follow the correct format for " + taskType + "s\n" +
                    "      deadline <description> /by <date>";
        } else {
            return super.toString() + " Please follow the correct format for " + taskType + "s\n" +
                           "      event <description> /at <date>";
        }
    }
}
