public class Deadline extends TimeTask {
    public Deadline(String description, String time) throws DukeException {
        super(description, time);
    }
    @Override
    public String getTaskIdentifier() {
        return "D";
    }
    @Override
    public String getDateDescriptor() {
        return "by";
    }
}
