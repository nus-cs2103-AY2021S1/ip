package main.java;

class ToDo extends Task {

    ToDo(String description) {
        super(description);
    }

    ToDo(boolean isCompleted, String description) {
        super(isCompleted, description);
    }

    String[] getDataString() {
        return new String[] {"todo", String.valueOf(isCompleted), description};
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
