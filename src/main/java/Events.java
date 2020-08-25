public class Events extends Task {
    private String at;

    Events(String taskInfo , String at) {
        super(taskInfo, TaskType.EVENT);
        this.at = at;
    }

    public String returnTime() {
        return at;
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
