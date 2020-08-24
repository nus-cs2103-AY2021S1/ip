package main.java.Commands;

import main.java.Task.TaskList;

public class doneCommand extends Command{
    int taskNumber;
    public doneCommand(TaskList taskList,int taskNumber){
        super(taskList);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(){
        this.tasklist.completeTask(taskNumber);
    }
}
