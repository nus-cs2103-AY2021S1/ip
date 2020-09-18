package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.note.Note;

public class DeleteNoteCommand extends Command {
    private int idx;

    public DeleteNoteCommand(int idx) {
        this.idx = idx - 1;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The list of existing notes.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing notes.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Note deletedNote = tasks.getNote(idx);
            tasks.deleteNote(this.idx);
            storage.writeNoteFile(tasks);
            return ui.showDeleteNoteMessage(deletedNote, tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number does not exist!");
        }
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return Always false.
     */
    public boolean isExit() {
        return false;
    }
}
