package alison.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String savedFormat() {
        return "T " + super.savedFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
