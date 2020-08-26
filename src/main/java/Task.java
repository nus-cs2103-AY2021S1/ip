package main.java;

class Task {
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
         Ui.printDoneMessage(this.isDone);
        if (!this.isDone) {
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