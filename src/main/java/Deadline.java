public class Deadline extends Task {
    private DateTime deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = new DateTime(deadline);
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[D]%s %s (by: %s)", completeSymbol, this.title, this.deadline);
    }
}