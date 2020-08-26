public class Deadline extends Task {

    public Deadline(String deadlineName, String deadlineTime) {
        super(deadlineName, deadlineTime);
    }

    @Override
    public String writeToFile() {
        TimeParser timeParser = new TimeParser(localDate, time);
        String formattedTime = timeParser.getFormattedTime();
        return "deadline" + "|" + this.getStatusSymbol() + "|"
                + this.taskName + "|" + formattedTime;
    }

    @Override
    public String toString() {
        TimeParser timeParser = new TimeParser(localDate, time);
        String formattedTime = timeParser.getFormattedTime();
        return "[D]" + super.toString() + " (by: " + formattedTime + ")";
    }
}
