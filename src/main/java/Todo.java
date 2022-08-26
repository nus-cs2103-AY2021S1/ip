public class Todo extends Task {
    public Todo (String s, boolean isCompleted) {
        super(s.substring(5), isCompleted);
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[T]" + completion + " " + this.getTaskName();
    }
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "T | " + completion + " | " + this.getTaskName();
    }
}
