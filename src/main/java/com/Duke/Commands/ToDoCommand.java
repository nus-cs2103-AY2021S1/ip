package com.Duke.Commands;

import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
import com.Duke.Tasks.ToDo;
/**
 * This class acts as a model for the execution of the TodoCommand
 */
public class ToDoCommand extends Command{
    private final String task;
    private final TaskList taskList;

    public ToDoCommand(String task, TaskList taskList) {
        this.task = task;
        this.taskList = taskList;
    }
    /**
     * Simulates Duke executing the Find Command
     * @return The response to the command "todo"
     */
    public String execute() {
        String result;
        try {
            ToDo todo = new ToDo(task, false);
            result = UI.toDoCalled(taskList, todo);
        } catch (Exception e) {
            result = UI.printError("     \u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        return result;
    }
}
