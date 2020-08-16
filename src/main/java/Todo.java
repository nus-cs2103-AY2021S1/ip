class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
