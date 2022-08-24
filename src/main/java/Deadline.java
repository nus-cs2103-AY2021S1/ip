public class Deadline extends Task {
    private String date;
<<<<<<< HEAD
    public Deadline (String s , boolean isCompleted) {
        super(s.split("/by")[0].substring(8).strip(), isCompleted);
=======
    public Deadline (String s) {
        super(s.split("/by")[0].substring(8).strip());
>>>>>>> parent of f4e7424 (Add date parsing functionality)
        date = s.split("/by")[1].strip();
    }
    @Override
    public String toString() {
        String completion = this.isComplete() ? "[✓]" : "[✗]";
        return "[D]" + completion + " " +this.getTaskName() + " (by: " + date + ")";
<<<<<<< HEAD
    }
    public String toFormattedString() {
        String completion = this.isComplete() ? "1" : "0";
        return "D | " + completion + " | " + this.getTaskName() + " | " + date;
=======
>>>>>>> parent of f4e7424 (Add date parsing functionality)
    }
}