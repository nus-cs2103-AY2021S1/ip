package duke.exception;

public class SaveTaskFailedException extends SaveFailedException {
    public SaveTaskFailedException(int taskIndex) {
        super("task " + taskIndex);
    }
}
