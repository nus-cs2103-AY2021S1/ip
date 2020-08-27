package com.Duke.Commands;

import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.ToDo;

public class ToDoCommand extends Command{
    private final String task;
    private final TaskList ls;

    public ToDoCommand(String task, TaskList ls) {
        this.task = task;
        this.ls = ls;
    }

    public void execute(){
        try {
            ToDo todo = new ToDo(task, false);
            UI.toDoCalled(ls,todo);
        }catch (Exception e) {
            UI.printError("     \u2639 OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
