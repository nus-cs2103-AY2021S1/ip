package main.java.Commands;

import main.java.Task.TaskList;

public class deleteCommand extends Command {
    int taskNumber;
    public deleteCommand(TaskList tasklist, int taskNumber){
        super(tasklist);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes a command to delete a task.
     */
    @Override
    public void execute(){
        this.tasklist.deleteTask(taskNumber);
    }
}
