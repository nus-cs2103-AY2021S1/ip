public class TodoTask extends Task {

    private static String display = "[T]";

    TodoTask(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return display + super.toString();
    }

    @Override
    public String saveString() { return "T/break/" + this.done + "/break/" + name; }
}
