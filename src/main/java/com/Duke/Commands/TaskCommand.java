package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.Task;

public class TaskCommand extends Command{
    private final TaskList ls;
    private final String task;

    public TaskCommand(TaskList ls, String task) {
        this.ls = ls;
        this.task = task;
    }

    @Override
    public String execute() {
        try {
            Task newTask = new Task(task, false);
            return UI.taskCalled(ls,newTask);
        }catch(DukeException e){
            return UI.printError(e.toString());
        }
    }
}
