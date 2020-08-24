import java.time.LocalDate;

public class ToDo extends Task {

    private ToDo (String task, boolean isDone) {
        super(task, isDone);
    }

    public ToDo(String task) {
        super(task);
    }

    @Override
    public ToDo markDone() {
        return new ToDo(task, true);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ToDo && super.equals(o);
    }

    @Override
    public String saveFormat() {
        return "T" + super.saveFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
