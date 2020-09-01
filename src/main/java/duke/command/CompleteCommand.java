package duke.command;

import duke.exceptions.IncompleteDukeCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The {@code CompleteCommand} class represents a command to mark a {@link Task} in a {@link TaskList} as complete.
 */
public class CompleteCommand extends Command {

    private int index;
    private Task completedTask;

    /**
     * Constructs a {@code CompleteCommand} with the specified {@code index}.
     *
     * @param index the index of the {@code Task} to mark as complete.
     */
    public CompleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes this {@code CompleteCommand}.
     * Marks the {@code Task} in the specified {@code list} at the stored {@code index} as complete.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        completedTask = list.markAsComplete(index);
        storage.save(list);
        super.completed = true;
    }

    /**
     * Prints a feedback confirming the execution of this {@code CompleteCommand}.
     *
     * @throws IncompleteDukeCommandException if this {@code CompleteCommand} was not executed.
     */
    @Override
    public String feedback() throws IncompleteDukeCommandException {
        if (super.completed) {
            return String.format("Nice! I've marked this task as complete:\n  %s\n",
                    completedTask.toString());
        } else {
            throw new IncompleteDukeCommandException("Complete command was not completed.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
