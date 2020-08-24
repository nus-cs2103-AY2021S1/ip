public class Deadline extends Task {

    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName, deadlineTime);
    }

    @Override
    public String writeToFile() {
        return "deadline" + "|" + this.getStatusSymbol() + "|"
                + this.taskName + "|" + this.completeBy;
    }

    @Override
    public String toString() {
        TimeParser timeParser = new TimeParser(localDate);
        String formattedTime = timeParser.getFormattedTime();
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
