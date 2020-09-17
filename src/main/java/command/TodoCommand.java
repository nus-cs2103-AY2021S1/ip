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

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todo = new Todo(desc);
        taskList.addTask(todo);
        ui.showAddTask(todo);
        ui.showNumberOfTasksLeft(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
