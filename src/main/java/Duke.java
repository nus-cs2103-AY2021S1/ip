public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = storage.load();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            try {
                Command command = Parser.parse(input);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (Exception ex) {
                ui.show("\t " + ex.getMessage());
            }
        }

        storage.save(taskList);
        System.exit(0);
    }
}
