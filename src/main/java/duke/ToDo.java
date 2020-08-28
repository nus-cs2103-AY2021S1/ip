package duke;

class ToDo extends Task {
    static String SYMBOL = "[T]";

    public ToDo(String name) {
        super(name, TaskType.ToDo);
    }

    @Override
    public String getFileString() {
        String status = this.isDone() ? "T" : "F";
        return String.format("%s~todo %s\n", status, name);
    }

    @Override
    public String toString() {
        String tick = this.isDone() ? "[✓]" : "[✗]";
        return String.format("%s%s %s", SYMBOL, tick, name);
    }
}
