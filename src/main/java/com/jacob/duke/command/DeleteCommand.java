package main.java.com.jacob.duke.command;

import main.java.com.jacob.duke.*;
import main.java.com.jacob.duke.task.Task;

import java.util.List;

public class DeleteCommand implements Command{
    private String inputCommand;
    private boolean isComplete;
    public DeleteCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }

    @Override
    public void execute(Ui ui, TaskList tasks, Storage storage) throws DukeException {
        List<Task> taskList = tasks.taskList;
        Task theRemovedTask = taskList.remove(Integer.parseInt(inputCommand.substring(7))-1);
        if (theRemovedTask == null) {
            throw new DukeException("No such task exists! ");
        }
        //remove text
        storage.removeText(theRemovedTask.convertToFile());

        //print the output

        ui.showTaskDeleted(theRemovedTask.getCurrentStatus(),taskList);
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
