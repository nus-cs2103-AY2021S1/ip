package tasks;

public class DeadlineTask extends Task {

    String title;
    String dateTimeDetails;

    public DeadlineTask(String title, String dateTimeDetails) {
        super(String.format("%s (by: %s)", title, dateTimeDetails), "D");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }

    public DeadlineTask(String title, String dateTimeDetails, boolean isDone) {
        super(String.format("%s (by: %s)", title, dateTimeDetails), isDone, "D");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }

    @Override
    public String getSaveFormat() {
        return String.format("D | %s | %s | %s",
                super.getIsDone() ? 1 : 0, title, dateTimeDetails);
    }
}
