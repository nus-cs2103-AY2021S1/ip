package com.duke.tasks;

public class ToDos extends Task {

    public ToDos(String task) {
        super(task);
    }

    public ToDos(String task, boolean done) {
        super(task, done);
    }

    /**
     * Returns a string representation of the contents of the specified array.
     *
     * @return String Returns a string representation of the contents of the specified array.
     */
    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[T]" + doneIndicator + " " + this.task;
    }

    /**
     * Returns a string representation of the Deadline object to be saved in persistent file.
     *
     * @return String Returns a string representation of the ToDos object to be saved in persistent file.
     */
    @Override
    public String parseToSaveFormat() {
        String isDoneStr = this.done ? "1" : "0";
        String res = "T - " + isDoneStr + " - " + this.task;
        return res;
    }
}
