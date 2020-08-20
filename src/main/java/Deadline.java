public class Deadline extends Task{
    protected String dateAndTime;
    Deadline(String description, String dateAndTime, TaskType taskType) {
        super(description, taskType);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[Deadline]" + super.toString() + "(by: " + dateAndTime + ")";
    }
}
