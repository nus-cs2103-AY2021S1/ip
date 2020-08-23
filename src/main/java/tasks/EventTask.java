package tasks;

public class EventTask extends Task {

    String title;
    String dateTimeDetails;

    public EventTask(String title, String dateTimeDetails) {
        super(String.format("%s (at: %s)", title, dateTimeDetails), "E");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }

    public EventTask(String title, String dateTimeDetails, boolean isDone) {
        super(String.format("%s (at: %s)", title, dateTimeDetails), isDone, "E");
        this.title = title;
        this.dateTimeDetails = dateTimeDetails;
    }

    @Override
    public String getSaveFormat() {
        return String.format("E | %s | %s | %s",
                super.getIsDone() ? 1 : 0, title, dateTimeDetails);
    }
}
