public class Event extends Task {
    private String date;
    public Event (String s) {
        super(s.split("/at")[0].substring(5).strip());
        date = s.split("/at")[1].strip();
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: " + date + ")";
    }
}
