public class Event extends Task {
    private String dateAndTime;

    public Event(String description) {
        super(description.split("/")[0]);
        this.dateAndTime = description.split("/")[1];
    }

    public String dateAndTimeBracket() {
        String formattedDateAndTime[] = this.dateAndTime.split(" ", 2);
        return String.format("(%s: %s)", formattedDateAndTime[0], formattedDateAndTime[1]);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s %s",
                this.getStatusIcon(), this.description, this.dateAndTimeBracket());
    }
}
