package com.jacob.duke.command;

import com.jacob.duke.*;
import com.jacob.duke.task.Task;
import com.jacob.duke.task.Todo;

import java.util.List;

public class TodoCommand implements Command {
    private String inputCommand;
    private boolean isComplete;

    public TodoCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException{
        List<Task> taskList = tasks.taskList;
        String s = inputCommand.substring(4);

        if (s.equals("")) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task theTodo = new Todo(inputCommand.substring(4+1));
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
