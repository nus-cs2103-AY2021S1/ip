public class Deadline extends Task {
    private String deadline;
    Deadline(String content, String deadline) {
        super(content);
        this.deadline = deadline;
    }

    @Override
    public String returnStringForm() {
        return "[D]" + super.returnStringForm() + "(by: " + this.deadline + ")";
    }
}
