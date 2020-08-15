package task;

public class ToDo extends Task {
    private static String TODO = "[T]";

    ToDo(String task, boolean completed) {
        super(task, completed);
    }

    @Override
    public String toString() {
        return TODO + toStringSuffix();
    }
}
