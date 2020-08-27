package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a command where a Task in the TaskList is marked as done.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Public constructor
     * @param index the index of the Task in the TaskList that is to be marked as done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the Task with the index specified within this DoneCommand as done.
     * An error message is printed if the index is out of bounds of the TaskList.
     * After successfully marking a task as done, the Task information is printed.
     * @param tasks the TaskList in which a Task is to be marked as done
     * @param storage unused Storage object
     * @param ui unused Ui object
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            if (index > tasks.size()) throw new DukeException("C'mon parder! That task doesn't exist cos you don't go so many!");
            Task toBeDone = tasks.get(index);
            tasks.markAsDone(index);
            System.out.println("Sure thing baws! This right here is marked as done!\n" + toBeDone.toString());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
