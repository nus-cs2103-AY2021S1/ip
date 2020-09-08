package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.task.Task;
import main.java.com.jacob.duke.task.Todo;


public class TodoCommand implements Command {
    private String inputCommand;

    /**
     * Constructor for To do Command.
     *
     * @param fullCommand with from console input.
     */
    public TodoCommand(String fullCommand) {
        this.inputCommand = fullCommand;
    }

    /**
     * Execution command for pre-determined To-do Command.
     *
     * @param ui UI object to deal with program output.
     * @param dukeList Task List Representation.
     * @param storage Storage object to deal with interfacing with file system.
     */
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) {
        List<Task> taskList = dukeList.getTaskList();
        String description = inputCommand.substring(4);

        Task theTodo = new Todo(description.trim());
        taskList.add(theTodo);

        storage.appendTextToTasks(theTodo.convertToFile());

        return ui.showNewTaskAdded(theTodo.getCurrentStatus(), taskList);
    }

    /**
     * Check if it is the bye Command
     * @return false since it is not
     */
    @Override
    public boolean isBye() {
        return false;
    }

}
