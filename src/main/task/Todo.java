package main.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean doneState) {
        super(name, doneState);
    }

    @Override
    public String write() {
        return String.format("T,%s", super.write());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Task o = (Task) obj;
            return super.equals(o);
        }
        return false;
    }
}
