package main.java.com.jacob.duke.command;
import java.util.List;

import main.java.com.jacob.duke.DukeException;
import main.java.com.jacob.duke.DukeList;
import main.java.com.jacob.duke.Ui;
import main.java.com.jacob.duke.io.Storage;
import main.java.com.jacob.duke.note.Note;


public class PrintNoteListCommand implements Command {
    @Override
    public String execute(Ui ui, DukeList dukeList, Storage storage) throws DukeException {
        List<Note> noteList = dukeList.getNoteList();
        return ui.showAllNotes(noteList);
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
