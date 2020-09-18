package duke.command;

import java.io.IOException;
import java.util.Date;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskType;

/**
 * Represents the command where user adds task
 */
public class AddCommand extends Command {
    private final TaskType type;
    private final String name;
    private Date date;

    /**
     * Creates an instance of AddCommand with no date field.
     * @param type the type of task added
     * @param name the name of task added
     */
    public AddCommand(TaskType type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * Creates an instance of AddCommand with date.
     * @param type the type of task added
     * @param name the name of task added
     * @param date the date of task added
     */
    public AddCommand(TaskType type, String name, Date date) {
        this.type = type;
        this.name = name;
        this.date = date;
    }

    /**
     * Returns false since this is not an exit command.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command by adding task to taskList, storing it in file and displaying
     * the success message.
     * @param taskList the list of tasks user has
     * @param ui ui instance to display messages
     * @param storage storage instance to manage storing on disk
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task;
            if (type == TaskType.TODO) {
                task = taskList.addTodo(name);
            } else if (type == TaskType.DEADLINE) {
                task = taskList.addDeadline(name, date);
            } else {
                task = taskList.addEvent(name, date);
            }
            storage.saveData(taskList);
            return ui.addSuccess(task, taskList.getCount());

        } catch (IOException e) {
            return e.getMessage();
        }
    }


}
