class ToDo extends Task {
    public ToDo(String name) {
        super(name, TaskType.ToDo);
    }

    @Override
    public String toString() {
        return name;
    }
}
