package duke.task;

public abstract class Task {
    public abstract String getTaskIdentifier();
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public void markAsDone() {
        this.isDone = true;
    }
    @Override
    public String toString() {
        String checkMark = this.isDone ? "X" : " ";
        return squareBox(this.getTaskIdentifier())
                + squareBox(checkMark)
                + " "
                + this.description;
    }
    private String squareBox(String str) {
        return "["+str+"]";
    }
}
