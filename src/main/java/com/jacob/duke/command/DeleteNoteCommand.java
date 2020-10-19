package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.note.Note;

public class DeleteNoteCommand implements Command {
    private String fullCommand;
    public DeleteNoteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Note> noteList = dukeList.getNoteList();
        int deleteIndex = Integer.parseInt(fullCommand.substring("note-delete ".length())) - 1;
        if (deleteIndex == -1 || deleteIndex >= noteList.size()) {
            throw new DukeException("No such note exists.");
        }
        Note theRemovedNote = noteList.remove(deleteIndex);
        storage.removeTextFromNotes(theRemovedNote.convertToFileFormat());
        return ui.showNoteDeleted(theRemovedNote.getCurrentStatus(), noteList);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
