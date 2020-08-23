package tasks;

public class DeadlineTask extends Task {

    String title;
    String dateTimeDetails;

    public DeadlineTask(String title, String dateTimeDetails) {
        super(String.format("%s (by: %s)", title, dateTimeDetails), "D");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }
}
