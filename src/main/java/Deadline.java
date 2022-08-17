public class Deadline extends Task {
    private String date;
    public Deadline (String s) {
        super(s.split("/by")[0].substring(8).strip());
        date = s.split("/by")[1].strip();
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[D]" + completion + " " +this.getTaskName() + " (by: " + date + ")";
    }
}