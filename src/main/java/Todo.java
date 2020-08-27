package main.java;

class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public String toData() {
        return "T | " + super.toData();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
