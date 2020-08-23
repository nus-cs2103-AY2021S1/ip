package main.java;

class ToDo extends Task {

    ToDo(String description) {
        super(description, null);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
