public class Task {
    private static final String COMPLETE = "\u2713";
    private static final String NOT_COMPLETE = "\u2718";

    private String description;
    private boolean isComplete = false;

    public Task(String description) {
        this.description = description;
    }

    public void completeTask() {
        isComplete = true;
    }

    public void resetTask() {
        isComplete = false;
    }

    @Override
    public String toString() {
        String completionFlag = isComplete ? COMPLETE : NOT_COMPLETE;
        return "[" + completionFlag + "] | " + description;
    }
}
