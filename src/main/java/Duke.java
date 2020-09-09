import java.util.Map;

public class Duke {

    // Attributes
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final NotesList notes;

    // Constructor

    /**
     * Creates a new duke bot that saves list of tasks to /data/todo.txt.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/todo.txt", "./data/notes.txt");

        if (storage.doesFileExistTasks()) {
            this.tasks = storage.readTasks();
        } else {
            storage.createFileTasks();
            this.tasks = new TaskList();
        }

        if (storage.doesFileExistNotes()) {
            this.notes = storage.readNotes();
        } else {
            storage.createFileNotes();
            this.notes = new NotesList();
        }

    }

    // Methods
    String getResponse(String fullCommand, Map<String, Runnable> runnables) {
        try {
            Command c = Parser.parse(fullCommand);
            return c.execute(tasks, notes, ui, storage, runnables);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
