package main.java;

class Task {

    final String description;
    boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    String getStatusIcon() {
        if (this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    void completeTask() {
        this.isCompleted = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
