package duke.command;

import duke.exception.DukeException;
import duke.note.Note;
import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class RemoveNoteCommand extends Command {

    private final String markNote;

    /**
     * Constructs a command to delete a potential note in the note list.
     *
     * @param secondArg String argument to specify the note to delete.
     */
    public RemoveNoteCommand(String secondArg) {
        markNote = secondArg;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage)
            throws DukeException {

        //checks if second argument of instruction is valid
        try {
            boolean isAboveZero = (Integer.parseInt(this.markNote) < 1);
            boolean isBelowListSize = (Integer.parseInt(this.markNote) > notes.getSize());
            if (isAboveZero || isBelowListSize) {
                throw new DukeException("Please enter a valid item number from the note list! Type 'notes' to "
                        + "see your note list.");
            }
        } catch (NumberFormatException e) { //second argument wrong format
            throw new DukeException("Please only input 'RemoveNote <item number>' with no other inputs!");
        }

        int deleteIndex = Integer.parseInt(this.markNote);

        //delete task
        Note deletedNote = notes.delete(deleteIndex);

        //print output
        String output = ui.printNoteDeleted(notes, deletedNote);

        //update storage
        noteStorage.saveNotesToHardDisk(notes);

        return output;
    }

    /**
     * Returns false to indicate that the Command does not exit the program.
     *
     * @return Exit program indicator.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
