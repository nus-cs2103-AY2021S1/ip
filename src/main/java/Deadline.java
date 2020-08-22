public class Deadline extends Task {
    private String dueDate;
    Deadline(String item, String dueDate, boolean completed) {
        super(item, completed);
        this.dueDate = dueDate;
    }

    @Override
    public String getItem() {
        return "[D]" + super.getItem() + "(by: " + dueDate + ")";
    }

    public String getDueDate() {
        return dueDate;
    }
}
