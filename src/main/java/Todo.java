public class Todo extends Task {
    public static final String STORE_TODO = "T";

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    @Override
    public String toStorageString() {
        return STORE_TODO + " | " + description + " | " + getCompletionFlagStorage();
    }

    @Override
    public String toString() {
        return printCompletionFlag() + " | T | " + description;
    }
}
