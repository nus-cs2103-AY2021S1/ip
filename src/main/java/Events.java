public class Events extends Task{
    public Events(String taskTitle) {
        super(taskTitle);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
