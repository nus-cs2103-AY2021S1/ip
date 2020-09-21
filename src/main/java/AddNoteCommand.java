import java.util.Map;
import java.util.Objects;

public class AddNoteCommand extends Command {

    // Attributes
    private String title;
    private String description;
    private Priority priority;

    // Constructor
    public AddNoteCommand(String title, String description, Priority priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    // Methods
    @Override
    String execute(TaskList tasks, NotesList notes, Ui ui, Storage storage, Map<String, Runnable> runnables) throws DukeException {
        Note newNote = Note.createNote(this.title, this.description, this.priority);
        notes.createNote(newNote);
        return String.format("Note with title %s created!", this.title);
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddNoteCommand that = (AddNoteCommand) o;

        if (!title.equals(that.title)) {
            return false;
        }
        if (!description.equals(that.description)) {
            return false;
        }
        return priority == that.priority;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + priority.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.title + this.description;
    }
}
