package com.jacob.duke.command;

import com.jacob.duke.Storage;
import com.jacob.duke.task.Task;
import com.jacob.duke.TaskList;
import com.jacob.duke.Ui;

import java.util.List;

public class DoneCommand implements Command {
    private boolean isComplete = false;
    private String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        List<Task> taskList = tasks.taskList;
        //get the integer from the string command and convert to integer
        Task theTask = taskList.get(Integer.parseInt(fullCommand.substring(5))-1);
        String lineToEdit = theTask.convertToFile();

        //set task as done
        theTask.setDone();
        String replacementText = theTask.convertToFile();

        //replace line
        try {
            storage.replacement(lineToEdit,replacementText);
            isComplete = true;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Line to replace does not match any string in text: "+ e.getMessage());
        }
        ui.showNewTaskAdded(theTask.getCurrentStatus(), taskList);
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
