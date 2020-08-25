public class Events extends Task {
    private String at;

    Events(String taskInfo , String at) {
        super(taskInfo, TaskType.EVENT, at);
        this.at = at;
    }

    @Override
    public Events doneTask() {
        super.done = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), this.at);
    }

}
