package main.java.duke.command;

import main.java.duke.command.Command;
import main.java.duke.task.Todo;
import main.java.duke.task.TaskList;
import main.java.duke.Ui;
import main.java.duke.Storage;

public class TodoCommand extends Command {
    String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        return (ui.showAddTask(todo) + ui.showNumberOfTasksLeft(taskList));
    }

    public boolean isExit() {
        return false;
    }
}
