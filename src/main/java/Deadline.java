public class Deadline extends Task {

    private final String description;

    public Deadline(String description) {
        super(description);
        this.description = description;
    }

    @Override
    public String getDescription() {
        String[] desArray = this.description.split("/", 2);
        return desArray[0];
    }

    public String getTime() {
        String[] desArray = this.description.split("/", 2);
        return desArray[1];
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return "[D]" + getStatusIcon() + desArray[0] + "(" + timeArray[0] + ": " + timeArray[1] + ")";
    }
}