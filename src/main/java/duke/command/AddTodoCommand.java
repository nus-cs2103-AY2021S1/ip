package duke.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates an add command for to-do tasks. This command adds a to-do task to the task list. The format for this
 * command is: "to-do task" (without the hyphen between "to" and "do").
 */
public class AddTodoCommand extends AddCommand {

    /** Valid words to invoke the add to-do command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("todo", "td", "t"));

    private String taskDescription;

    /**
     * Creates and initializes an AddTodoCommand object.
     *
     * @param taskDescription The task description entered by the user.
     */
    public AddTodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the command in the CLI version of Duke. If successful, it will add a to-do task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskDescription);
        tasks.addTask(newTask);
        ui.showReplyForAddTask(newTask, tasks);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes the command in the GUI version of Duke. If successful, it will add a to-do task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        Task newTask = new ToDo(taskDescription);
        tasks.addTask(newTask);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return uiForGui.showReplyForAddTask(newTask, tasks);
    }
}
