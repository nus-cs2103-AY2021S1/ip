public class Todo extends Task {
    public Todo (String s) {
        super(s.substring(5));
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[T]" + completion + " " + this.getTaskName();
    }
}
