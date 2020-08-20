public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return "[D]" + "[" + getStatusIcon() + "] " + desArray[0] + "(" + timeArray[0] + ": " + timeArray[1] + ")";
    }
}