package duke.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.UiForGui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * Encapsulates an add command for deadline tasks. This command adds a deadline task to the task list. The format for
 * this command is: "deadline task /by YYYY-MM-DD hhmm" where hh is hours and mm is minutes.
 */
public class AddDeadlineCommand extends AddCommand {

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
     * Executes the command. If successful, it will add a deadline task to the task list.
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
