public class Event extends Task {

    private String identifier;

    public Event(String description) {
        super(description);
        this.identifier = "E";
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    public String getDate() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return timeArray[1];
    }

    //separates date from description
    @Override
    public String getDescription() {
        String fullDescription = super.getDescription();
        String[] desArray = fullDescription.split("/", 2);
        return desArray[0];
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);

        return "[E]" + "[" + getStatusIcon() + "] " + desArray[0] + "(" + timeArray[0] + ": " + timeArray[1] + ")";
    }
}
