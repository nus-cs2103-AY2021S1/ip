package main.java.com.jacob.duke.command;

import java.util.List;

import main.java.com.jacob.duke.Storage;
import main.java.com.jacob.duke.TaskList;
import main.java.com.jacob.duke.Ui;

import main.java.com.jacob.duke.task.Task;



public class DoneCommand implements Command {
    private boolean isComplete = false;
    private String fullCommand;

    public DoneCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) {
        List<Task> taskList = tasks.getTaskList();
        //get the integer from the string command and convert to integer
        Task theTask = taskList.get(Integer.parseInt(fullCommand.substring(5)) - 1);
        String lineToEdit = theTask.convertToFile();

        //set task as done
        theTask.setDone();
        String replacementText = theTask.convertToFile();

        //replace line
        try {
            storage.replacement(lineToEdit, replacementText);
            isComplete = true;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Line to replace does not match any string in text: " + e.getMessage());
        }
        ui.showDone(theTask.getCurrentStatus());
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
