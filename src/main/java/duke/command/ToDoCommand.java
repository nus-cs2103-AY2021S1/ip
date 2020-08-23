package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.task.ToDo;

/**
 * Represents a command which creates a task with a todo.
 */
public class ToDoCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of an ToDoCommand.
     *
     * @param commandDetails String array with task details.
     */
    public ToDoCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task with a todo to be added.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(" Got it. I've added this task: ");
        ToDo toDo = new ToDo(commandDetails[1]);
        tasks.getTasks().add(toDo);
        System.out.println(String.format("   %s \n Now you have %d tasks in the list. ",
                toDo, tasks.getTasks().size()));
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
