package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        try {
            storage.load(tasks);
        } catch (Exception e){
            Ui.showLoadingError();
        }
    }

    public void run() throws DukeException {
        Ui.greeting();
        Parser.parse(tasks);
        storage.save(TaskList.tasks);
    }

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
