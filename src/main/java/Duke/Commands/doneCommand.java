package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class doneCommand extends Command {
    int taskNumber;
    public doneCommand(TaskList taskList, int taskNumber) {
        super(taskList,"");
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as done.
     */
    @Override
    public String execute() {
        return this.tasklist.completeTask(taskNumber);
    }
}
