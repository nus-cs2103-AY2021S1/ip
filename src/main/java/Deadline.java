public class Deadline extends Task {

    private String identifier;

    public Deadline(String description) {
        super(description);
        this.identifier = "D";
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    //separates date from description
    @Override
    public String getDescription() {
        String fullDescription = super.getDescription();
        String[] desArray = fullDescription.split("/", 2);
        return desArray[0];
    }

    public String getDate() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return timeArray[1];
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return "[D]" + "[" + getStatusIcon() + "] " + desArray[0] + "(" + timeArray[0] + ": " + timeArray[1] + ")";
    }
}