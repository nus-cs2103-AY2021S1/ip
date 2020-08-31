public class EventTask extends Task {

    private String eventTime;

    public EventTask(String description) {
        super(description.split(" /")[0]);
        String[] output = description.split("/");
        String pattern = ("(at?)(\\s)(.+)");
        eventTime = output[1].replaceAll(pattern, "$3");
    }

    public EventTask(String description, boolean done, String eventTime) {
        super(description);
        isDone = done;
        this.eventTime = eventTime;
    }

    @Override
    public String[] getSaveData() {
        return new String[] {"E", isDone ? "1" : "0", description, eventTime};
    }

    @Override
    public String toString()
    {
        return String.format("[E][%s] %s (at: %s)", getStatusIcon(), description, eventTime);
    }

}
