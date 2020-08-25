package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.task.Todo;


public class TodoCommand implements Command {
    private String inputCommand;
    private boolean isComplete;

    public TodoCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        String s = inputCommand.substring(4);

        if (s.equals("")) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task theTodo = new Todo(inputCommand.substring(4 + 1));
        taskList.add(theTodo);

        //append text
        storage.appendText(theTodo.convertToFile());

        //print UI
        ui.showNewTaskAdded(theTodo.getCurrentStatus(), taskList);

    }

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    @Override
    public boolean isBye() {
        return false;
    }

}
