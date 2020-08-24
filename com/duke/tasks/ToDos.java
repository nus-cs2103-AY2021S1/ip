package com.duke.tasks;

public class ToDos extends Task {

    public ToDos(String task) {
        super(task);
    }

    public ToDos(String task, boolean isDone) {
        super(task, isDone);
    }

    @Override
    public String toString() {
        String doneIndicator = this.done ? "[✓]" : "[✗]";
        return "[T]" + doneIndicator + " " + this.task;
    }

    @Override
    public String parseToSaveFormat() {
        String isDoneStr = this.done ? "1" : "0";
        String res = "T - " + isDoneStr + " - " + this.task;
        return res;
    }
}
