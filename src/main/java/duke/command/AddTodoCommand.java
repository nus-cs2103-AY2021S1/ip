package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.TodoWrongFormatException;
import duke.exception.WrongFormatException;
import duke.task.Task;
import duke.task.ToDo;

import java.io.IOException;

/**
 * Encapsulates an add command for to-do tasks. This command adds a to-do task to the task list. The format for this
 * command is: "to-do task" (without the hyphen between "to" and "do").
 */
public class AddTodoCommand extends AddCommand {

    /** The entire command entered by the user */
    private String fullCommand;

    /**
     * Creates and initializes an AddTodoCommand object.
     *
     * @param fullCommand The entire command entered by the user.
     */
    public AddTodoCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command. If successful, it will add a to-do task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws TodoWrongFormatException If the add to-do command is in a wrong format.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TodoWrongFormatException {
        try {
            Task newTask = new ToDo(fullCommand.substring(5).trim());
            tasks.addTask(newTask);
            ui.showReplyForAddTask(newTask, tasks);
            try {
                storage.writeToFile(tasks);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (IndexOutOfBoundsException | WrongFormatException e) { // add to-do command is in a wrong format
            throw new TodoWrongFormatException();
        }
    }
}
