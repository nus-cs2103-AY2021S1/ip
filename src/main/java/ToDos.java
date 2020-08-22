public class ToDos extends Task {

    public ToDos(String task) {
        super(task);
    }

    public ToDos(String task, boolean done) {
        super(task, done);
    }
    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[T]" + doneIndicator + " " + this.task;
    }
}
