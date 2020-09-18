package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.note.Note;

public class AddNoteCommand extends Command {
    private Note note;

    public AddNoteCommand(Note note) {
        this.note = note;
    }

    /**
     * Executes the command.
     *
     * @param tasks   The list of existing notes.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing notes.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addNote(this.note);
        storage.writeNoteFile(tasks);
        return ui.showAddNoteMessage(this.note, tasks);
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
