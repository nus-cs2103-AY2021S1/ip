package main.java.Commands;

import main.java.Task.TaskList;

public class deleteCommand extends Command {
    String taskNumber;
    public deleteCommand(TaskList tasklist, String taskNumber){
        super(tasklist);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(){
        this.tasklist.deleteTask(taskNumber);
    }
}
