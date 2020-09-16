package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates an add command for deadline tasks. This command adds a deadline task to the task list. The format for
 * this command is: "deadline task /by YYYY-MM-DD hhmm" where hh is hours and mm is minutes.
 */
public class AddDeadlineCommand extends AddCommand {

    /** Valid words to invoke the add deadline command */
    public static final List<String> COMMAND_WORDS = new ArrayList<>(List.of("deadline", "d"));

    /** Delimiter that separates the task's description and deadline */
    public static final String COMMAND_SPLIT_WORD = "/by";

    private String taskDescription;
    private LocalDate deadlineDate;
    private LocalDateTime deadlineDateAndTime;

    /**
     * Creates and initializes an AddDeadlineCommand object.
     *
     * @param taskDescription The task description as entered by the user.
     * @param deadlineDate The date of the deadline.
     * @param deadlineDateAndTime The date and time of the deadline.
     */
    public AddDeadlineCommand(String taskDescription, LocalDate deadlineDate, LocalDateTime deadlineDateAndTime) {
        this.taskDescription = taskDescription;
        this.deadlineDate = deadlineDate;
        this.deadlineDateAndTime = deadlineDateAndTime;
    }

    /**
     * Executes the command in the CLI version of Duke. If successful, it will add a deadline task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new Deadline(taskDescription, deadlineDate, deadlineDateAndTime);
        tasks.addTask(newTask);
        ui.showReplyForAddTask(newTask, tasks);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Executes the command in the GUI version of Duke. If successful, it will add a deadline task to the task list.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        Task newTask = new Deadline(taskDescription, deadlineDate, deadlineDateAndTime);
        tasks.addTask(newTask);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return uiForGui.showReplyForAddTask(newTask, tasks);
    }
}
