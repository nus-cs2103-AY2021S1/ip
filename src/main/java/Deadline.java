public class Deadline extends Task {
    private String deadline;

    public Deadline(String title, String deadline) {
        super(title);
        this.deadline = deadline;
    }

    public Deadline(String title, Boolean isComplete, String deadline) {
        super(title, isComplete);
        this.deadline = deadline;
    }

    @Override
    public String saveString() {
        int completeSymbol = this.complete ? 1 : 0;
        return String.format("D|%d|%s|%s", completeSymbol, this.title, this.deadline);
    }

    @Override
    public String toString() {
        String completeSymbol = this.complete ? "[/]" : "[X]";
        return String.format("[D]%s %s (by: %s)", completeSymbol, this.title, this.deadline);
    }
}