package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class Command {
    protected String command;
    protected int order;
    protected LocalDate date;
    protected boolean hasDate;
    protected String time;
    protected String type;
    protected boolean isExit;
    protected int priority;

    public Command(String command) {
        this.command = command;
    }

    public Command(String command, int order) {
        this.command = command;
        this.order = order;
    }

    public Command(String type, String command) {
        this.type = type;
        this.command = command;
    }

    public Command(String command, int order, int priority) {
        this.command = command;
        this.order = order;
        this.priority = priority;
    }

    public Command(String type, String command, String time, LocalDate date, boolean hasDate) {
        this.type = type;
        this.command = command;
        this.time = time;
        this.date = date;
        this.hasDate = hasDate;
    }

    /**
     * Execute the command and store the task into the list.
     *
     * @param tasks   The tasklist object that contains the list of tasks.
     * @param ui      The ui object.
     * @param storage The storage object
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "";
        return message;
    }

    public boolean isExit() {
        return isExit;
    }
}
