package main.java;

class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(boolean isCompleted, String description) {
        super(description);
        super.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
