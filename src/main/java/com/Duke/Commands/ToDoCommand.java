package com.Duke.Commands;

import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.ToDo;

public class ToDoCommand extends Command{
    private final String task;
    private final TaskList taskList;

    public ToDoCommand(String task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }

    public String execute(){
        try {
            ToDo todo = new ToDo(task, false);
            return UI.toDoCalled(taskList,todo);
        }catch (Exception e) {
            return UI.printError("     \u2639 OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
