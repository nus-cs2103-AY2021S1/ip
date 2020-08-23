public class TaskIndexOutOfBoundsException extends DukeException {
    private String index;

    public TaskIndexOutOfBoundsException(String index) {
        this.index = index;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " There is no task no. " + index;
    }
}
