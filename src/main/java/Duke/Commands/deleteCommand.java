package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class deleteCommand extends Command {
    int taskNumber;
    public deleteCommand(TaskList tasklist, int taskNumber) {
        super(tasklist,"");
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a command to delete a task.
     */
    @Override
    public String execute() {
        return this.tasklist.deleteTask(taskNumber);
    }
}
