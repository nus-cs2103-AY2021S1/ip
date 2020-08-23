public class ToDo extends Task{
    public ToDo(String taskName) {
        super(taskName);
    }

    public String dataStorage() {
        return "T | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
