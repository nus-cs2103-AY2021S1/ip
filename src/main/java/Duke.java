public class Duke {
    
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isExit;
    
    Duke() {
        storage = new Storage();
        tasks = new TaskList();
        storage.retrieve(tasks);
        this.ui = new Ui();
    }

    // activate the Duke Bot
    public void echo() {
        ui.greetings();
        isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.getExitStatus();
            } catch (DukeException e) {
                ui.printException(e.getMessage());
            }
        }
    }

}
