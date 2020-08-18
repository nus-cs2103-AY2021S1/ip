public class Deadline extends Task {
    private String dueDate;
    Deadline(String item, String dueDate) {
        super(item);
        this.dueDate = dueDate;
    }

    @Override
    public String getItem() {

        return "[D]" + super.getItem() + "(by:" + dueDate + ")";
    }
}
