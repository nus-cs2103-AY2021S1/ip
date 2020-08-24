package main.java.duke;

class ToDo extends Task {

    ToDo(String description, boolean isComplete) {
        super(description, isComplete, null);
    }

    @Override
    String[] getDataString() {
        return new String[] {"todo", String.valueOf(isComplete), description};
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
