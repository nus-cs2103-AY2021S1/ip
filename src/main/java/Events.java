public class Events extends Task {

    protected String timing;

    protected static final String SPLITTER = " /at ";

    public Events(String taskDescription) {
        super(taskDescription.split(SPLITTER)[0]);
        timing = taskDescription.split(SPLITTER)[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), timing);
    }



}
