public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }
    
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
}
