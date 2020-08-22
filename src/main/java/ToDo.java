public class ToDo extends Task {

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return done
                ? "[T][✓] " + text
                : "[T][✗] " + text;
    }

    public String toCommand() {
        return done
                ? "done todo " + text
                : "todo " + text;
    }
}