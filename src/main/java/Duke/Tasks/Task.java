package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;

public class Task {
    protected String name;
    protected boolean isDone;
    public boolean isExit;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.isExit = false;
    }


    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public Task markDone() {
        this.isDone = true;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.name;
    }

    public String fileFormattedString() {
        String doneOrNot = isDone ? "1" : "0";
        return "N | " + doneOrNot + " | " + this.name;
    }
    public void excute(TaskList tasklist, Ui ui, Storage storage){};
}
