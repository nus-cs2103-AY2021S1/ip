package command;

import java.io.IOException;
import java.time.DateTimeException;

import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.ToDoTask;
import util.Storage;
import util.TaskList;
import util.Ui;



/**
 * Represents the add command. The add command adds either a to-do, deadline or event task to the task list.
 */
public class AddCommand extends Command {
    /**
     * The type of the task to be added.
     */
    private final TaskType taskType;

    /**
     * The description of the task to be added.
     */
    private final String taskDesc;

    /**
     * The date of the task to be added.
     */
    private final String taskDate;

    /**
     * Creates a new Add command.
     *
     * @param type Type of the task to be added.
     * @param desc Description of the task to be added.
     */
    public AddCommand(String type, String desc) {
        this.taskType = TaskType.valueOf(type);
        this.taskDesc = desc;
        this.taskDate = "";
    }

    /**
     * Creates a new Add command.
     *
     * @param type Type of the task to be added.
     * @param desc Description of the task to be added.
     * @param date Date of the task to be added.
     */
    public AddCommand(String type, String desc, String date) {
        this.taskType = TaskType.valueOf(type);
        this.taskDesc = desc;
        this.taskDate = date;
    }

    /**
     * Executes the add command. The execution involves adding the task to the task list,
     * writing to the storage as well as printing the relevant UI.
     *
     * @param lst     List containing the current tasks.
     * @param ui      Ui allows execute to carry out ui methods to print to the console.
     * @param storage Storage allows execute to write and read files.
     * @return String response by the application after executing the command.
     */
    public String execute(TaskList lst, Ui ui, Storage storage) {
        String result = "";
        try {
            Task task;
            int taskNum = lst.size() + 1;
            switch (taskType) {
            case TODO:
                task = new ToDoTask(taskDesc, false);
                lst.add(task);
                storage.addLine("TODO | 0 | " + taskDesc);
                result = ui.showAddTask(task, taskNum);
                break;
            case EVENT:
                task = new EventTask(taskDesc, false, taskDate);
                lst.add(task);
                storage.addLine("EVENT | 0 | " + taskDesc + "| " + taskDate);
                result = ui.showAddTask(task, taskNum);
                break;
            case DEADLINE:
                task = new DeadlineTask(taskDesc, false, taskDate);
                lst.add(task);
                storage.addLine("DEADLINE | 0 | " + taskDesc + "| " + taskDate);
                result = ui.showAddTask(task, taskNum);
                break;
            default:
                assert false : taskType;
            }
        } catch (IOException e) {
            result = ui.showError(e.getMessage());
        } catch (DateTimeException e) {
            result = ui.showError("Please enter a valid date");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            result = ui.showError("Be sure to write the date in the correct format");
        }
        assert !result.isEmpty() : "Response should not be empty";
        return result;
    }

    /**
     * Constants representing the different tasks.
     */
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
}
