public class Deadlines extends Task{
    public Deadlines(String taskTitle) {
        super(taskTitle);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
