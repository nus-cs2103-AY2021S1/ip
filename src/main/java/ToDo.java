class ToDo extends Task {
    static String SYMBOL = "[T]";

    public ToDo(String name) {
        super(name, TaskType.ToDo);
    }

    @Override
    public String toString() {
        String tick = this.isDone() ? "[✓]" : "[✗]";
        return String.format("%s%s %s", SYMBOL, tick, name);
    }
}
