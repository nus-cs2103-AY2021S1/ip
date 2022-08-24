public class Event extends Task {
    private String date;
<<<<<<< HEAD
    public Event (String s, boolean isCompleted) {
        super(s.split("/at")[0].substring(5).strip(),isCompleted);
=======
    public Event (String s) {
        super(s.split("/at")[0].substring(5).strip());
>>>>>>> parent of f4e7424 (Add date parsing functionality)
        date = s.split("/at")[1].strip();
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[E]" + completion + " " + this.getTaskName() + " (at: " + date + ")";
<<<<<<< HEAD
    }
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "E | " + completion + " | " + this.getTaskName() + " | " + date;
=======
>>>>>>> parent of f4e7424 (Add date parsing functionality)
    }
}
