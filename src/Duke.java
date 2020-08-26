public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.startBot();
        boolean isCommand = true;
        while (isCommand) {
            try {
                String input = ui.getInput();
                isCommand = Parser.parse(input, tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                ui.printBorder();
            }
        }
        ui.stopBot();
    }

    public static void main(String[] args) throws DukeException {
        new Duke("tasklist.txt").run();
    }
}