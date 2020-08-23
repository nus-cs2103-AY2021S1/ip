package tasks;

public class EventTask extends Task {

    String title;
    String dateTimeDetails;

    public EventTask(String title, String dateTimeDetails) {
        super(String.format("%s (at: %s)", title, dateTimeDetails), "E");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }
}
