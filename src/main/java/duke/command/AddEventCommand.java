package duke.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates an add command for event tasks. This command adds an event task to the task list. The format for this
 * command is: "event task /at venue".
 */
public class AddEventCommand extends AddCommand {

    /** Valid words to invoke the add event command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("event", "ev", "e"));

    /** Delimiter that separates the task's description and location */
    public static final String COMMAND_SPLIT_WORD = "/at";

    private String taskDescription;
    private String taskLocation;

    /**
     * Creates and initializes an AddEventCommand object.
     *
     * @param taskDescription The task description as entered by the user.
     * @param taskLocation The task location as entered by the user.
     */
    public AddEventCommand(String taskDescription, String taskLocation) {
        this.taskDescription = taskDescription;
        this.taskLocation = taskLocation;
    }

    /**
     * Executes the command in the CLI version of Duke. If successful, it will add an event task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Event(taskDescription, taskLocation);
        tasks.addTask(newTask);
        ui.showReplyForAddTask(newTask, tasks);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes the command in the GUI version of Duke. If successful, it will add an event task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        Task newTask = new Event(taskDescription, taskLocation);
        tasks.addTask(newTask);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return uiForGui.showReplyForAddTask(newTask, tasks);
    }
}
