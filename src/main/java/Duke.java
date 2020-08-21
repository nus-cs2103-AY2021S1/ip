public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
        ui.greet();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    private void run() {
        while (true) {
            Parser parser = new Parser();
            String input = ui.readCommand();
            try {
                Command cmd = parser.parse(input);
                cmd.execute(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
