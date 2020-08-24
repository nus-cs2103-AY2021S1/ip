package main.java.Commands;

import main.java.Task.Task;
import main.java.Task.TaskList;

public class addCommand extends Command{
    Task task;
    public addCommand(TaskList tasklist, Task task){
        super(tasklist);
        this.task = task;
    }

    @Override
    public void execute(){
        this.tasklist.addTask(this.task);
    }
}
