package duke.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.ToDo;

/**The AddCommand class will add a Task of type TaskType to the
 * list of tasks in TaskList.
 */
public class AddCommand implements Command {
    private final TaskType type;
    private final String name;
    private final String time;

    /**
     * Constructor for an AddCommand object.
     *
     * @param type A TaskType which represents the type of Task to be added.
     * @param name A String representation of the name of the task.
     * @param time A String representation of the time associated with the task.
     */
    public AddCommand(TaskType type, String name, String time) {
        this.type = type;
        this.name = name;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (type == TaskType.TODO) {
                tasks.addTask(new ToDo(name));
            } else if (type == TaskType.DEADLINE) {
                LocalDate by = LocalDate.parse(time);
                tasks.addTask(new Deadline(name, by));
            } else if (type == TaskType.EVENT) {
                LocalDate at = LocalDate.parse(time);
                tasks.addTask(new Event(name, at));
            }
            storage.updateDataFile(tasks.getList());
        } catch (IOException error) {
            error.printStackTrace();
        } catch (DateTimeException error) {
            System.out.println("Please provide a valid date and time");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
