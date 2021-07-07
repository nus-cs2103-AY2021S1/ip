package duke.command;

import duke.note.NoteList;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class NotesCommand extends Command {
    @Override
    public String execute(TaskList tasks, NoteList notes, Ui ui, Storage taskStorage, Storage noteStorage) {
        return ui.printNotes(notes);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
