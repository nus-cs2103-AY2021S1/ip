public class Todo extends Task {

    Todo(String task) {
        super(task);
    }

    Todo(String task, boolean done) { super(task, done);}

    @Override
    String getSaveString() {
        return "[T] " + super.getSaveString()  + "/";
    }

    @Override
    public String toString() {
        return "[Todo] " + super.toString();
    }
}
