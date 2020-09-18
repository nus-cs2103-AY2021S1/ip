package luke.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.exception.LukeIndexOutOfBoundsException;
import luke.task.Task;

/**
 * Represents a command to delete existing tasks.
 */
public class DeleteCommand extends Command {

    protected int taskNumber;
    protected List<Integer> taskNumberList;
    protected boolean massOps;

    /**
     * Creates a DeleteCommand to delete the given task.
     *
     * @param taskNumber index number of the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
        this.taskNumberList = null;
        this.massOps = false;
    }

    /**
     * Creates a DeleteCommand to delete the given tasks.
     *
     * @param taskNumberList list of index numbers of the tasks to be deleted
     */
    public DeleteCommand(List<Integer> taskNumberList) {
        super();
        this.taskNumber = -1;
        this.taskNumberList = taskNumberList;
        this.massOps = true;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            if (this.massOps) {
                List<Task> deletedTaskList = new ArrayList<>();
                this.taskNumberList.sort(Collections.reverseOrder()); // to make sure index numbers do not get shifted
                for (Integer integer : this.taskNumberList) {
                    deletedTaskList.add(tasks.deleteTask(integer));
                }
                storage.save(tasks);
                return ui.showMassDeleteResult(deletedTaskList, tasks.getSize());
            } else {
                Task deletedTask = tasks.deleteTask(this.taskNumber);
                storage.save(tasks);
                return ui.showDeleteResult(deletedTask, tasks.getSize());
            }
        } catch (NullPointerException e) {
            throw new LukeIndexOutOfBoundsException(String.valueOf(this.taskNumber));
        }
    }
}
