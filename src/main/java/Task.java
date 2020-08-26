package main.java;

class Task {
    private final String line = "____________________________________________________________\n";
    protected String description;
    protected boolean isDone;

     Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void finishTask() {
        if (this.isDone) {
            System.out.println("Task is already done!\n" + line);
        } else {
            System.out.println("Congratulations! I have marked this task done.\n" + line);
            this.isDone = true;
        }
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getTypeOfTask() {
        return "todo";
    }

    @Override
    public String toString() {
        return "[T] [" + this.getStatusIcon() + "] " + this.description;
    }

    public String getStoreRepresentation() {
        String doneStatus = this.isDone ? "D," : "N,";
        return "T," + doneStatus + this.description;
    }
}