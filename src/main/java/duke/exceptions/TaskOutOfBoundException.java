package duke.exceptions;

public class TaskOutOfBoundException extends DukeException {

    private int taskNum;

    /**
     * Constuctor for task out of bound exception.
     * @param err error message
     * @param taskNum input task number
     */
    public TaskOutOfBoundException(String err, int taskNum) {
        super(err);
        this.taskNum = taskNum;
    }

    @Override
    public String getMessage() {
        return "😝OOPS!!! You do not have task " + taskNum + " in the schedule.";
    }
}
