public class Deadlines extends Task {

    protected String deadLine;

    protected static final String SPLITTER = " /by ";

    public Deadlines(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        deadLine = taskDescription.split(SPLITTER)[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadLine);
    }





}
