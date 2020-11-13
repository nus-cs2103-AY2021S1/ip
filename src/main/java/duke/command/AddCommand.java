package duke.command;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.utils.Ui;

/**
 * Represents command that add a task to the duke.KK's task list upon execution
 */

public class AddCommand extends Command {
    private String taskType;
    private String info;

    /**
     * Constructor for AddCommand class
     */
    public AddCommand(String taskType, String info) {
        this.taskType = taskType;
        this.info = info;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Adds new task to the tasks provided and shows add message through ui.
     *
     * @param tasks  a TaskList object which contains a list of task
     * @param ui a UI object which can prints message to console
     */
    @Override
    public String execute(TaskList tasks, Ui ui) throws DukeException {

        Task task;

        if (taskType.equals("todo")) {
            task = new ToDo(info);
        } else if (taskType.equals("deadline")) {
            try {
                String[] deadlineParts = info.split(" /by ");
                task = new Deadline(deadlineParts[0], deadlineParts[1]);
            } catch (Exception e ) {
                throw new DukeException("invalid deadline command format");
            }
        } else { // event
            try {
                String[] eventParts = info.split(" /at ");
                task = new Event(eventParts[0], eventParts[1]);
            } catch (Exception e ) {
                throw new DukeException("invalid event command format");
            }
        }

        tasks.addTask(task);
        return ui.showAddMessage(task, tasks);

    }

}
