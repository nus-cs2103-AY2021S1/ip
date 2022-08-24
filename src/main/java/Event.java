public class Event extends Task {
    private String date;
    public Event (String s, boolean isCompleted) {
        super(s.split("/at")[0].substring(5).strip(),isCompleted);
        date = s.split("/at")[1].strip();
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: " + date + ")";
    }
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "E | " + completion + " | " + this.getTaskName() + " | " + date;
    }
}
