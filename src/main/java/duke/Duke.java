package duke;

/**
 * Represents Duke, a virtual task management system and personal assistant.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isQuitting;

    /**
     * Initializes a newly created Duke program with a saved file path.
     *
     * @param filePath path of the saved tasks file.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.isQuitting = false;
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.chatPrint(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        this.ui.greet();
        while (!this.isQuitting) {
            try {
                Command c = Parser.parse(this.ui.getInput());
                c.execute(this.tasks, this.ui, this.storage);
                this.isQuitting = c.isQuitting();
            } catch (DukeException e) {
                ui.chatPrint(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}