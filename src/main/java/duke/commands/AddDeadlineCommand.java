package duke.commands;

import java.time.LocalDate;

import duke.support.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a Deadline task to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String commandContent;

    /**
     * Creates a {@code AddDeadlineCommand} with given command content.
     *
     * @param commandContent A String of user input for command.
     */
    public AddDeadlineCommand(String commandContent) {
        this.commandContent = commandContent;
    }

    /**
     * Parses user's input into task index, deadline and content and creates a {@code Deadline} task for the task list.
     */
    public String run(TaskList taskList, Storage storage) {
        Task newTask;
        int index = commandContent.indexOf('/');
        String taskContent = commandContent.substring(0, index - 1);
        String dateString = commandContent.substring(index + 4);
        try {
            LocalDate taskDeadline = LocalDate.parse(dateString);
            newTask = new Deadline(taskContent, taskDeadline);
        } catch (Exception e) {
            newTask = new Deadline(taskContent, dateString);
        }
        taskList.add(newTask);
        return Ui.addTask(newTask, taskList);
    }

}
