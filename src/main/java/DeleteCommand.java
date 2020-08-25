import java.io.IOException;
import java.util.Objects;

/**
 * Represents a command to delete a particular task. A DeleteCommand instance requires an id. Implements the execute
 * method.
 */
public class DeleteCommand extends Command {
    private final short id;

    /**
     * Public Constructor.
     *
     * @param id Task number.
     */
    public DeleteCommand(short id) {
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
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException {
        ui.showDelete((short) (tasks.size() - 1), tasks.getTaskAtIndex(id));
        tasks.delete(id);
        storage.updateMemory(tasks.getList());
    }

    /**
     * Returns true if the other object is a DeleteCommand instance with the same id.
     *
     * @param o Other object.
     * @return True if the given object represents an DeleteCommand equivalent to this DeleteCommand, false otherwise.
     */
    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteCommand that = (DeleteCommand) o;
        return id == that.id;
    }

    /**
     * Returns a hash code for this DeleteCommand. The hashcode depends on the id.
     *
     * @return A hash code for this DeleteCommand.
     */
    @Override public int hashCode() {
        return Objects.hash(id);
    }
}
