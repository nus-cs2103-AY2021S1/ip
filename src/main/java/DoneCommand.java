import java.io.IOException;
import java.util.Objects;

/**
 * Represents a command to mark a task as done. Every DoneCommand instance will have an id. Implements the Execute
 * command.
 */
public class DoneCommand extends Command {
    private final short id;

    /**
     * Public Constructor.
     *
     * @param id Id of task.
     */
    public DoneCommand(short id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Storage.
     * @throws IOException               If there is an error during saving to disk.
     * @throws IndexOutOfBoundsException If the task number provided does not exist.
     */
    @Override public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException, IndexOutOfBoundsException {
        Task curr = tasks.getTaskAtIndex(id);
        if (curr.isDone()) {
            ui.displayOutput(Ui.MESSAGE_ALR_DONE);
            return;
        }
        tasks.markAsDone(id);
        ui.showDone(curr);
        storage.updateMemory(tasks.getList());
    }

    /**
     * Returns true if the other object is a DoneCommand instance with the same id.
     *
     * @param o Other object.
     * @return True if the given object represents an DoneCommand equivalent to this DoneCommand, false otherwise.
     */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DoneCommand that = (DoneCommand) o;
        return id == that.id;
    }

    /**
     * Returns a hash code for this DoneCommand. The hashcode depends on the id.
     *
     * @return A hash code for this DoneCommand.
     */
    @Override public int hashCode() {
        return Objects.hash(id);
    }
}
