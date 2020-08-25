package duke.task;

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    public String getSaveToFileString() {
        return "T`" + super.getSaveToFileString();
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
