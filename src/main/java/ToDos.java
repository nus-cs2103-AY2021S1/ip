public class ToDos extends Task {

    public ToDos(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[T]" + doneIndicator + " " + this.task;
    }
}
