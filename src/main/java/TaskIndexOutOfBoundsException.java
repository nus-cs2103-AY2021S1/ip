public class TaskIndexOutOfBoundsException extends DukeException {
    private String index;

    public TaskIndexOutOfBoundsException(String index) {
        this.index = index;
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + " There is no task no. " + index;
    }
}
