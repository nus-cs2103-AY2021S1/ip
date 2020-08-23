package main.java;

class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
