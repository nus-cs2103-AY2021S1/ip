package duke.command;

import duke.note.Note;
import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to add a note in the program.
 */
public class NoteCommand extends Command {

    private String description;

    /**
     * Creates a command to add a Note object with a String description.
     *
     * @param description String description of the Note object.
     */
    public NoteCommand(String description) {
        this.description = description;
    }

    /**
     * Creates a new Note object and adds it into the NoteList. The note storage
     * is updated with the latest note and the Ui Object will return a
     * relevant string message to notify the user on this addition.
     * @param tasks TaskList object containing the list of tasks.
     * @param notes NoteList object containing the list of notes.
     * @param ui Ui object to output messages to the user.
     * @param taskStorage Storage object for storing tasks.
     * @param noteStorage Storage object for storing notes.
     * @return String response to user
     */
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage) {
        Note newNote = new Note(this.description);
        notes.add(newNote);

        //print output
        String output = ui.printNoteAdded(newNote);

        //update storage
        noteStorage.saveNotesToHardDisk(notes);

        return output;

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
