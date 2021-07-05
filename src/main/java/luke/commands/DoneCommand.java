package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.exception.LukeIndexOutOfBoundsException;
import luke.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a command to mark existing tasks as done.
 */
public class DoneCommand extends Command {

    protected int taskNumber;
    protected List<Integer> taskNumberList;
    protected boolean massOps;


    /**
     * Creates a DoneCommand to mark the given task as done.
     *
     * @param taskNumber index number of the task to be marksed as done
     */
    public DoneCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
        this.taskNumberList = null;
        this.massOps = false;
    }

    /**
     * Creates a DoneCommand to mark the given tasks as done.
     *
     * @param taskNumberList list of index numbers of the tasks to be marked as done
     */
    public DoneCommand(List<Integer> taskNumberList) {
        super();
        this.taskNumber = -1;
        this.taskNumberList = taskNumberList;
        this.massOps = true;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            if (this.massOps) {
                List<Task> doneTaskList = new ArrayList<>();
                this.taskNumberList.sort(Collections.reverseOrder()); // to make sure index numbers do not get shifted
                for (Integer integer : this.taskNumberList) {
                    doneTaskList.add(tasks.doTask(integer));
                }
                storage.save(tasks);
                return ui.showMassDoneResult(doneTaskList);
            } else {
                Task doneTask = tasks.doTask(this.taskNumber);
                storage.save(tasks);
                return ui.showDoneResult(doneTask);
            }
        } catch (NullPointerException e) {
            throw new LukeIndexOutOfBoundsException(String.valueOf(this.taskNumber));
        }
    }
}
