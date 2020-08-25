public class Deadline extends TimedTask {

    public Deadline(String description, String deadline) {
        super(description, deadline);
        super.type = Task.Type.DEADLINE;
    }
}