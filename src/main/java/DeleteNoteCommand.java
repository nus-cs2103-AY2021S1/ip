import java.util.Map;

/**
 * Class representing commands to delete a task
 */
public class DeleteNoteCommand extends Command {

    // Attributes
    private final int noteNumber;

    // Constructor
    public DeleteNoteCommand(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    // Methods

    /**
     * Executes the command to delete a note by deleting specified note from given NotesList.
     * @param tasks TaskList representing list of current tasks.
     * @param notes NotesList representing list of current notes.
     * @param ui Ui object to handle printing of outputs.
     * @param storage Storage object to handle saving of outputs to computer
     * @throws IndexOutOfBoundsDukeException If note number is invalid.
     */
    @Override
    String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables)
            throws IndexOutOfBoundsDukeException {
        return notes.deleteNote(noteNumber);
    }

    /**
     * Returns whether the command is a command to exit.
     * @return false.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
