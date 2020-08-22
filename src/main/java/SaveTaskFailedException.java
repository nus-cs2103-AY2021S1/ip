public class SaveTaskFailedException extends SaveFailedException {
    SaveTaskFailedException(int taskIndex) {
        super("task " + taskIndex);
    }
}
