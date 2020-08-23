public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String getStatusIcon() {
        return String.format("[T]%s ", super.getStatusIcon());
    }
}
