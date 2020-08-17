public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean done) {
       super(name, done);
    }

    @Override
    public ToDo setDone(boolean b) {
        return new ToDo(this.getName(), true);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
