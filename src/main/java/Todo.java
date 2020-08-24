class Todo extends Task {
    Todo(String name) {
        super(name, Type.TODO);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
